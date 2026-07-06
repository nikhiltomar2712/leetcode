import "math"

func myAtoi(s string) int {
    if len(s) == 0 {
        return 0
    }
    
    i := 0
    n := len(s)
    
    for i < n && s[i] == ' ' {
        i++
    }
    
    if i == n {
        return 0
    }
    
    sign := 1
    if s[i] == '+' || s[i] == '-' {
        if s[i] == '-' {
            sign = -1
        }
        i++
    }
    
    result := 0
    for i < n && s[i] >= '0' && s[i] <= '9' {
        digit := int(s[i] - '0')
        
        if result > (math.MaxInt32-digit)/10 {
            if sign == 1 {
                return math.MaxInt32
            }
            return math.MinInt32
        }
        
        result = result*10 + digit
        i++
    }
    
    return result * sign
}