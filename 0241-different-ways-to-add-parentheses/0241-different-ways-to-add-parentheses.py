class Solution(object):
    def diffWaysToCompute(self, expression):
        """
        :type expression: str
        :rtype: List[int]
        """
        # Memoization to avoid recomputation
        memo = {}
        
        def compute(expr):
            if expr in memo:
                return memo[expr]
            
            # If expr is just a number
            if expr.isdigit():
                return [int(expr)]
            
            res = []
            for i, ch in enumerate(expr):
                if ch in '+-*':
                    left_results = compute(expr[:i])
                    right_results = compute(expr[i+1:])
                    
                    for left_val in left_results:
                        for right_val in right_results:
                            if ch == '+':
                                res.append(left_val + right_val)
                            elif ch == '-':
                                res.append(left_val - right_val)
                            else:  # ch == '*'
                                res.append(left_val * right_val)
            
            memo[expr] = res
            return res
        
        return compute(expression) 