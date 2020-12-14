package com.company;

public class Main {

    public static void main(String[] args) {

        try {
            TestExample testExample = new TestExample();
            TestUtils.start(testExample.getClass());
        }catch (RuntimeException e){
            e.printStackTrace();
        }


    }
}
