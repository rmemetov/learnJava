package ru.stqa.fpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleting extends TestBase {


    @Test
    public void contactDeletingTest() throws Exception {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("Ivanov", "Ivan", "Moscow", "71234567890", "test@test.ru", "test2"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().getContactById(before.size() - 1);
        app.getContactHelper().acceptNextAllert();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAlert();
        app.homePageOpen();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

}