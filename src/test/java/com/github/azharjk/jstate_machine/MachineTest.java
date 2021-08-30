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
  public void shouldHandleEndOfString() {
    // test 1
    ArrayList<String> resultToTest_1 = this.createMachineAndGetSignature("hello");
    Assert.assertEquals(1, resultToTest_1.size());
    Assert.assertEquals("hello", resultToTest_1.get(0));

    // test 2
    ArrayList<String> resultToTest_2 = this.createMachineAndGetSignature("covid-");
    Assert.assertEquals(1, resultToTest_2.size());
    Assert.assertEquals("covid-", resultToTest_2.get(0));
 }

  @Test
  public void shouldHandleArrowSymbol() {
    // test 1
    ArrayList<String> resultToTest_1 = this.createMachineAndGetSignature("covid->19");
    ArrayList<String> expectedResultTest_1 = this.createExpectedResultSignature("covid", "19");
    int size_1 = resultToTest_1.size();

    Assert.assertEquals(2, size_1);
    this.assertMultipleSignature(size_1, expectedResultTest_1, resultToTest_1);

    // test 2
    ArrayList<String> resultToTest_2 = this.createMachineAndGetSignature("covid->19->bad");
    ArrayList<String> expectedResultTest_2 = this.createExpectedResultSignature("covid", "19", "bad");
    int size_2 = resultToTest_2.size();

    Assert.assertEquals(3, size_2);
    this.assertMultipleSignature(size_2, expectedResultTest_2, resultToTest_2);

    // test 3
    ArrayList<String> resultToTest_3 = this.createMachineAndGetSignature("covid-19->bad");
    ArrayList<String> expectedResultTest_3 = this.createExpectedResultSignature("covid-19", "bad");
    int size_3 = resultToTest_3.size();

    Assert.assertEquals(2, size_3);
    this.assertMultipleSignature(size_3, expectedResultTest_3, resultToTest_3);
  }

  @Test
  public void shouldHandleSpecialCase() {
    // test 1
    ArrayList<String> resultToTest_1 = this.createMachineAndGetSignature("");
    Assert.assertEquals(1, resultToTest_1.size());
    Assert.assertEquals("", resultToTest_1.get(0));

    // test 2
    ArrayList<String> resultToTest_2 = this.createMachineAndGetSignature("covid->");
    ArrayList<String> expectedResultTest_2 = this.createExpectedResultSignature("covid", "");
    int size_2 = resultToTest_2.size();

    Assert.assertEquals(2, size_2);
    this.assertMultipleSignature(size_2, expectedResultTest_2, resultToTest_2);

    // test 3
    ArrayList<String> resultToTest_3 = this.createMachineAndGetSignature("->");
    ArrayList<String> expectedResultTest_3 = this.createExpectedResultSignature("", "");
    int size_3 = resultToTest_3.size();

    Assert.assertEquals(2, size_3);
    this.assertMultipleSignature(size_3, expectedResultTest_3, resultToTest_3);

    // test 4
    ArrayList<String> resultToTest_4 = this.createMachineAndGetSignature("->covid");
    ArrayList<String> expectedResultTest_4 = this.createExpectedResultSignature("", "covid");
    int size_4 = resultToTest_4.size();

    Assert.assertEquals(2, size_4);
    this.assertMultipleSignature(size_4, expectedResultTest_4, resultToTest_4);
  }
}
