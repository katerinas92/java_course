package ru.stqa.pft.addressbook.model;

public class ContactGroupData {
  private int id;
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String address;
  private final String email;
  private final String home;
  private final String mobile;
  private final String work;
  private String group;

  public ContactGroupData(String firstname, String middlename, String lastname, String address, String email, String home, String mobile, String work, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.group = group;
  }

  public ContactGroupData(int id, String firstname, String middlename, String lastname, String address, String email, String home, String mobile, String work, String group) {
    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
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
