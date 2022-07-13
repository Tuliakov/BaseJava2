package com.urise.webapp;

public class MainString {
  public static void main(String[] args) {
    String[] strArray = new String[]{"1", "2", "3", "4", "5"};
    StringBuilder sb = new StringBuilder();
    for (String str : strArray){
      sb.append(str).append(", ");
    }
    System.out.println(sb.toString());

    String str1 = "str";
    String str3 = "r";
    String str2 = ("st" + str3).intern();
    System.out.println(str1 == str2);
  }
}
