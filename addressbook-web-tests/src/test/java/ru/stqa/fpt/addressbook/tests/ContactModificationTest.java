package ru.stqa.fpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void contactModification() {
        if (! app.getContactHelper().isThereAContact())  {
            app.getContactHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("Ivanov", "Ivan", "Moscow", "71234567890", "test@test.ru", "test2"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();

        //int before = app.getContactHelper().getContactCount();
        app.getContactHelper().getContactById(before.size() - 1);
        app.getContactHelper().editContact();
        ContactData contact = new ContactData("Ivanov2", "Ivan2", "Omsk", "123", "email@test.com", null);
        app.getContactHelper().fillForm(contact);
        app.getContactHelper().updateContact();
        app.homePageOpen();
        List<ContactData> after = app.getContactHelper().getContactList();

        //int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }

}
