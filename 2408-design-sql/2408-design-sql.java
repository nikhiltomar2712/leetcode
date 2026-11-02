import java.util.*;

class SQL {
    // Table name -> (rowId -> row data)
    private Map<String, Map<Integer, List<String>>> tables;
    // Table name -> next auto-increment ID
    private Map<String, Integer> nextId;
    // Table name -> number of columns
    private Map<String, Integer> columnCount;

    public SQL(List<String> names, List<Integer> columns) {
        tables = new HashMap<>();
        nextId = new HashMap<>();
        columnCount = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            tables.put(name, new HashMap<>());
            nextId.put(name, 1);          // IDs start from 1
            columnCount.put(name, columns.get(i));
        }
    }

    public boolean ins(String name, List<String> row) {
        // Table does not exist, or row length does not match column count
        if (!tables.containsKey(name) || row.size() != columnCount.get(name)) {
            return false;
        }
        int id = nextId.get(name);
        tables.get(name).put(id, row);
        nextId.put(name, id + 1);
        return true;
    }

    public void rmv(String name, int rowId) {
        if (tables.containsKey(name)) {
            tables.get(name).remove(rowId);
        }
    }

    public String sel(String name, int rowId, int columnId) {
        if (!tables.containsKey(name)) {
            return "<null>";
        }
        Map<Integer, List<String>> table = tables.get(name);
        if (!table.containsKey(rowId)) {
            return "<null>";
        }
        List<String> row = table.get(rowId);
        // columnId is 1-indexed
        if (columnId < 1 || columnId > row.size()) {
            return "<null>";
        }
        return row.get(columnId - 1);
    }

    public List<String> exp(String name) {
        List<String> result = new ArrayList<>();
        if (!tables.containsKey(name)) {
            return result; // Return empty array
        }
        Map<Integer, List<String>> table = tables.get(name);
        // Sort by rowId to ensure stable export order
        List<Integer> ids = new ArrayList<>(table.keySet());
        Collections.sort(ids);
        for (int id : ids) {
            StringBuilder sb = new StringBuilder();
            sb.append(id);
            for (String cell : table.get(id)) {
                sb.append(",").append(cell);
            }
            result.add(sb.toString());
        }
        return result;
    }
}