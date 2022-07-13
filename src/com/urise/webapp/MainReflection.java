package com.urise.webapp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.urise.webapp.model.Resume;

public class MainReflection {
  public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Resume r = new Resume();
    Class <? extends Resume > resumeClass = r.getClass();
    Field field = r.getClass().getDeclaredFields()[0];
    field.setAccessible(true);
    System.out.println(field.getName());
    System.out.println(field.get(r));
    field.set(r, "new_uuid");
    System.out.println(r);

    Method method = resumeClass.getMethod("toString");
    Object result = method.invoke(r);
    System.out.println(result);
  }
}
