class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        int[] a = getRealAndImag(num1);
        int[] b = getRealAndImag(num2);
        
        int real = a[0] * b[0] - a[1] * b[1];
        int imag = a[0] * b[1] + a[1] * b[0];
        
        return real + "+" + imag + "i";
    }
    
    private int[] getRealAndImag(String s) {
        int plusIndex = s.indexOf('+');
        String realStr = s.substring(0, plusIndex);
        String imagStr = s.substring(plusIndex + 1, s.length() - 1);
        return new int[]{
            Integer.parseInt(realStr),
            Integer.parseInt(imagStr)
        };
    }
}