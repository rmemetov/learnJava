package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.GroupData;
import ru.stqa.fpt.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase {

    @BeforeMethod

    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testGroupModification() {

        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test2").withHeader("test").withFooter("test");
        app.goTo().groupPage();
        app.group().modify(group);
        app.goTo().groupPage();
        Groups after = app.group().all();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }


}
