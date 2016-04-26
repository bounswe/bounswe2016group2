/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tv
 */
public class TestModelTest {
    
    public TestModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
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
