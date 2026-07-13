int calculate(char* s) {
    int len = strlen(s);
    int* stack = (int*)malloc(len * sizeof(int));
    int top = -1;
    
    int num = 0;
    char sign = '+';  // Previous operator, start with '+'
    
    for (int i = 0; i <= len; i++) {
        char c = s[i];
        
        // Build number
        if (c >= '0' && c <= '9') {
            num = num * 10 + (c - '0');
        }
        
        // Process when we hit an operator or end of string
        if ((c < '0' || c > '9') && c != ' ' && c != '\0' || i == len - 1) {
            if (i == len - 1 && c >= '0' && c <= '9') {
                // If last character is a digit, we need to process
                // But we'll hit the end-of-string case below
            }
            
            if (sign == '+') {
                stack[++top] = num;
            } else if (sign == '-') {
                stack[++top] = -num;
            } else if (sign == '*') {
                stack[top] *= num;  // Multiply with top of stack
            } else if (sign == '/') {
                stack[top] /= num;  // Divide top of stack
            }
            
            sign = c;
            num = 0;
        }
    }
    
    // Sum all numbers in the stack
    int result = 0;
    for (int i = 0; i <= top; i++) {
        result += stack[i];
    }
    
    free(stack);
    return result;
}