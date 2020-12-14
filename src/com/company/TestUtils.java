package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class TestUtils {
    private static Object obj;

    public static void start(Class className){

    if (checkAmountOfBeforeAndAfter(className)){
        throw new RuntimeException();
    }

    try {
        obj = className.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        e.printStackTrace();
    }


    TreeMap<Integer, Method> priorityOfMethods = new TreeMap<>();
    Method[] methods = className.getDeclaredMethods();

    for (Method method : methods) {
        if (method.getAnnotation(BeforeSuite.class) != null) {
            priorityOfMethods.put(0, method);
        }
        if (method.getAnnotation(AfterSuite.class) != null) {
            priorityOfMethods.put(11, method);
        }
        if (method.getAnnotation(Test.class) != null) {
            priorityOfMethods.put(method.getAnnotation(Test.class).priority(), method);
        }
    }

        for(Map.Entry entry: priorityOfMethods.entrySet()) {
            try {
                Method value = (Method) entry.getValue();
                value.invoke(obj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

}

    private static boolean checkAmountOfBeforeAndAfter(Class className) {
        int before = 0;
        int after = 0;

        for (Method method : className.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                before++;
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                after++;
            }
        }

        return (before > 1) || (after > 1);
    }
}
