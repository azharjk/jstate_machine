package com.github.azharjk.jstate_machine;

public class App {
  public static void main(String[] args) {
    Machine scanner = new Machine("covid->");

    for (String signature : scanner.getSignature()) {
      System.out.println(signature);
    }
  }
}
