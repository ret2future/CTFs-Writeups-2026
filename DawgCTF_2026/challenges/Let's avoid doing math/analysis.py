#!/usr/bin/env python3

# Parse the threat depth analysis log and calculate metrics
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

def calculate_metrics(samples):
    """Calculate false positive and false negative rates for each classification"""
    
    # Initialize counters
    classifications = ['minor', 'medium', 'major']
    
    # Structure: {classification: {'true_positives': 0, 'false_positives': 0, 'false_negatives': 0, 'true_negatives': 0}}
    metrics = {cls: {'tp': 0, 'fp': 0, 'fn': 0, 'tn': 0} for cls in classifications}
    
    # Calculate confusion matrix for each classification
    for known, detected in samples:
        for cls in classifications:
            if known == cls and detected == cls:
                metrics[cls]['tp'] += 1
            elif known != cls and detected == cls:
                metrics[cls]['fp'] += 1
            elif known == cls and detected != cls:
                metrics[cls]['fn'] += 1
            else:  # known != cls and detected != cls
                metrics[cls]['tn'] += 1
    
    # Calculate rates
    results = {}
    for cls in classifications:
        tp = metrics[cls]['tp']
        fp = metrics[cls]['fp']
        fn = metrics[cls]['fn']
        tn = metrics[cls]['tn']
        
        # False Positive Rate = FP / (FP + TN)
        fpr = fp / (fp + tn) if (fp + tn) > 0 else 0
        
        # False Negative Rate = FN / (FN + TP)
        fnr = fn / (fn + tp) if (fn + tp) > 0 else 0
        
        results[cls] = {
            'fpr': fpr,
            'fnr': fnr,
            'counts': metrics[cls]
        }
    
    return results

def format_results(results):
    """Format results with single leading zero and no trailing zeros, comma-separated"""
    
    # Sort classifications by importance (minor -> medium -> major)
    classifications = ['minor', 'medium', 'major']
    
    formatted_rates = []
    
    for cls in classifications:
        fpr = results[cls]['fpr']
        fnr = results[cls]['fnr']
        
        def format_rate(rate):
            """Helper function to format rate with single leading zero and no trailing zeros"""
            if rate == 1.0:
                return "1"
            else:
                # Round to 1 decimal place first
                rounded = round(rate, 1)
                # Convert to string and ensure proper formatting
                if rounded < 1:
                    # Format with single leading zero
                    formatted = f"{rounded:.1f}"
                    # Ensure it starts with "0" not "00"
                    if formatted.startswith("00"):
                        formatted = "0" + formatted[2:]
                else:
                    formatted = f"{rounded:.1f}"
                
                # Remove trailing zeros after decimal point
                if '.' in formatted:
                    formatted = formatted.rstrip('0').rstrip('.')
                return formatted
        
        formatted_fpr = format_rate(fpr)
        formatted_fnr = format_rate(fnr)
        
        formatted_rates.extend([formatted_fpr, formatted_fnr])
    
    return ','.join(formatted_rates)

def main():
    log_file = '/Users/elenaeftimie/Desktop/CTFs/SIX-SEVEN/Let\'s avoid doing math/threat_depth_analysis.log'
    
    # Parse the log file
    samples = parse_log_file(log_file)
    print(f"Total samples parsed: {len(samples)}")
    
    # Calculate metrics
    results = calculate_metrics(samples)
    
    # Print detailed analysis
    print("\nDetailed Analysis:")
    print("=" * 60)
    
    classifications = ['minor', 'medium', 'major']
    for cls in classifications:
        counts = results[cls]['counts']
        fpr = results[cls]['fpr']
        fnr = results[cls]['fnr']
        
        print(f"\n{cls.upper()} Classification:")
        print(f"  True Positives: {counts['tp']}")
        print(f"  False Positives: {counts['fp']}")
        print(f"  False Negatives: {counts['fn']}")
        print(f"  True Negatives: {counts['tn']}")
        print(f"  False Positive Rate: {fpr:.3f}")
        print(f"  False Negative Rate: {fnr:.3f}")
    
    # Format final results
    formatted = format_results(results)
    print(f"\nFinal formatted results: {formatted}")

if __name__ == "__main__":
    main()
