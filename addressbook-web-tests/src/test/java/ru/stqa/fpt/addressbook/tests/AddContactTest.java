package ru.stqa.fpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class AddContactTest extends TestBase {


    @Test(enabled=false)
    public void contactCreateTest() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Ivanov", "Ivan", "Moscow", "71234567890", "test@test.ru", "test2");
        app.getContactHelper().goToAddContactPage();
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
