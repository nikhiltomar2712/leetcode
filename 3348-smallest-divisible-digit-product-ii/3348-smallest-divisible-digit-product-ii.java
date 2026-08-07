class Solution {
    private static final Map<Integer, Map<Integer, Integer>> kFactorCounts = new HashMap<>();
    static {
        kFactorCounts.put(0, Map.of());
        kFactorCounts.put(1, Map.of());
        kFactorCounts.put(2, Map.of(2, 1));
        kFactorCounts.put(3, Map.of(3, 1));
        kFactorCounts.put(4, Map.of(2, 2));
        kFactorCounts.put(5, Map.of(5, 1));
        kFactorCounts.put(6, Map.of(2, 1, 3, 1));
        kFactorCounts.put(7, Map.of(7, 1));
        kFactorCounts.put(8, Map.of(2, 3));
        kFactorCounts.put(9, Map.of(3, 2));
    }

    public String smallestNumber(String num, long t) {
        Map<Integer, Integer> primeCount = new HashMap<>();
        primeCount.put(2, 0);
        primeCount.put(3, 0);
        primeCount.put(5, 0);
        primeCount.put(7, 0);

        for (int prime : new int[]{2, 3, 5, 7}) {
            while (t % prime == 0) {
                t /= prime;
                primeCount.put(prime, primeCount.get(prime) + 1);
            }
        }
        if (t != 1) {
            return "-1";
        }

        Map<Integer, Integer> factorCount = getFactorCount(primeCount);
        if (sumValues(factorCount) > num.length()) {
            return construct(factorCount);
        }

        Map<Integer, Integer> primeCountPrefix = getPrimeCount(num);
        int firstZeroIndex = num.indexOf('0');
        if (firstZeroIndex == -1) {
            firstZeroIndex = num.length();
            if (isSubset(primeCount, primeCountPrefix)) {
                return num;
            }
        }

        for (int i = num.length() - 1; i >= 0; --i) {
            int d = num.charAt(i) - '0';
            primeCountPrefix = subtract(primeCountPrefix, kFactorCounts.get(d));
            int spaceAfterThisDigit = num.length() - 1 - i;
            if (i > firstZeroIndex) {
                continue;
            }
            for (int biggerDigit = d + 1; biggerDigit < 10; ++biggerDigit) {
                Map<Integer, Integer> factorsAfterReplacement = getFactorCount(
                        subtract(subtract(primeCount, primeCountPrefix), kFactorCounts.get(biggerDigit)));
                if (sumValues(factorsAfterReplacement) <= spaceAfterThisDigit) {
                    int fillOnes = spaceAfterThisDigit - sumValues(factorsAfterReplacement);
                    return num.substring(0, i) +
                            biggerDigit +
                            "1".repeat(fillOnes) +
                            construct(factorsAfterReplacement);
                }
            }
        }

        Map<Integer, Integer> factorsAfterExtension = getFactorCount(primeCount);
        return "1".repeat(num.length() + 1 - sumValues(factorsAfterExtension)) +
                construct(factorsAfterExtension);
    }

    private Map<Integer, Integer> getPrimeCount(String num) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(2, 0);
        count.put(3, 0);
        count.put(5, 0);
        count.put(7, 0);
        for (char c : num.toCharArray()) {
            Map<Integer, Integer> digitFactors = kFactorCounts.get(c - '0');
            for (Map.Entry<Integer, Integer> entry : digitFactors.entrySet()) {
                count.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }
        return count;
    }

    private Map<Integer, Integer> getFactorCount(Map<Integer, Integer> count) {
        int count8 = count.get(2) / 3;
        int remaining2 = count.get(2) % 3;
        int count9 = count.get(3) / 2;
        int count3 = count.get(3) % 2;
        int count4 = remaining2 / 2;
        int count2 = remaining2 % 2;
        int count6 = 0;
        if (count2 == 1 && count3 == 1) {
            count2 = 0;
            count3 = 0;
            count6 = 1;
        }
        if (count3 == 1 && count4 == 1) {
            count2 = 1;
            count6 = 1;
            count3 = 0;
            count4 = 0;
        }
        Map<Integer, Integer> res = new HashMap<>();
        res.put(2, count2);
        res.put(3, count3);
        res.put(4, count4);
        res.put(5, count.get(5));
        res.put(6, count6);
        res.put(7, count.get(7));
        res.put(8, count8);
        res.put(9, count9);
        return res;
    }

    private String construct(Map<Integer, Integer> factors) {
        StringBuilder sb = new StringBuilder();
        for (int digit = 2; digit <= 9; ++digit) {
            sb.append(String.valueOf(digit).repeat(factors.getOrDefault(digit, 0)));
        }
        return sb.toString();
    }

    private boolean isSubset(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        for (Map.Entry<Integer, Integer> entry : a.entrySet()) {
            if (b.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    private Map<Integer, Integer> subtract(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        Map<Integer, Integer> res = new HashMap<>(a);
        for (Map.Entry<Integer, Integer> entry : b.entrySet()) {
            int key = entry.getKey();
            res.put(key, Math.max(0, res.getOrDefault(key, 0) - entry.getValue()));
        }
        return res;
    }

    private int sumValues(Map<Integer, Integer> count) {
        return count.values().stream().mapToInt(Integer::intValue).sum();
    }
}