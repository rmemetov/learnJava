package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.fpt.addressbook.model.GroupData;

public class DeletingGroupTest extends TestBase {

  @Test
  public void testDeletingGroup() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test2", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
    app.getNavigationHelper().goToGroupPage();
  }

}
