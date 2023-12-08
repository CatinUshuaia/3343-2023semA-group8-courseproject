package demo.testDemo;

import static org.junit.jupiter.api.Assertions.*;

import demo.core.Cook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class CookTest {
    private ArrayList<Cook> cooks;

    @BeforeEach
    public void setup() {
        cooks = new ArrayList<>();
        cooks.add(new Cook(new String[]{"Italian", "Chinese"}, "Cook 1", 1, 1));
        cooks.add(new Cook(new String[]{"Indian"}, "Cook 2", 2, 2));
        cooks.add(new Cook(new String[]{"Mexican"}, "Cook 3", 3, 3));
    }

    @Test
    public void testCookToString() {
        Cook cook = new Cook(new String[]{"Italian", "Chinese"}, "Cook 1", 1, 1);
        assertEquals("Cook 1", cook.toString());
    }

    @Test
    public void testCookGetInfo() {
        Cook cook = new Cook(new String[]{"Italian", "Chinese"}, "Cook 1", 1, 1);
        assertEquals("Cook 1 [Italian, Chinese] 1", cook.getInfo());
    }

    @Test
    public void testInputCookInfo() throws IOException {
        ArrayList<Cook> cooks = Cook.inputCookInfo("src/demo/core/testcase/cook.txt");
        assertEquals(3, cooks.size());
        assertEquals("Joy", cooks.get(0).toString());
        assertEquals("Tom", cooks.get(1).toString());
        assertEquals("Sandy", cooks.get(2).toString());
    }

    @Test
    public void testCookCompareTo() {
        Cook cook1 = new Cook(new String[]{"Italian", "Chinese"}, "Cook 1", 1, 1);
        Cook cook2 = new Cook(new String[]{"Indian"}, "Cook 2", 2, 2);
        Cook cook3 = new Cook(new String[]{"Mexican"}, "Cook 3", 3, 3);

        cook1.initializeAvailableTime(LocalTime.of(9, 30));
        cook2.initializeAvailableTime(LocalTime.of(9, 30));
        cook3.initializeAvailableTime(LocalTime.of(11, 0));

        assertEquals(cook1.compareTo(cook2) , -1);
        assertEquals(cook2.compareTo(cook3) , -1);
        assertEquals(cook3.compareTo(cook1) , 1);
    }

    @Test
    public void testCookCookFood() {
        Cook cook = new Cook(new String[]{"Italian", "Chinese"}, "Cook 1", 1, 1);
        cook.cookFood(LocalTime.of(12, 0));
        assertEquals(LocalTime.of(12, 0), cook.getAvailableTime());
    }

    @Test
    public void testSelectCook() {
        Cook cook1 = new Cook(new String[]{"Italian", "Chinese"}, "Cook 1", 1, 1);
        Cook cook2 = new Cook(new String[]{"Indian"}, "Cook 2", 2, 2);
        Cook cook3 = new Cook(new String[]{"Mexican"}, "Cook 3", 3, 3);

        cook1.initializeAvailableTime(LocalTime.of(10, 0));
        cook2.initializeAvailableTime(LocalTime.of(12, 0));
        cook3.initializeAvailableTime(LocalTime.of(11, 0));

        ArrayList<Cook> cooks = new ArrayList<>();
        cooks.add(cook1);
        cooks.add(cook2);
        cooks.add(cook3);

        assertEquals(cook1, Cook.selectCook(cooks));
    }
}