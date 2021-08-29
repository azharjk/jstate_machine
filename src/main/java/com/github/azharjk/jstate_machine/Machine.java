package com.github.azharjk.jstate_machine;

import java.util.ArrayList;
import java.util.Objects;

public class Machine {
  private String content;
  private ArrayList<String> signature;

  private int cursor = 0;
  private int size;

  public Machine(String content) {
    this.content = Objects.requireNonNull(content);
    this.signature = new ArrayList<String>();
    this.size = this.content.length();

    this.parse();
  }

  public ArrayList<String> getSignature() {
    return this.signature;
  }

  // misc method
  private char peek() {
    return this.content.charAt(cursor);
  }

  private void next() {
    if (!(this.cursor >= this.size - 1)) {
      this.cursor++;
    }
  }

  private void parse() {
    this.peek();
    this.next();
  }
}
