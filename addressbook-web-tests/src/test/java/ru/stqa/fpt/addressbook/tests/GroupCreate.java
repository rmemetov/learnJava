package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.fpt.addressbook.model.GroupData;


public class GroupCreate extends TestBase {


    @Test
    public void testGroupCreate() throws Exception {
      app.getNavigationHelper().goToGroupPage();
      app.getGroupHelper().initGroupCreation();
      app.getGroupHelper().fillGroupForm(new GroupData("test2", "test", "test"));
      app.getGroupHelper().submitGroupCreation();
      app.getNavigationHelper().goToGroupPage();
      app.logoutToWelcomePage();
    }

}
