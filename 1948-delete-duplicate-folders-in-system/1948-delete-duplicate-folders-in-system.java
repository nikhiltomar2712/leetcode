class Solution {
    class Node {
        String name;
        TreeMap<String, Node> children = new TreeMap<>();
        String key = "";
        boolean deleted = false;
        Node(String name) {
            this.name = name;
        }
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Node root = new Node("");
        for (List<String> path : paths) {
            Node cur = root;
            for (String folder : path) {
                cur.children.putIfAbsent(folder, new Node(folder));
                cur = cur.children.get(folder);
            }
        }
        Map<String, List<Node>> map = new HashMap<>();
        encode(root, map);
        for (List<Node> list : map.values()) {
            if (list.size() > 1) {
                for (Node node : list) {
                    node.deleted = true;
                }
            }
        }
        List<List<String>> res = new ArrayList<>();
        collect(root, new ArrayList<>(), res);
        return res;
    }

    private String encode(Node node, Map<String, List<Node>> map) {
        if (node.children.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Node child : node.children.values()) {
            sb.append("(").append(child.name).append(encode(child, map)).append(")");
        }
        String key = sb.toString();
        node.key = key;
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(node);
        return key;
    }

    private void collect(Node node, List<String> path, List<List<String>> res) {
        for (Node child : node.children.values()) {
            if (child.deleted) continue;
            path.add(child.name);
            res.add(new ArrayList<>(path));
            collect(child, path, res);
            path.remove(path.size() - 1);
        }
    }
}