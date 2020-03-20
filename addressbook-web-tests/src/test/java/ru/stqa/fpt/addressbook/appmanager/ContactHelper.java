package ru.stqa.fpt.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.fpt.addressbook.model.ContactData;
import ru.stqa.fpt.addressbook.model.Contacts;

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
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
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

    public void editContact(int id) {
        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();
    }

    public void deleteContact() {
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void acceptNextAllert() {
        acceptNextAlert = true;
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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


    public void create(ContactData contactData) {
        fillForm(contactData);
        confirmContactCreation();
        homePageOpen();
    }

    public void homePageOpen() {
        wd.findElement(By.linkText("home page")).click();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("//table[@id='maintable']/tbody/tr[2]/td/input"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("//table[@id='maintable']/tbody/tr[2]/td/input")).size();
    }


    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withLastname(lastname));
        }
        return contacts;
    }

    public void modify(ContactData contact) {

        selectContactById(contact.getId());
        editContact(contact.getId());
        fillForm(contact);
        updateContact();
        homePageOpen();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        acceptNextAllert();
        deleteContact();
        closeAlert();
    }
}
