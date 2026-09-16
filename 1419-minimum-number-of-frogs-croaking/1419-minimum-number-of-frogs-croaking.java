class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        int frogs = 0;      // currently active frogs
        int maxFrogs = 0;

        for (char ch : croakOfFrogs.toCharArray()) {
            switch (ch) {
                case 'c':
                    c++;
                    frogs++;
                    maxFrogs = Math.max(maxFrogs, frogs);
                    break;
                case 'r':
                    if (c == 0) return -1;
                    c--; r++;
                    break;
                case 'o':
                    if (r == 0) return -1;
                    r--; o++;
                    break;
                case 'a':
                    if (o == 0) return -1;
                    o--; a++;
                    break;
                case 'k':
                    if (a == 0) return -1;
                    a--;
                    frogs--;   // this frog is now free
                    break;
                default:
                    return -1; // invalid character
            }
        }

        // All frogs must have finished
        return frogs == 0 ? maxFrogs : -1;
    }
}