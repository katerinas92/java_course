package ru.stqa.pft.addressbook.model;

public class ContactGroupData {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String middlename;
  private String lastname;
  private String address;
  private String email;
  private String home;
  private String mobile;
  private String work;
  private String group;

  public ContactGroupData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactGroupData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactGroupData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactGroupData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactGroupData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactGroupData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactGroupData withHome(String home) {
    this.home = home;
    return this;
  }

  public ContactGroupData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactGroupData withWork(String work) {
    this.work = work;
    return this;
  }

  public ContactGroupData withGroup(String group) {
    this.group = group;
    return this;
  }

  public int getId() {
    return id;
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

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactGroupData that = (ContactGroupData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactGroupData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
}
