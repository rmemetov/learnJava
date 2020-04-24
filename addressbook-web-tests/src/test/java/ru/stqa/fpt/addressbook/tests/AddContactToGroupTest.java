package ru.stqa.fpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;
import ru.stqa.fpt.addressbook.model.Contacts;
import ru.stqa.fpt.addressbook.model.GroupData;
import ru.stqa.fpt.addressbook.model.Groups;

import java.io.IOException;
import java.util.stream.Collectors;

public class AddContactToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() throws IOException {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        if (groups.size() == 0 || contacts.stream().filter((c) -> c.getGroups().equals(groups))
                .collect(Collectors.toList()).size() == contacts.size() && contacts.size() != 0) {
            app.goTo().groupPage();
            app.group().create(app.getTestDataHelper().readGroupsFromXml().get(0));
        }
        if (contacts.size() == 0) {
            app.contact().goToAddContactPage();
            app.contact().create(app.getTestDataHelper().readContactsFromXml().get(0));
        }
    }

    @Test
    public void testAddContactToGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.stream().filter((c) -> !c.getGroups().equals(groups))
                .collect(Collectors.toList()).get(0);
        GroupData group = groups.stream().filter((g) -> !contact.getGroups().contains(g))
                .collect(Collectors.toList()).get(0);

        app.goTo().homePage();
        app.contact().addContactToGroup(contact.getId(), group);

        Assert.assertEquals(app.db().contacts().stream().
                filter((c) -> c.getId() == contact.getId() && c.getGroups().contains(group)).collect(Collectors.toList()).size(), 1);
        Assert.assertEquals(app.db().groups().stream().
                filter((g) -> g.getId() == group.getId() && g.getContacts().contains(contact)).collect(Collectors.toList()).size(), 1);
    }
}
