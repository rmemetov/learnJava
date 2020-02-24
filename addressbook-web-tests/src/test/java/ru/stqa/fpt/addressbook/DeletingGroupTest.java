package ru.stqa.fpt.addressbook;

import org.testng.annotations.*;

public class DeletingGroupTest extends TestBase {

  @Test
  public void testDeletingGroup() throws Exception {
    goToGroupPage();
    selectGroup();
    deleteGroup();
    goToGroupPage();
  }

}
