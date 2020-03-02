package ru.stqa.fpt.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleting extends TestBase {


    @Test
    public void contactDeletingTest() throws Exception {

        app.getContactHelper().getContactById();
        app.getContactHelper().acceptNextAllert();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAlert();

    }

}