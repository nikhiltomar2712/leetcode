char nextGreatestLetter(char* letters, int lettersSize, char target) {
    int left = 0, right = lettersSize;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (letters[mid] > target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    
    // If left == lettersSize, no greater letter exists → wrap around
    return letters[left % lettersSize];
}