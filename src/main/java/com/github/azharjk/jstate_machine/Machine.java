package com.github.azharjk.jstate_machine;

import java.util.ArrayList;
import java.util.Objects;

public class Machine {
  private String content;
  private String lexeme;
  private ArrayList<String> signature;

  private int size;
  private int cursor = 0;
  private StateType state = StateType.IDLE;

  enum StateType {
    IDLE,
    EOS,
    HYPHEN,
    LPAREN
  }

  public Machine(String content) {
    this.content = Objects.requireNonNull(content);
    this.lexeme = "";
    this.signature = new ArrayList<String>();
    this.size = this.content.length();

    this.parse();
  }

  public ArrayList<String> getSignature() {
    return this.signature;
  }

  private void parse() {
    // check if content is empty string
    // special case
    if (this.size == 0) {
      this.signature.add(this.lexeme);
      return;
    }

    while (true) {
      switch (this.state) {
      case IDLE: {
        // check for eos
        if (this.cursor == this.size - 1) {
          // special case
          if (!((this.content.charAt(this.size - 1) == '>') && (this.content.charAt(this.size - 2) == '-'))) {
            this.consume();
          }
          this.state = StateType.EOS;
        }
        // check for hyphen
        else if (this.peek() == '-') {
          this.consumeTo(StateType.HYPHEN);
        }
        // check for lparen
        else if (this.peek() == '(' && this.content.indexOf(')', this.cursor) != -1) {
          this.consumeTo(StateType.LPAREN);
        }
        else {
          this.consumeTo(this.state);
        }
        break;
      }
      case EOS: {
        this.signature.add(this.lexeme);
        return;
      }
      case HYPHEN: {
        // check for arrow
        if (this.peek() == '>') {
          this.signature.add(this.lexeme.substring(0, this.lexeme.length() - 1));
          this.lexeme = "";
          this.state = StateType.IDLE;
          this.next();
        }
        else {
          this.consumeTo(StateType.IDLE);
        }
        break;
      }
      case LPAREN: {
        // check for rparen
        if (this.peek() == ')') {
          this.consume();
          if (this.cursor == this.size - 1) {
            this.state = StateType.EOS;
          }
          else {
            this.state = StateType.IDLE;
            this.next();
          }
        }
        else {
          this.consumeTo(this.state);
        }
        break;
      }
      } // switch
    }
  }

  // helper method
  private char peek() {
    return this.content.charAt(this.cursor);
  }

  private void next() {
    if (!(this.cursor >= this.size - 1)) {
      this.cursor++;
    }
  }

  private void consume() {
    this.lexeme += this.peek();
  }

  private void consumeTo(StateType newState) {
    this.consume();
    this.state = newState;
    this.next();
  }
}
