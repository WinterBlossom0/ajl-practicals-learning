#!/bin/bash
# Script to run and verify all 31 Java programs sequentially using Java single-file execution.

echo "=========================================="
echo "      VERIFYING ALL 31 JAVA DEMOS         "
echo "=========================================="
echo ""

# Helper function to run a file and display output
run_java_file() {
    local file_path="$1"
    local run_args="$2"
    local base_name=$(basename "$file_path")
    
    echo "------------------------------------------"
    echo "Executing: $base_name"
    echo "------------------------------------------"
    
    # Run the Java source file directly using JEP 330
    if [ -n "$run_args" ]; then
        java $run_args "$file_path"
    else
        java "$file_path"
    fi
    
    echo ""
}

# 1. Run Design Patterns (Q1 to Q19)
echo "=== Category 1: Design Patterns ==="
for i in {1..19}; do
    # Find the file starting with Q{i}_
    file=$(find "/mnt/c/Users/vihaa/OneDrive/Documents/ajl practicals/design_patterns" -name "Q${i}_*.java" | head -n 1)
    if [ -n "$file" ]; then
        run_java_file "$file"
    else
        echo "Error: Q${i} file not found."
    fi
done

# 2. Run Cryptography (Q20 to Q26)
echo "=== Category 2: Cryptography ==="
for i in {20..26}; do
    file=$(find "/mnt/c/Users/vihaa/OneDrive/Documents/ajl practicals/cryptography" -name "Q${i}_*.java" | head -n 1)
    if [ -n "$file" ]; then
        run_java_file "$file"
    else
        echo "Error: Q${i} file not found."
    fi
done

# 3. Run Java Features (Q27 to Q31)
echo "=== Category 3: Java Features ==="
for i in {27..31}; do
    file=$(find "/mnt/c/Users/vihaa/OneDrive/Documents/ajl practicals/java_features" -name "Q${i}_*.java" | head -n 1)
    if [ -n "$file" ]; then
        # Enable assertions (-ea) specifically for Q30_AssertionDemo
        if [ "$i" -eq 30 ]; then
            run_java_file "$file" "-ea"
        else
            run_java_file "$file"
        fi
    else
        echo "Error: Q${i} file not found."
    fi
done

echo "=========================================="
echo "        VERIFICATION COMPLETE             "
echo "=========================================="
