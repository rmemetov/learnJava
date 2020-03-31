package ru.stqa.fpt.addressbook.tests;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.fpt.addressbook.model.ContactData;
import ru.stqa.fpt.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactsFromJson")
    public void contactCreateTest(ContactData contact) throws Exception {
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/pic.jpg");
        app.contact().goToAddContactPage();
        app.contact().create(contact.withPhoto(photo));
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded( contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));
    }


    @Test(enabled = false)
    public void contactBadCreateTest() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Ivan'").withLastname("Ivanov").withMobilephone("+79123213223").withEmail("test@test.test").withAddress("").withGroup("test2");
        app.contact().goToAddContactPage();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}
