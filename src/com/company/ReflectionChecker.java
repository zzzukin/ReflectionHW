package com.company;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class ReflectionChecker {

    void showClassName(Object object){
        Class cls = object.getClass();
        System.out.println("class name:");
        System.out.println(cls.getName());
    }

    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get"))      return false;
        if(method.getParameterTypes().length != 0)   return false;
        if(void.class.equals(method.getReturnType())) return false;
        return true;
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        if(method.getParameterTypes().length != 1) return false;
        return true;
    }

    List getAllGetters(Object object){
        Class cls = object.getClass();
        Method[] methods = cls.getDeclaredMethods();
        List getters = new LinkedList<Method>();

        for(Method method : methods){
            if(isGetter(method)) getters.add(method);
        }

        return getters;
    }

    List getAllSetters(Object object){
        Class cls = object.getClass();
        Method[] methods = cls.getDeclaredMethods();
        List setters = new LinkedList<Method>();

        for(Method method : methods){
            if(isSetter(method)) setters.add(method);
        }

        return setters;
    }
}
