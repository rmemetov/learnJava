package ru.stqa.fpt.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String address;
    private final String mobilephone;
    private final String email;

    public ContactData(String firstname, String middlename, String address, String mobilephone, String email) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.address = address;
        this.mobilephone = mobilephone;
        this.email = email;
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
}
