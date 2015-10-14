package org.checkout.shoppingcart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rule {

    private final Map<Character, Integer> productMapping;
    private final double price;
    private final int numberProducts;

    public Rule(List<Character> products, double price) {
        numberProducts = products.size();
        productMapping = new HashMap<>();

        for (Character product : products) {
            if (!productMapping.containsKey(product)) {
                productMapping.put(product, 1);
            } else {
                productMapping.put(product, productMapping.get(product) + 1);
            }
        }

        this.price = price;
    }

    public boolean matches(Map<Character, Integer> products) {
        boolean matches = true;

        for (Character product : productMapping.keySet()) {
            if (!products.containsKey(product) || (products.get(product) < productMapping.get(product))) {
                matches = false;
                break;
            }
        }

        return matches;
    }

    public double removeAndGetPrice(Map<Character, Integer> products) {
        for (Character product : productMapping.keySet()) {
            int updatedValue = products.get(product) - productMapping.get(product);

            if (updatedValue > 0) {
                products.put(product, updatedValue);
            } else {
                products.remove(product);
            }
        }

        return price;
    }

    public int getNumberProducts() {
        return numberProducts;
    }
}