class Solution {
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        Set<String> pos = new HashSet<>(Arrays.asList(positive_feedback));
        Set<String> neg = new HashSet<>(Arrays.asList(negative_feedback));
        Map<Integer, Integer> score = new HashMap<>();
        for (int i = 0; i < student_id.length; i++) {
            int id = student_id[i];
            int s = 0;
            for (String w : report[i].split(" ")) {
                if (pos.contains(w)) s += 3;
                else if (neg.contains(w)) s -= 1;
            }
            score.put(id, s);
        }
        List<Integer> ids = new ArrayList<>(score.keySet());
        ids.sort((a, b) -> {
            int sa = score.get(a), sb = score.get(b);
            if (sa != sb) return sb - sa;
            return a - b;
        });
        return ids.subList(0, Math.min(k, ids.size()));
    }
}