package ru.stqa.fpt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.fpt.addressbook.model.ContactData;

public class ContactHelper {
    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void logoutToWelcomePage() {
        wd.findElement(By.linkText("Logout")).click();
    }

    public void confirmContactCreation() {
        wd.findElement(By.name("theform")).click();
        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wd.findElement(By.name("middlename")).click();
        wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
        wd.findElement(By.name("address")).click();
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wd.findElement(By.name("theform")).click();
        wd.findElement(By.name("mobile")).click();
        wd.findElement(By.name("mobile")).sendKeys(contactData.getMobilephone());
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    public void goToAddContactPage() {
        wd.findElement(By.linkText("add new")).click();
    }
}
