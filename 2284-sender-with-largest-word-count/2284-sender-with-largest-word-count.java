class Solution {
    public String largestWordCount(String[] messages, String[] senders) {
        Map<String, Integer> count = new HashMap<>();
        for (int i = 0; i < senders.length; i++) {
            int words = messages[i].split(" ").length;
            count.merge(senders[i], words, Integer::sum);
        }
        String res = "";
        int max = 0;
        for (Map.Entry<String, Integer> e : count.entrySet()) {
            if (e.getValue() > max || (e.getValue() == max && e.getKey().compareTo(res) > 0)) {
                max = e.getValue();
                res = e.getKey();
            }
        }
        return res;
    }
}