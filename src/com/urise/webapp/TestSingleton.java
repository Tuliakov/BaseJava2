package com.urise.webapp;

import com.urise.webapp.model.SectionType;

public class TestSingleton {

  private static TestSingleton ourInstance = new TestSingleton();

  public static TestSingleton getInstance() {
    if (ourInstance == null) {
      ourInstance = new TestSingleton();
    }
    return ourInstance;
  }

  private TestSingleton() {
  }

  public static void main(String[] args) {
    TestSingleton.getInstance().toString();
    Singleton instance = Singleton.valueOf("INSTANCE");
    System.out.println(instance.ordinal());

    for (SectionType type : SectionType.values()) {
      System.out.println(type.getTitle());
    }
  }

  public enum Singleton{
    INSTANCE
  }
}
