package com.github.azharjk.jstate_machine;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

public class MachineTest {
  @Test
  public void shouldHandleEndOfString() {
    Machine m1 = new Machine("hello");
    ArrayList<String> resultToTest_1 = m1.getSignature();

    Assert.assertEquals(1, resultToTest_1.size());
    Assert.assertEquals("hello", resultToTest_1.get(0));

    Machine m2 = new Machine("covid-");
    ArrayList<String> resultToTest_2 = m2.getSignature();

    Assert.assertEquals(1, resultToTest_2.size());
    Assert.assertEquals("covid-", resultToTest_2.get(0));
 }

  @Test
  public void shouldHandleArrowSymbol() {
    Machine m1 = new Machine("covid->19");
    ArrayList<String> resultToTest_1 = m1.getSignature();
    int size_1 = resultToTest_1.size();

    ArrayList<String> parsedResultTest_1 = new ArrayList<String>();
    parsedResultTest_1.add("covid");
    parsedResultTest_1.add("19");

    Assert.assertEquals(2, size_1);
    for (int i = 0; i < size_1; i++) {
      Assert.assertEquals(parsedResultTest_1.get(i), resultToTest_1.get(i));
    }

    Machine m2 = new Machine("covid->19->bad");
    ArrayList<String> resultToTest_2 = m2.getSignature();
    int size_2 = resultToTest_2.size();

    ArrayList<String> parsedResultTest_2 = new ArrayList<String>();
    parsedResultTest_2.add("covid");
    parsedResultTest_2.add("19");
    parsedResultTest_2.add("bad");

    Assert.assertEquals(3, size_2);
    for (int i = 0; i < size_2; i++) {
      Assert.assertEquals(parsedResultTest_2.get(i), resultToTest_2.get(i));
    }

    Machine m3 = new Machine("covid-19->bad");
    ArrayList<String> resultToTest_3 = m3.getSignature();
    int size_3 = resultToTest_3.size();

    ArrayList<String> parsedResultTest_3 = new ArrayList<String>();
    parsedResultTest_3.add("covid-19");
    parsedResultTest_3.add("bad");

    Assert.assertEquals(2, size_3);
    for (int i = 0; i < size_3; i++) {
      Assert.assertEquals(parsedResultTest_3.get(i), resultToTest_3.get(i));
    }
  }

  @Test
  public void shouldHandleSpecialCase() {
    Machine m1 = new Machine("");
    ArrayList<String> resultToTest_1 = m1.getSignature();

    Assert.assertEquals(1, resultToTest_1.size());
    Assert.assertEquals("", resultToTest_1.get(0));

    Machine m2 = new Machine("covid->");
    ArrayList<String> resultToTest_2 = m2.getSignature();
    int size_2 = resultToTest_2.size();

    ArrayList<String> parsedResultTest_2 = new ArrayList<String>();
    parsedResultTest_2.add("covid");
    parsedResultTest_2.add("");

    Assert.assertEquals(2, size_2);
    for (int i = 0; i < size_2; i++) {
      Assert.assertEquals(parsedResultTest_2.get(i), resultToTest_2.get(i));
    }

    Machine m3 = new Machine("->");
    ArrayList<String> resultToTest_3 = m3.getSignature();
    int size_3 = resultToTest_3.size();

    ArrayList<String> parsedResultTest_3 = new ArrayList<String>();
    parsedResultTest_3.add("");
    parsedResultTest_3.add("");

    Assert.assertEquals(2, size_3);
    for (int i = 0; i < size_3; i++) {
      Assert.assertEquals(parsedResultTest_3.get(i), resultToTest_3.get(i));
    }

    Machine m4 = new Machine("->covid");
    ArrayList<String> resultToTest_4 = m4.getSignature();
    int size_4 = resultToTest_4.size();

    ArrayList<String> parsedResultTest_4 = new ArrayList<String>();
    parsedResultTest_4.add("");
    parsedResultTest_4.add("covid");

    Assert.assertEquals(2, size_4);
    for (int i = 0; i < size_4; i++) {
      Assert.assertEquals(parsedResultTest_4.get(i), resultToTest_4.get(i));
    }
  }
}
