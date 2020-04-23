package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;
import ru.stqa.fpt.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleting extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().goToAddContactPage();
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withLastname("Ivanov").withMobilephone("+79123213223").withEmail("test@test.test").withAddress("").withGroup("test2"));
        }
    }

    @Test()
    public void contactDeletingTest() throws Exception {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.homePageOpen();
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));

    }

}