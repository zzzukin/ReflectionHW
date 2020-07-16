package com.company;

public class To {
    private String Field1 = "this is field1 'to' ";
    private String Field2 = "this is field2 'to' ";

    public String getField1() {
        return Field1;
    }

    public String getField2() {
        return Field2;
    }

    public void setField1(String field1) {
        Field1 = Field1 + "" + field1;
        System.out.println(Field1);
    }

    public void setField2(String field2) {
        Field2 = Field2 + "" + field2;
        System.out.println(Field2);
    }
}
