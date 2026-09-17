class BrowserHistory {
    private List<String> history;
    private int current;
    private int size;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        current = 0;
        size = 1;
    }

    public void visit(String url) {
        current++;
        if (current < history.size()) {
            history.set(current, url);
        } else {
            history.add(url);
        }
        size = current + 1;
    }

    public String back(int steps) {
        current = Math.max(0, current - steps);
        return history.get(current);
    }

    public String forward(int steps) {
        current = Math.min(size - 1, current + steps);
        return history.get(current);
    }
}