package com.company;

public class TestExample {

    @BeforeSuite()
    public void Before(){
        System.out.println("Before");
    }

    @Test(priority = 3)
    public void Test1(){
        System.out.println("Test3");
    }

    @Test(priority = 2)
    public void Test2(){
        System.out.println("Test2");
    }

    @Test(priority = 1)
    public void Test3(){
        System.out.println("Test1");
    }

    @AfterSuite()
    public void After(){
        System.out.println("After");
    }

}
