package ru.stqa.fpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.fpt.addressbook.model.GroupData;


public class GroupCreateTest extends TestBase {


    @Test
    public void testGroupCreate() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        int before = app.getGroupHelper().getGroupCount();

        app.getGroupHelper().createGroup(new GroupData("test2", null, null));
        app.getNavigationHelper().goToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
        app.getContactHelper().logoutToWelcomePage();


    }

}
