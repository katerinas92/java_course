package ru.stqa.pft.addressbook.model;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

// Задаем названия для тэгов XML файла
@XStreamAlias("contact")
// Связываем объект GroupData с таблицей group_list в БД
@Entity
@Table(name = "addressbook")

public class ContactGroupData {
  // указываем, что поле ID не должно сохраняться в XML файл
  @XStreamOmitField
  // Связываем значение id объекта GroupData с полем group_id в таблице group_list
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private String firstname;
  @Expose
  @Column(name = "middlename")
  private String middlename;
  @Expose
  @Column(name = "lastname")
  private String lastname;
  @Transient
  private String address;
  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email1;
  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;
  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;
  @Column(name = "home")
  @Type(type = "text")
  private String home;
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile;
  @Column(name = "work")
  @Type(type = "text")
  private String work;
  @Expose
  // помечаем поле allPhones аннотацией @Transient - это значит, это поле не будет использоваться в выборке данных и сравнении
  @Transient
  private String allPhones;
  @Transient
  private String allEmails;
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;
  // помечаем поле groups аннотацией @ManyToMany - т.к. один контакт, может быть связан с несколькими группами, а группа может содержать несколько контактов
  @ManyToMany
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  public Set<GroupData> groups = new HashSet<GroupData>();

  public File getPhoto() {
    return new File(photo);
  }

  public ContactGroupData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactGroupData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactGroupData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactGroupData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

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

  public ContactGroupData withEmail1 (String email1) {
    this.email1 = email1;
    return this;
  }

  public ContactGroupData withEmail2 (String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactGroupData withEmail3 (String email3) {
    this.email3 = email3;
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

  public int getId() { return id; }

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

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
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

  public Groups getGroups() {
    return new Groups(groups);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactGroupData that = (ContactGroupData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (email1 != null ? !email1.equals(that.email1) : that.email1 != null) return false;
    if (home != null ? !home.equals(that.home) : that.home != null) return false;
    if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
    return work != null ? work.equals(that.work) : that.work == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (email1 != null ? email1.hashCode() : 0);
    result = 31 * result + (home != null ? home.hashCode() : 0);
    result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
    result = 31 * result + (work != null ? work.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactGroupData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", email1='" + email1 + '\'' +
            ", home='" + home + '\'' +
            ", mobile='" + mobile + '\'' +
            ", work='" + work + '\'' +
            '}';
  }
}