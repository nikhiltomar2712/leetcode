class Solution(object):
    def addOperators(self, num, target):
        result = []
        
        def dfs(start, path, value, last_operand):
            # start: current index in num
            # path: current expression string
            # value: current evaluated value
            # last_operand: last operand (to handle multiplication)
            
            if start == len(num):
                if value == target:
                    result.append(path)
                return
            
            for end in range(start + 1, len(num) + 1):
                # Take substring num[start:end]
                curr_str = num[start:end]
                curr_val = int(curr_str)
                
                # Skip numbers with leading zeros
                if len(curr_str) > 1 and curr_str[0] == '0':
                    continue
                
                if start == 0:
                    # First number, no operator before it
                    dfs(end, curr_str, curr_val, curr_val)
                else:
                    # Add '+'
                    dfs(end, path + '+' + curr_str, value + curr_val, curr_val)
                    
                    # Add '-'
                    dfs(end, path + '-' + curr_str, value - curr_val, -curr_val)
                    
                    # Add '*': need to undo the last operand
                    dfs(end, path + '*' + curr_str, 
                        value - last_operand + last_operand * curr_val, 
                        last_operand * curr_val)
        
        dfs(0, "", 0, 0)
        return result