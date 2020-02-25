package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.*;

public class DeletingGroupTest extends TestBase {

  @Test
  public void testDeletingGroup() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
    app.getNavigationHelper().goToGroupPage();
  }

}
