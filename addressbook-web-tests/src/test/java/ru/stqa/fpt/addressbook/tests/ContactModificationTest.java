package ru.stqa.fpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void contactModification() {
        if (! app.getContactHelper().isThereAContact())  {
            app.getContactHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("Ivanov", "Ivan", "Moscow", "71234567890", "test@test.ru", "test2"), true);
        }
        int before = app.getGroupHelper().getGroupCount();

        app.getContactHelper().editContact();
        app.getContactHelper().fillForm(new ContactData("Ivanov2", "Ivan2", "Omsk", "123", "email@test.com", null), false);
        app.getContactHelper().updateContact();
        app.homePageOpen();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }

}
