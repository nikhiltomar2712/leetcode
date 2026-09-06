class Solution {
    public List<String> invalidTransactions(String[] transactions) {
        List<String> result = new ArrayList<>();
        Map<String, List<Transaction>> nameToTrans = new HashMap<>();

        // Parse all transactions and group by name
        for (String t : transactions) {
            Transaction trans = parse(t);
            nameToTrans.computeIfAbsent(trans.name, k -> new ArrayList<>()).add(trans);
        }

        // Check each transaction
        for (String t : transactions) {
            Transaction curr = parse(t);
            boolean invalid = false;

            // Condition 1: amount > 1000
            if (curr.amount > 1000) {
                invalid = true;
            } else {
                // Condition 2: same name, different city, within 60 minutes
                for (Transaction other : nameToTrans.get(curr.name)) {
                    if (!other.city.equals(curr.city) &&
                        Math.abs(other.time - curr.time) <= 60) {
                        invalid = true;
                        break;
                    }
                }
            }

            if (invalid) {
                result.add(t);
            }
        }

        return result;
    }

    private Transaction parse(String s) {
        String[] parts = s.split(",");
        return new Transaction(
            parts[0],
            Integer.parseInt(parts[1]),
            Integer.parseInt(parts[2]),
            parts[3]
        );
    }

    private static class Transaction {
        String name;
        int time;
        int amount;
        String city;

        Transaction(String name, int time, int amount, String city) {
            this.name = name;
            this.time = time;
            this.amount = amount;
            this.city = city;
        }
    }
}