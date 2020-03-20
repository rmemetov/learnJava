package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;
import ru.stqa.fpt.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().goToAddContactPage();
            app.contact().create(new ContactData()
                    .withFirstname("Ivan")
                    .withLastname("Ivanov")
                    .withMobilephone("+79123213223")
                    .withEmail("test@test.test")
                    .withAddress("")
                    .withGroup("test2"));
        }
        app.goTo().homePage();

    }

    @Test()
    public void contactModification() {


        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("Petr")
                .withLastname("Petrovich")
                .withMobilephone("+66666666666")
                .withEmail("raz@dva.tri")
                .withAddress("Moscow")
                .withGroup(null);
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)) );
    }


}
