package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;
import ru.stqa.fpt.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactTest extends TestBase {


    @Test()
    public void contactCreateTest() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Ivan").withLastname("Ivanov").withMobilephone("+79123213223").withEmail("test@test.test").withAddress("").withGroup("test2");
        app.contact().goToAddContactPage();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded( contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));
    }

    @Test()
    public void contactBadCreateTest() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Ivan'").withLastname("Ivanov").withMobilephone("+79123213223").withEmail("test@test.test").withAddress("").withGroup("test2");
        app.contact().goToAddContactPage();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}
