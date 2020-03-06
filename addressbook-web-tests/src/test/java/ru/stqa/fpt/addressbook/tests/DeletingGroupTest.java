package ru.stqa.fpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.fpt.addressbook.model.GroupData;

public class DeletingGroupTest extends TestBase {

    @Test
    public void testDeletingGroup() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test2", null, null));
        }
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().goToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before - 1);
    }

}
