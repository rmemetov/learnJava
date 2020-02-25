package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.fpt.addressbook.model.GroupData;


public class GroupCreate extends TestBase {


    @Test
    public void testGroupCreate() throws Exception {
      app.goToGroupPage();
      app.initGroupCreation();
      app.fillGroupForm(new GroupData("test2", "test", "test"));
      app.submitGroupCreation();
      app.goToGroupPage();
      app.logoutToWelcomePage();
    }

}
