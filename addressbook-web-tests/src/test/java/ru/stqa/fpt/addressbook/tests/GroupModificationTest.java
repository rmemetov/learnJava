package ru.stqa.fpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase{
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isThereAGroup()){

            app.getGroupHelper().createGroup(new GroupData("test2", null, null));
        }
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup(before -1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test2", "test", "test"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().goToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }
}
