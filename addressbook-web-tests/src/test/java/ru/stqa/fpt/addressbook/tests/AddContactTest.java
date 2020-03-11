package ru.stqa.fpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;

import java.util.List;

public class AddContactTest extends TestBase {


    @Test
    public void contactCreateTest() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        //int before = app.getContactHelper().getContactCount();
        app.getContactHelper().goToAddContactPage();
        app.getContactHelper().createContact(new ContactData("Ivanov", "Ivan", "Moscow", "71234567890", "test@test.ru", "test2"));
        List<ContactData> after = app.getContactHelper().getContactList();
        //int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
