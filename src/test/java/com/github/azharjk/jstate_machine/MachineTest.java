package com.github.azharjk.jstate_machine;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

public class MachineTest {
  // helper method
  private ArrayList<String> createMachineAndGetSignature(String param) {
    Machine ret = new Machine(param);
    return ret.getSignature();
  }

  private ArrayList<String> createExpectedResultSignature(String... params) {
    ArrayList<String> ret = new ArrayList<String>();
    for (String param : params) {
      ret.add(param);
    }
    return ret;
  }

  private void assertMultipleSignature(int size, ArrayList<String> expected, ArrayList<String> actual) {
    for (int i = 0; i < size; i++) {
      Assert.assertEquals(expected.get(i), actual.get(i));
    }
  }

  @Test
  public void shouldHandleCommonString() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("hello");
    Assert.assertEquals(1, resultToTest.size());
    Assert.assertEquals("hello", resultToTest.get(0));
  }

  @Test
  public void shouldHandleHyphenAtEndOfString() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("covid-");
    Assert.assertEquals(1, resultToTest.size());
    Assert.assertEquals("covid-", resultToTest.get(0));
  }

  @Test
  public void shouldHandleAngleSymbolAtEndOfString() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("covid>");
    Assert.assertEquals(1, resultToTest.size());
    Assert.assertEquals("covid>", resultToTest.get(0));
  }

  @Test
  public void shouldHandleCommonBehavior() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("covid->19->bad");
    ArrayList<String> expectedResultTest = this.createExpectedResultSignature("covid", "19", "bad");
    int size = resultToTest.size();

    Assert.assertEquals(3, size);
    this.assertMultipleSignature(size, expectedResultTest, resultToTest);
  }

  @Test
  public void shouldHandleHyphenAndCommonBehavior() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("covid-19->bad");
    ArrayList<String> expectedResultTest = this.createExpectedResultSignature("covid-19", "bad");
    int size = resultToTest.size();

    Assert.assertEquals(2, size);
    this.assertMultipleSignature(size, expectedResultTest, resultToTest);
  }

  @Test
  public void shouldHandleEmptyString() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("");
    Assert.assertEquals(1, resultToTest.size());
    Assert.assertEquals("", resultToTest.get(0));
  }

  @Test
  public void shouldHandleSymbolAtEnd() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("covid->");
    ArrayList<String> expectedResultTest = this.createExpectedResultSignature("covid", "");
    int size = resultToTest.size();

    Assert.assertEquals(2, size);
    this.assertMultipleSignature(size, expectedResultTest, resultToTest);
  }

  @Test
  public void shouldHandleSymbolAtStart() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("->covid");
    ArrayList<String> expectedResultTest = this.createExpectedResultSignature("", "covid");
    int size = resultToTest.size();

    Assert.assertEquals(2, size);
    this.assertMultipleSignature(size, expectedResultTest, resultToTest);
  }

  @Test
  public void shouldHandleOnlySymbol() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("->");
    ArrayList<String> expectedResultTest = this.createExpectedResultSignature("", "");
    int size = resultToTest.size();

    Assert.assertEquals(2, size);
    this.assertMultipleSignature(size, expectedResultTest, resultToTest);
  }

  @Test
  public void shouldHandleCommonParenthesisBehavior() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("(java->machine)->jvm");
    ArrayList<String> expectedResultTest = this.createExpectedResultSignature("(java->machine)", "jvm");
    int size = resultToTest.size();

    Assert.assertEquals(2, size);
    this.assertMultipleSignature(size, expectedResultTest, resultToTest);
  }

  @Test
  public void shouldHandleParenthesisSymbolAtEnd() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("(java->machine)");
    Assert.assertEquals(1, resultToTest.size());
    Assert.assertEquals("(java->machine)", resultToTest.get(0));
  }

  @Test
  public void shouldHandleParenthesisAndArrowSymbolAtEnd() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("(java->machine)->");
    ArrayList<String> expectedResultTest = this.createExpectedResultSignature("(java->machine)", "");
    int size = resultToTest.size();

    Assert.assertEquals(2, size);
    this.assertMultipleSignature(size, expectedResultTest, resultToTest);
  }

  @Test
  public void shouldHandleParenthesisWithoutClosing() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("(java->machine->good");
    ArrayList<String> expectedResultTest = this.createExpectedResultSignature("(java", "machine", "good");
    int size = resultToTest.size();

    Assert.assertEquals(3, size);
    this.assertMultipleSignature(size, expectedResultTest, resultToTest);
  }

  @Test
  public void shouldHandleNestedParenthesisLikeUsually() {
    ArrayList<String> resultToTest = this.createMachineAndGetSignature("(java->(machine))->good");
    ArrayList<String> expectedResultTest = this.createExpectedResultSignature("(java->(machine))", "good");
    int size = resultToTest.size();

    Assert.assertEquals(2, size);
    this.assertMultipleSignature(size, expectedResultTest, resultToTest);
  }
}
