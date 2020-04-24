package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;
import ru.stqa.fpt.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
                    .withAddress(""));
        }
        app.goTo().homePage();

    }

    @Test()
    public void contactModification() {


        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("Petr")
                .withLastname("Petrovich")
                .withMobilephone("+66666666666")
                .withEmail("raz@dva.tri")
                .withAddress("Moscow")
                .withPhoto(new File("src/test/resources/pic.jpg"));
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUi();
    }

}