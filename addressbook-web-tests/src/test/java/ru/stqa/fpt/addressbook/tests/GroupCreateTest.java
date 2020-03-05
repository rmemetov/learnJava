package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.fpt.addressbook.model.GroupData;


public class GroupCreateTest extends TestBase {


    @Test
    public void testGroupCreate() throws Exception {
      app.getNavigationHelper().goToGroupPage();
      app.getGroupHelper().createGroup(new GroupData("test2", null, null));
      app.getContactHelper().logoutToWelcomePage();
    }

}
