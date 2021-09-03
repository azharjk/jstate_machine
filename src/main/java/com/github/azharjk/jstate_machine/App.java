package com.github.azharjk.jstate_machine;

public class App {
  public static void main(String[] args) {
    Machine scanner = new Machine("(MEMEk->asdsda)");

    System.out.println(scanner.getSignature().toString());
  }
}
