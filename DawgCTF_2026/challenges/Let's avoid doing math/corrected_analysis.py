#!/usr/bin/env python3

# CORRECTED: Calculate false positive and false negative rates properly
import re

def parse_log_file(file_path):
    """Parse the log file to extract known vs detected threat depths"""
    samples = []
    
    with open(file_path, 'r') as f:
        content = f.read()
    
    # Find all sample blocks
    sample_blocks = re.split(r'--- Sample Analysis #\d+ ---', content)
    
    for block in sample_blocks:
        if 'Sample ID:' not in block:
            continue
            
        # Extract known and detected threat depths
        known_match = re.search(r'Known Threat Depth: (\w+)', block)
        detected_match = re.search(r'Detected Threat Depth: (\w+)', block)
        
        if known_match and detected_match:
            known = known_match.group(1)
            detected = detected_match.group(1)
            samples.append((known, detected))
    
    return samples

def calculate_correct_metrics(samples):
    """Calculate CORRECT false positive and false negative rates for each classification"""
    
    classifications = ['minor', 'medium', 'major']
    
    # Count actual occurrences of each classification
    actual_counts = {cls: 0 for cls in classifications}
    predicted_counts = {cls: 0 for cls in classifications}
    
    # Count confusion matrix
    confusion = {cls: {pred: 0 for pred in classifications} for cls in classifications}
    
    for known, detected in samples:
        actual_counts[known] += 1
        predicted_counts[detected] += 1
        confusion[known][detected] += 1
    
    results = {}
    
    for cls in classifications:
        # CORRECT False Positive Rate: FP / (FP + TN)
        # Where: FP = times we predicted this class but it was wrong
        #        TN = times we predicted NOT this class and it was right
        
        fp = sum(confusion[other][cls] for other in classifications if other != cls)
        tn = sum(confusion[other][other] for other in classifications if other != cls)
        
        fpr = fp / (fp + tn) if (fp + tn) > 0 else 0
        
        # CORRECT False Negative Rate: FN / (FN + TP)  
        # Where: FN = times it was this class but we predicted wrong
        #        TP = times it was this class and we predicted right
        
        fn = sum(confusion[cls][other] for other in classifications if other != cls)
        tp = confusion[cls][cls]
        
        fnr = fn / (fn + tp) if (fn + tp) > 0 else 0
        
        results[cls] = {
            'fpr': fpr,
            'fnr': fnr,
            'fp': fp,
            'fn': fn,
            'tp': tp,
            'tn': tn,
            'actual_count': actual_counts[cls],
            'predicted_count': predicted_counts[cls]
        }
    
    return results, actual_counts, predicted_counts, confusion

def format_results(results):
    """Format results with single leading zero and no trailing zeros, comma-separated"""
    
    classifications = ['minor', 'medium', 'major']
    formatted_rates = []
    
    for cls in classifications:
        fpr = results[cls]['fpr']
        fnr = results[cls]['fnr']
        
        def format_rate(rate):
            if rate == 1.0:
                return "1"
            else:
                rounded = round(rate, 1)
                if rounded < 1:
                    formatted = f"{rounded:.1f}"
                    if formatted.startswith("00"):
                        formatted = "0" + formatted[2:]
                else:
                    formatted = f"{rounded:.1f}"
                
                if '.' in formatted:
                    formatted = formatted.rstrip('0').rstrip('.')
                return formatted
        
        formatted_fpr = format_rate(fpr)
        formatted_fnr = format_rate(fnr)
        
        formatted_rates.extend([formatted_fpr, formatted_fnr])
    
    return ','.join(formatted_rates)

def main():
    log_file = '/Users/elenaeftimie/Desktop/CTFs/SIX-SEVEN/Let\'s avoid doing math/threat_depth_analysis.log'
    
    samples = parse_log_file(log_file)
    print(f"Total samples parsed: {len(samples)}")
    
    results, actual_counts, predicted_counts, confusion = calculate_correct_metrics(samples)
    
    print("\nCORRECTED Analysis:")
    print("=" * 60)
    
    classifications = ['minor', 'medium', 'major']
    for cls in classifications:
        res = results[cls]
        print(f"\n{cls.upper()} Classification:")
        print(f"  Actual samples: {res['actual_count']}")
        print(f"  Predicted as {cls}: {res['predicted_count']}")
        print(f"  True Positives: {res['tp']}")
        print(f"  False Positives: {res['fp']}")
        print(f"  False Negatives: {res['fn']}")
        print(f"  True Negatives: {res['tn']}")
        print(f"  False Positive Rate: {res['fpr']:.3f}")
        print(f"  False Negative Rate: {res['fnr']:.3f}")
        print(f"  FPR Formula: {res['fp']} / ({res['fp']} + {res['tn']}) = {res['fpr']:.3f}")
        print(f"  FNR Formula: {res['fn']} / ({res['fn']} + {res['tp']}) = {res['fnr']:.3f}")
    
    print(f"\nConfusion Matrix:")
    print("Known\\Detected", " | ".join(classifications))
    print("-" * 40)
    for known in classifications:
        row = [str(confusion[known][det]) for det in classifications]
        print(f"{known:12}", " | ".join(row))
    
    formatted = format_results(results)
    print(f"\nCORRECTED formatted results: {formatted}")

if __name__ == "__main__":
    main()
