package ru.stqa.fpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.fpt.addressbook.model.GroupData;

import java.util.List;

public class DeletingGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();

        if (app.group().list().size() == 0) {

            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testDeletingGroup() throws Exception {

        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        app.goTo().groupPage();
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
