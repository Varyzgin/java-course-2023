package edu.hw3;

import edu.hw3.task1.Encryptor;
import edu.hw3.task2.Interpreter;
import edu.hw3.task3.Dictionary;
import edu.hw3.task4.Digits;
import edu.hw3.task5.Contacts;
import edu.hw3.task5.Person;
import edu.hw3.task6.Market;
import edu.hw3.task6.Stock;
import edu.hw3.task7.Comparators;
import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HW3Test {
    @Test
    @DisplayName("1. Шифр Атбаш")
    void atbash() {
        // given
        String str =
            "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        // when
        String ans =
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";
        String res = Encryptor.atbash(str);
        // then
        assertThat(res).isEqualTo(ans);
    }

    @Test
    @DisplayName("2. Кластеризация скобок")
    void cluster1() throws Exception {
        // given
        String str = "((())())(()(()()))";
        // when
        ArrayList<String> ans = new ArrayList<>();
        ans.add("((())())");
        ans.add("(()(()()))");
        ArrayList<String> res = Interpreter.clusterize(str);
        // then
        assertThat(res).isEqualTo(ans);
    }

    @Test
    @DisplayName("3. Частота слов")
    void dictionary() throws Exception {
        // given
        List<Object> input = List.of("Cat", "Dog", "Bird", "Cat", "Spider");
        // when
        HashMap<Object, Integer> ans = new HashMap<>();
        ans.put("Cat", 2);
        ans.put("Dog", 1);
        ans.put("Bird", 1);
        ans.put("Spider", 1);
        HashMap<Object, Integer> res = Dictionary.freqDict(input);
        // then
        assertEquals(res, ans);
    }

    @Test
    @DisplayName("4. Римские цифры")
    void roman() throws Exception {
        // given
        int num = 3128;
        // when
        String ans = "MMMCXXVIII";
        String res = Digits.convertToRoman(num);
        // then
        assertThat(res).isEqualTo(ans);
    }

    @Test
    @DisplayName("5. Список контактов")
    void contacts() throws Exception {
        // given
        List<String> contacts = List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss", "Mister Who");
        // when
        List<Person> ans = List.of(
            new Person("Mister", "Who"),
            new Person("Carl", "Gauss"),
            new Person("Leonhard", "Euler"),
            new Person("Paul", "Erdos")
        );
        List<Person> res = Contacts.parseContacts(contacts, "DESC");
        // then
        for (int i = 0; i < 4; i++) {
            assertEquals(res.get(i), ans.get(i));
        }
    }

    @Test
    @DisplayName("6. Биржа")
    void stock_market() {
        // given
        Market market = new Market();
        market.add(new Stock("Huawei", 1000));
        market.add(new Stock("Xiaomi", 1000));
        market.add(new Stock("Apple", 3000));
        market.add(new Stock("Samsung", 2000));
        // when
        Stock ans = new Stock("Apple", 3000);
        Stock res = market.mostValuableStock();
        // then
        assertEquals(res, ans);
    }

    @Test
    @DisplayName("7. Дерево и null")
    void tree_and_null() {
        TreeMap<String, String> tree = new TreeMap<>(Comparators.nullHandlingComparator);
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    @DisplayName("8. Обратный итератор")
    void backward_iterator() {
        List<Integer> list = List.of(1, 2, 3);

        Iterator<Object> iterator = new BackwardIterator<>(list.toArray());
        int list_el = 3;
        while (iterator.hasNext()) {
            assertEquals(list_el, iterator.next());
            list_el--;
        }
    }
}
