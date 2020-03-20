package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withLastname("test").withFirstname("test")
                    .withHomePhone("22 33").withMobilephone("+79111111111")
                    .withWorkPhone("812-123-12-12").withEmail("test@mail.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHomePhone(), equalTo(contactInfoFromEditForm.getHomePhone()));
        assertThat(contact.getMobilephone(), equalTo(contactInfoFromEditForm.getMobilephone()));
        assertThat(contact.getWorkPhone(), equalTo(contactInfoFromEditForm.getWorkPhone()));

    }
}
