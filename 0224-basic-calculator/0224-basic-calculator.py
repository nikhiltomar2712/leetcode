class Solution(object):
    def calculate(self, s):
        stack = []
        result = 0
        sign = 1  # 1 for '+', -1 for '-'
        i = 0
        n = len(s)
        
        while i < n:
            ch = s[i]
            
            if ch.isdigit():
                # Build the number
                num = 0
                while i < n and s[i].isdigit():
                    num = num * 10 + int(s[i])
                    i += 1
                result += sign * num
                continue  # skip i++ at the end
            
            elif ch == '+':
                sign = 1
            elif ch == '-':
                sign = -1
            elif ch == '(':
                # Push current result and sign to stack
                stack.append(result)
                stack.append(sign)
                # Reset for the new expression inside parentheses
                result = 0
                sign = 1
            elif ch == ')':
                # Pop sign and previous result
                sign = stack.pop()
                prev_result = stack.pop()
                result = prev_result + sign * result
            
            i += 1
        
        return result
        