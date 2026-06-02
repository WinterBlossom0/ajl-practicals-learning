#!/bin/bash
# Script to run and verify all Java programs sequentially using Java single-file execution.

echo "=========================================="
echo "      VERIFYING ALL JAVA DEMOS            "
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

# 0. Run Difficult Questions (Q1 to Q5 in difficult_questions)
echo "=== Category 0: Difficult Questions ==="
for i in {1..5}; do
    file=$(find "/mnt/c/Users/vihaa/OneDrive/Documents/ajl practicals/difficult_questions" -name "Q0${i}_*.java" | head -n 1)
    if [ -n "$file" ]; then
        run_java_file "$file"
    else
        echo "Error: Q${i} file not found."
    fi
done

# 1. Run Design Patterns (Q1 to Q19 in design_patterns)
echo "=== Category 1: Design Patterns ==="
for i in {1..19}; do
    if [ $i -lt 10 ]; then
        prefix="Q0${i}"
    else
        prefix="Q${i}"
    fi
    file=$(find "/mnt/c/Users/vihaa/OneDrive/Documents/ajl practicals/design_patterns" -name "${prefix}_*.java" | head -n 1)
    if [ -n "$file" ]; then
        run_java_file "$file"
    else
        echo "Error: Q${i} file not found."
    fi
done

# 2. Run Cryptography (Q20 to Q26 in cryptography)
echo "=== Category 2: Cryptography ==="
for i in {20..26}; do
    file=$(find "/mnt/c/Users/vihaa/OneDrive/Documents/ajl practicals/cryptography" -name "Q${i}_*.java" | head -n 1)
    if [ -n "$file" ]; then
        run_java_file "$file"
    else
        echo "Error: Q${i} file not found."
    fi
done

# 3. Run Java Features (Q27 to Q31 in java_features)
echo "=== Category 3: Java Features ==="
for i in {27..31}; do
    file=$(find "/mnt/c/Users/vihaa/OneDrive/Documents/ajl practicals/java_features" -name "Q${i}_*.java" | head -n 1)
    if [ -n "$file" ]; then
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
