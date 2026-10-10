class TextEditor {
    private StringBuilder left;
    private StringBuilder right;

    public TextEditor() {
        left = new StringBuilder();
        right = new StringBuilder();
    }

    public void addText(String text) {
        left.append(text);
    }

    public int deleteText(int k) {
        int len = Math.min(k, left.length());
        left.setLength(left.length() - len);
        return len;
    }

    public String cursorLeft(int k) {
        int len = Math.min(k, left.length());
        for (int i = 0; i < len; i++) {
            right.append(left.charAt(left.length() - 1));
            left.setLength(left.length() - 1);
        }
        return getLeft();
    }

    public String cursorRight(int k) {
        int len = Math.min(k, right.length());
        for (int i = 0; i < len; i++) {
            left.append(right.charAt(right.length() - 1));
            right.setLength(right.length() - 1);
        }
        return getLeft();
    }

    private String getLeft() {
        int len = Math.min(10, left.length());
        return left.substring(left.length() - len);
    }
}