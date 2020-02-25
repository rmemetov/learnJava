package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.*;

public class DeletingGroupTest extends TestBase {

  @Test
  public void testDeletingGroup() throws Exception {
    app.goToGroupPage();
    app.selectGroup();
    app.deleteGroup();
    app.goToGroupPage();
  }

}
