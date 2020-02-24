package ru.stqa.fpt.addressbook;

import org.testng.annotations.*;



public class GroupCreate extends TestBase {


    @Test
    public void testGroupCreate() throws Exception {
      goToGroupPage();
      initGroupCreation();
      fillGroupForm(new GroupData("test2", "test", "test"));
      submitGroupCreation();
      goToGroupPage();
      logoutToWelcomePage();
    }

}
