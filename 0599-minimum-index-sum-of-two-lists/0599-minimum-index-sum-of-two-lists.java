class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        java.util.Map<String, Integer> map = new java.util.HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        
        int minSum = Integer.MAX_VALUE;
        java.util.List<String> result = new java.util.ArrayList<>();
        
        for (int j = 0; j < list2.length; j++) {
            String s = list2[j];
            if (map.containsKey(s)) {
                int sum = map.get(s) + j;
                if (sum < minSum) {
                    minSum = sum;
                    result.clear();
                    result.add(s);
                } else if (sum == minSum) {
                    result.add(s);
                }
            }
        }
        
        return result.toArray(new String[0]);
    }
}