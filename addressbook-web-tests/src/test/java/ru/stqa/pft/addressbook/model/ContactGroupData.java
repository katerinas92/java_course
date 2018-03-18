package ru.stqa.pft.addressbook.model;

public class ContactGroupData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String address;
  private final String email;
  private final String home;
  private final String mobile;
  private final String work;

  public ContactGroupData(String firstname, String middlename, String lastname, String address, String email, String home, String mobile, String work) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }
}
