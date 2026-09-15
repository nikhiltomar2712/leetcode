class Solution {
    public String entityParser(String text) {
        Map<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");
        map.put("&apos;", "'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int n = text.length();

        while (i < n) {
            if (text.charAt(i) == '&') {
                // find the next ';' within a reasonable range
                int semi = text.indexOf(';', i);
                boolean matched = false;

                if (semi != -1) {
                    String candidate = text.substring(i, semi + 1);
                    if (map.containsKey(candidate)) {
                        sb.append(map.get(candidate));
                        i = semi + 1;
                        matched = true;
                    }
                }
                if (!matched) {
                    sb.append(text.charAt(i));
                    i++;
                }
            } else {
                sb.append(text.charAt(i));
                i++;
            }
        }

        return sb.toString();
    }
}