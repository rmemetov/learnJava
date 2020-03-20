package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.GroupData;
import ru.stqa.fpt.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreateTest extends TestBase {


    @Test
    public void testGroupCreate() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);
        app.goTo().groupPage();
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        assertThat(after.size(), equalTo(before.size() + 1));

        app.contact().logoutToWelcomePage();


    }

}
