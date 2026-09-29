class Solution {
    public int getMinSwaps(String num, int k) {
        char[] arr = num.toCharArray();
        for (int i = 0; i < k; i++) {
            nextPermutation(arr);
        }
        char[] original = num.toCharArray();
        int res = 0;
        for (int i = 0; i < original.length; i++) {
            if (original[i] == arr[i]) continue;
            int j = i + 1;
            while (arr[j] != original[i]) j++;
            while (j > i) {
                char temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
                res++;
            }
        }
        return res;
    }

    private void nextPermutation(char[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) i--;
        int j = arr.length - 1;
        while (arr[j] <= arr[i]) j--;
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        reverse(arr, i + 1, arr.length - 1);
    }

    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}