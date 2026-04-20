#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char** readOutput(int* line_count) {
    FILE* input = fopen("output.txt", "rb");
    if (input == NULL) {
        printf("Error opening output.txt\n");
        return NULL;
    }

    // Read entire file into buffer
    fseek(input, 0, SEEK_END);
    long file_size = ftell(input);
    fseek(input, 0, SEEK_SET);
    
    char* buffer = malloc(file_size + 1);
    fread(buffer, 1, file_size, input);
    buffer[file_size] = '\0';
    fclose(input);
    
    // Parse the buffer - split by } and remove final ~
    int lines = 0;
    int max_len = 0;
    
    // Count lines first
    char* ptr = buffer;
    char* line_start = buffer;
    
    while (*ptr != '\0' && *ptr != '~') {
        if (*ptr == '}') {
            lines++;
            int line_len = ptr - line_start;
            if (line_len > max_len) {
                max_len = line_len;
            }
            line_start = ptr + 1;
        }
        ptr++;
    }
    
    // Allocate array for lines
    char** arr = malloc(lines * sizeof(char*));
    
    // Extract lines
    ptr = buffer;
    line_start = buffer;
    int line_idx = 0;
    
    while (*ptr != '\0' && *ptr != '~' && line_idx < lines) {
        if (*ptr == '}') {
            int line_len = ptr - line_start;
            arr[line_idx] = malloc(line_len + 1);
            strncpy(arr[line_idx], line_start, line_len);
            arr[line_idx][line_len] = '\0';
            
            line_start = ptr + 1;
            line_idx++;
        }
        ptr++;
    }
    
    free(buffer);
    *line_count = lines;
    return arr;
}

char** decompressArray(char** compressed, int lines, int* orig_height, int* orig_width) {
    // Calculate original dimensions
    // Each compressed line represents 2 original lines
    // Each character in compressed line represents 3 original characters
    int orig_h = lines * 2;
    int orig_w = (strlen(compressed[0])) * 3;
    
    // Allocate original array
    char** original = malloc(orig_h * sizeof(char*));
    for (int i = 0; i < orig_h; i++) {
        original[i] = malloc(orig_w + 1); // +1 for null terminator
    }
    
    // Decompress each 2x3 block
    for (int line = 0; line < lines; line++) {
        for (int col = 0; col < strlen(compressed[line]); col++) {
            char c = compressed[line][col];
            
            // Reverse the compression: binary = c - 0b00100000
            int binary_val = (int)c - 0b00100000;
            
            // Convert to 6-bit binary string
            char binary_str[7];
            for (int i = 5; i >= 0; i--) {
                binary_str[5-i] = (binary_val & (1 << i)) ? '1' : '0';
            }
            binary_str[6] = '\0';
            
            // Fill the 2x3 block in original array
            // Top row
            original[line*2][col*3] = binary_str[0];
            original[line*2][col*3 + 1] = binary_str[1];
            original[line*2][col*3 + 2] = binary_str[2];
            
            // Bottom row
            original[line*2 + 1][col*3] = binary_str[3];
            original[line*2 + 1][col*3 + 1] = binary_str[4];
            original[line*2 + 1][col*3 + 2] = binary_str[5];
        }
        
        // Add null terminators to original lines
        original[line*2][orig_w] = '\0';
        original[line*2 + 1][orig_w] = '\0';
    }
    
    *orig_height = orig_h;
    *orig_width = orig_w + 1; // +1 for null terminator
    return original;
}

void writeOriginal(char** original, int height) {
    FILE* output = fopen("original.txt", "w");
    if (output == NULL) {
        printf("Error creating original.txt\n");
        return;
    }
    
    for (int i = 0; i < height; i++) {
        fprintf(output, "%s\n", original[i]);
    }
    
    fclose(output);
    printf("Original binary data written to original.txt\n");
}

void freeArray(char** arr, int len) {
    for (int i = 0; i < len; i++) {
        free(arr[i]);
    }
    free(arr);
}

int main() {
    int line_count;
    char** compressed = readOutput(&line_count);
    if (compressed == NULL) {
        return 1;
    }
    
    printf("Read %d compressed lines\n", line_count);
    
    int orig_height, orig_width;
    char** original = decompressArray(compressed, line_count, &orig_height, &orig_width);
    
    printf("Decompressed to %d x %d array\n", orig_height, orig_width-1);
    
    writeOriginal(original, orig_height);
    
    freeArray(compressed, line_count);
    freeArray(original, orig_height);
    
    return 0;
}
