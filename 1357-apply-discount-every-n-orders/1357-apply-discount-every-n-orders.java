import java.util.HashMap;
import java.util.Map;

class Cashier {
    private int n;
    private int discount;
    private Map<Integer, Integer> priceMap;
    private int customerCount;

    public Cashier(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discount = discount;
        this.priceMap = new HashMap<>();
        this.customerCount = 0;

        for (int i = 0; i < products.length; i++) {
            priceMap.put(products[i], prices[i]);
        }
    }

    public double getBill(int[] product, int[] amount) {
        customerCount++;

        // Calculate the total bill
        double total = 0.0;
        for (int i = 0; i < product.length; i++) {
            total += (double) priceMap.get(product[i]) * amount[i];
        }

        // Apply discount every n-th customer
        if (customerCount % n == 0) {
            total = total - (total * discount / 100.0);
        }

        return total;
    }
}