package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void contactModification() {
        app.getContactHelper().editContact();
        app.getContactHelper().fillForm(new ContactData("Ivanov2", "Ivan2", "Omsk", "123", "email@test.com", null));
        app.getContactHelper().updateContact();
        app.homePageOpen();
    }

}
