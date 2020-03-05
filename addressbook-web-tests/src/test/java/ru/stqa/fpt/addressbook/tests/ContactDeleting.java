package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;

public class ContactDeleting extends TestBase {


    @Test
    public void contactDeletingTest() throws Exception {

        if (! app.getContactHelper().isThereAContact()) {

            app.getContactHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("Ivanov", "Ivan", "Moscow", "71234567890", "test@test.ru", "test2"), true);
        }

        app.getContactHelper().getContactById();
        app.getContactHelper().acceptNextAllert();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAlert();

    }

}