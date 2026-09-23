import java.util.*;

class ThroneInheritance {
    private String king;
    private Map<String, List<String>> children;
    private Set<String> dead;

    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.children = new HashMap<>();
        this.dead = new HashSet<>();
        children.put(kingName, new ArrayList<>());
    }

    public void birth(String parentName, String childName) {
        children.computeIfAbsent(parentName, k -> new ArrayList<>()).add(childName);
        children.putIfAbsent(childName, new ArrayList<>());
    }

    public void death(String name) {
        dead.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(king, order);
        return order;
    }

    private void dfs(String person, List<String> order) {
        if (!dead.contains(person)) {
            order.add(person);
        }
        for (String child : children.getOrDefault(person, Collections.emptyList())) {
            dfs(child, order);
        }
    }
}