class Solution {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> next = new HashMap<>();
        String[] result = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            String name = names[i];

            if (!next.containsKey(name)) {
                result[i] = name;
                next.put(name, 1);
            } else {
                int k = next.get(name);
                String candidate = name + "(" + k + ")";

                while (next.containsKey(candidate)) {
                    k++;
                    candidate = name + "(" + k + ")";
                }

                result[i] = candidate;
                next.put(name, k + 1);      // next duplicate of `name` starts here
                next.put(candidate, 1);     // candidate itself is now taken
            }
        }
        return result;
    }
}