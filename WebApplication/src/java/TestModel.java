/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kagan Sari
 */
public class TestModel {

    public TestModel(String testField) {
        this.testField = testField;
    }
    
    private String testField;

    /**
     * Get the value of testField
     *
     * @return the value of testField
     */
    public String getTestField() {
        return testField;
    }

    /**
     * Set the value of testField
     *
     * @param testField new value of testField
     */
    public void setTestField(String testField) {
        this.testField = testField;
    }

}
