/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kagan Sari
 */
public class TestModelTest {
    
    public TestModelTest() {
    }

    /**
     * Test of getTestField method, of class TestModel.
     */
    @Test
    public void testGetTestField() {
        System.out.println("getTestField");
        TestModel instance = new TestModel("test string");
        String expResult = "test string";
        String result = instance.getTestField();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTestField method, of class TestModel.
     */
    @Test
    public void testSetTestField() {
        System.out.println("setTestField");
        String testField = "test string";
        TestModel instance = new TestModel("");
        instance.setTestField(testField);
        assertEquals(testField, instance.getTestField());
    }
    
}
