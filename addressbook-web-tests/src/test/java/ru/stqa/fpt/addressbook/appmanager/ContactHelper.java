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
    }


    public void goToAddContactPage() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void updateContact() {
        wd.findElement(By.name("update")).click();
    }

    public void editContact(int id) {
        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
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

    public void homePageOpen() {
        wd.findElement(By.linkText("home page")).click();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("//table[@id='maintable']/tbody/tr[2]/td/input"));
    }

    public int count() {
        return wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[2]/td/input")).size();
    }

    private Contacts contactCache = null;


    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withLastname(lastname)
                    .withAllPhones(allPhones)
                    .withAllEmails(allEmails)
                    .withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public void create(ContactData contactData) {
        fillForm(contactData);
        confirmContactCreation();
        contactCache = null;
        homePageOpen();
    }

    public void modify(ContactData contact) {

        selectContactById(contact.getId());
        editContact(contact.getId());
        fillForm(contact);
        updateContact();
        contactCache = null;
        homePageOpen();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        acceptNextAllert();
        deleteContact();
        contactCache = null;
        closeAlert();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        editContact(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilephone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }
}
