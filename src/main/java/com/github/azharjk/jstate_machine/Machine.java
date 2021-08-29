package com.github.azharjk.jstate_machine;

import java.util.ArrayList;
import java.util.Objects;

public class Machine {
  private String content;
  private ArrayList<String> signature;

  private int size;
  private int cursor = 0;
  private StateType state = StateType.IDLE;

  enum StateType {
    IDLE,
    EOS
  }

  public Machine(String content) {
    this.content = Objects.requireNonNull(content);
    this.signature = new ArrayList<String>();
    this.size = this.content.length();

    this.parse();
  }

  public ArrayList<String> getSignature() {
    return this.signature;
  }

  private void parse() {
    String tmp = "";

    // check if content is empty string
    if (this.size == 0) {
      this.signature.add(tmp);
      return;
    }

    while (true) {
      switch (this.state) {
      case IDLE: {
        // check for eos
        if (this.cursor == this.size - 1) {
          tmp += this.peek();
          this.state = StateType.EOS;
        }
        else {
          tmp += this.peek();
          this.next();
        }
        break;
      }
      case EOS: {
        this.signature.add(tmp);
        return;
      } // switch
      }
    }
  }

  // helper method
  private char peek() {
    return this.content.charAt(cursor);
  }

  private void next() {
    if (!(this.cursor >= this.size - 1)) {
      this.cursor++;
    }
  }
}
