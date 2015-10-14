package org.checkout.shoppingcart;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SmokeTest {
    
    private static final List<Rule> RULES = getRules(); 

    private int price(String products) {
        final CheckOut checkOut = new CheckOut(RULES);

        products.chars().forEach((product) -> checkOut.scan((char) product));

        return checkOut.getTotal();
    }

    @Test
    public void testTesting() {
        assertThat("This String").isNotEmpty();
    }

    @Test
    public void testTotal() {
        assertThat(price("")).isEqualTo(0);
        assertThat(price("i")).isEqualTo(549);
        assertThat(price("ii")).isEqualTo(499);
        assertThat(price("aaav")).isEqualTo(249);

        assertThat(price("aiiaiii")).isEqualTo(2718);
        assertThat(price("mvi")).isEqualTo(1949);
        
    }

    @Test
    public void testIncrementalTotal() {
        final CheckOut checkOut = new CheckOut(RULES);
        assertThat(checkOut.getTotal()).isEqualTo(0);
        checkOut.scan('i');
        assertThat(checkOut.getTotal()).isEqualTo(549);
        checkOut.scan('i');
        assertThat(checkOut.getTotal()).isEqualTo(499);
        
    }

    public static List<Rule> getRules() {
        List<Rule> rules = new ArrayList<>();
/*
 * i - ipad
 * m - macbook pro
 * a - apple tv
 * v - VGA
 */
        rules.add(new Rule(getList('i'), 549));
        rules.add(new Rule(getList('m'), 1399));
        rules.add(new Rule(getList('a'), 109));
        rules.add(new Rule(getList('v'), 30));
        rules.add(new Rule(getList('i','i'),499));
        rules.add(new Rule(getList('a', 'a', 'a','v'), 249));
        rules.add(new Rule(getList('a', 'i', 'i','a','i','i','i'), 2718));
        rules.add(new Rule(getList('m', 'v','i'), 1949));

        return rules;
    }

    private static List<Character> getList(Character... characters) {
        List<Character> characterList = new ArrayList<>();
        Collections.addAll(characterList, characters);

        return characterList;
    }
}