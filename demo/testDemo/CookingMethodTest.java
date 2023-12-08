package demo.testDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import demo.core.CookingMethod;

public class CookingMethodTest {

    private CookingMethod cookingMethod;

    @BeforeEach
    public void setUp() {
        cookingMethod = CookingMethod.BOIL;
    }

    @Test
    public void testGetOperationTime() {
        assertEquals(1, cookingMethod.getOperationTime());
    }

    @Test
    public void testToString() {
        assertEquals("boil", cookingMethod.toString());
    }

    @Test
    public void testGetWayToCook() {
        assertEquals(CookingMethod.BOIL, CookingMethod.getWayToCook("boil"));
        assertEquals(CookingMethod.ROAST, CookingMethod.getWayToCook("roast"));
        assertEquals(CookingMethod.STEAM, CookingMethod.getWayToCook("steam"));
        assertEquals(CookingMethod.FRY, CookingMethod.getWayToCook("fry"));
        assertEquals(CookingMethod.STEW, CookingMethod.getWayToCook("stew"));
        assertNull(CookingMethod.getWayToCook("grill")); // Testing for a non-existent cooking method
    }

    @Test
    public void testCustomCompareTo() {
        CookingMethod otherMethod = CookingMethod.ROAST;
        assertEquals(-1, cookingMethod.customCompareTo(otherMethod));
        assertEquals(0, cookingMethod.customCompareTo(cookingMethod));
        assertEquals(1, otherMethod.customCompareTo(cookingMethod));
    }
    
    @Test
    public void testNoSavedTimeOperation() {
        CookingMethod methodWithMaxTime = CookingMethod.BOIL;
        CookingMethod methodWithNonMaxTime = CookingMethod.FRY;
        assertEquals(methodWithMaxTime.noSavedTimeOperation(),false);
        assertEquals(methodWithNonMaxTime.noSavedTimeOperation(),true);
    }
}