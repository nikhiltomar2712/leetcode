class Solution {
    public int minMovesToMakePalindrome(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int moves = 0;
        int left = 0, right = n - 1;
        while (left < right) {
            if (arr[left] == arr[right]) {
                left++;
                right--;
            } else {
                int k = right;
                while (k > left && arr[k] != arr[left]) k--;
                if (k == left) {
                    // arr[left] is the odd character, swap it towards center
                    char temp = arr[left];
                    arr[left] = arr[left + 1];
                    arr[left + 1] = temp;
                    moves++;
                } else {
                    while (k < right) {
                        char temp = arr[k];
                        arr[k] = arr[k + 1];
                        arr[k + 1] = temp;
                        k++;
                        moves++;
                    }
                    left++;
                    right--;
                }
            }
        }
        return moves;
    }
}