package demo.testDemo;

import static org.junit.jupiter.api.Assertions.*;

import demo.core.Deliverer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class DelivererTest {
    private ArrayList<Deliverer> deliverers;

    @BeforeEach
    public void setUp() {
        deliverers = new ArrayList<>();
    }
    
    
    @Test
    public void testGetInfo() {
        Deliverer deliverer = new Deliverer("John");
        String info = deliverer.getInfo();
        
        assertEquals("John ", info);
    }
    
    @Test
    public void testInputDelivererInfo() throws IOException {
        String xmlFilePath = "src/demo/core/testcase/deliverers.xml";
        deliverers = Deliverer.inputDelivererInfo(xmlFilePath);
        
        assertFalse(deliverers.isEmpty());
        
        for (Deliverer deliverer : deliverers) {
            assertNotNull(deliverer.getName());
            assertFalse(deliverer.getName().isEmpty());
        }
    }
    
    @Test
    public void testNoInputDelivererInfo() throws IOException {
        String xmlFilePath = "src/demo/core/testcase/deliverersNoInput.xml";
        deliverers = Deliverer.inputDelivererInfo(xmlFilePath);

        assertTrue(deliverers.isEmpty());
    }
    
    @Test
    public void testCompareTo() {
        Deliverer deliverer1 = new Deliverer("John");
        Deliverer deliverer2 = new Deliverer("Alice");
        Deliverer deliverer3 = new Deliverer("Bob");
        
        deliverer1.setAvailableTime(LocalTime.of(9, 0));
        deliverer2.setAvailableTime(LocalTime.of(10, 0));
        deliverer3.setAvailableTime(LocalTime.of(11, 0));
        
        ArrayList<Deliverer> sortedDeliverers = new ArrayList<>();
        sortedDeliverers.add(deliverer1);
        sortedDeliverers.add(deliverer2);
        sortedDeliverers.add(deliverer3);
        sortedDeliverers.sort(Deliverer::compareTo);
        
        assertEquals(sortedDeliverers.get(0), deliverer1);
        assertEquals(sortedDeliverers.get(1), deliverer2);
        assertEquals(sortedDeliverers.get(2), deliverer3);
    }
    
}