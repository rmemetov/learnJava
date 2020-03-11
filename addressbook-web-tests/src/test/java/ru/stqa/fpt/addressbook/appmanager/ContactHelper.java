package ru.stqa.fpt.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.fpt.addressbook.model.ContactData;
import ru.stqa.fpt.addressbook.tests.GroupCreateTest;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {

    public boolean acceptNextAlert = true;


    public void acceptNextAlert() {
        acceptNextAlert = true;
    }

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void logoutToWelcomePage() {
        wd.findElement(By.linkText("Logout")).click();
    }

    public void confirmContactCreation() {
        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wd.findElement(By.name("middlename")).click();
        wd.findElement(By.name("middlename")).clear();
        wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
        wd.findElement(By.name("address")).click();
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wd.findElement(By.name("mobile")).click();
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(contactData.getMobilephone());
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(contactData.getEmail());

        /*if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }*/

    }


    public void goToAddContactPage() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void updateContact() {
        wd.findElement(By.name("update")).click();
    }

    public void editContact() {
        wd.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    public void deleteContact() {
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void acceptNextAllert() {
        acceptNextAlert = true;
    }

    public void getContactById(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void closeAlert() {
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public String closeAlertAndGetItsText() {
        try {
            Alert alert = wd.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert();
        }
    }


    public void createContact(ContactData contactData) {
        fillForm(contactData);
        confirmContactCreation();
        homePageOpen();
    }

    public void homePageOpen() {
        wd.findElement(By.linkText("home page")).click();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
       return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));


        for (WebElement element : elements){
            String name = element.getText();
            String middlename = element.getText();
            ContactData contact = new ContactData(name, middlename, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
