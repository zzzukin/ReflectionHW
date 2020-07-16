/**
      * Scans object from for all getters. If object to
      * contains correspondent setter, it will invoke it
      * to set property value for to which equals to the property
      * of from.
      *
      * The type in setter should be compatible to the value returned
      * by getter (if not, no invocation performed).
      * Compatible means that parameter type in setter should
      * be the same or be superclass of the return type of the getter.
      *
      * The method takes care only about public methods.
      *
      * @param to   Object which properties will be set.
      * @param from Object which properties will be used to get values.
      */

package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
	    From from = new From();
	    To to = new To();

//        System.out.println(from.getField1());
//        System.out.println(from.getField2());
//
//        System.out.println(to.getField1());
//        System.out.println(to.getField2());

        ReflectionChecker checker = new ReflectionChecker();

//        checker.showClassName(from);
//        checker.showClassName(to);

        List <Method> settersTo = checker.getAllSetters(to);
        List <Method> gettersFrom = checker.getAllGetters(from);

        for (Method setterTo : settersTo){
            System.out.println("setter 'to': " + Modifier.toString(setterTo.getModifiers()) + " " + setterTo.getName());
            if(Modifier.toString(setterTo.getModifiers()).equals("private")) continue;

            for (Method getterFrom : gettersFrom){
                System.out.println("getter 'from': " + Modifier.toString(getterFrom.getModifiers()) + " " + getterFrom.getName());
                if(Modifier.toString(getterFrom.getModifiers()).equals("private")) continue;
                if(setterTo.getName().contains(getterFrom.getName().replace('g', 's'))){

                    Parameter[] setterToParams = setterTo.getParameters();

                    if(setterToParams.length == 1){
                        if (getterFrom.getReturnType().equals(setterToParams[0].getType())){
                            System.out.println("setter parametr type: " + setterToParams[0].getType());
                            System.out.println("getter returned type: " + getterFrom.getReturnType());
                            setterTo.invoke(to, getterFrom.invoke(from));
                            break;
                        }
                    }
                }
            }
            System.out.println("----------------------------");
        }
    }
}
