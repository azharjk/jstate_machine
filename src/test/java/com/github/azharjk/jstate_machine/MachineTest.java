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

    Machine m2 = new Machine("");
    ArrayList<String> resultToTest_2 = m2.getSignature();

    Assert.assertEquals(1, resultToTest_2.size());
    Assert.assertEquals("", resultToTest_2.get(0));
  }
}
