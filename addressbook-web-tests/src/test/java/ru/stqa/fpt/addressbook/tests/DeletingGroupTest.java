package ru.stqa.fpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.fpt.addressbook.model.GroupData;

import java.util.List;

public class DeletingGroupTest extends TestBase {

    @Test
    public void testDeletingGroup() throws Exception {
        app.getNavigationHelper().goToGroupPage();

        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test2", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

}
