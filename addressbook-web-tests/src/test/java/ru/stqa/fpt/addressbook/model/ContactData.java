package ru.stqa.fpt.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String address;
    private final String mobilephone;
    private final String email;
    private String group;

    public ContactData(String firstname, String middlename, String address, String mobilephone, String email, String group) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.address = address;
        this.mobilephone = mobilephone;
        this.email = email;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(middlename, that.middlename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, middlename);
    }
}
