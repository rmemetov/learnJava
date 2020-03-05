package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;

public class AddContactTest extends TestBase {


    @Test
    public void contactCreateTest() throws Exception {
        app.getContactHelper().goToAddContactPage();
        app.getContactHelper().fillForm(new ContactData("Ivanov", "Ivan", "Moscow", "71234567890", "test@test.ru", "test2"), true);
        app.getContactHelper().confirmContactCreation();
        app.homePageOpen();
    }

}
