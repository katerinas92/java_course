package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Задаем названия для тэгов XML файла
@XStreamAlias("group")
// Связываем объект GroupData с таблицей group_list в БД
@Entity
@Table(name = "group_list")

public class GroupData {
  // указываем, что поле ID не должно сохраняться в XML файл
  @XStreamOmitField
  // Связываем значение id объекта GroupData с полем group_id в таблице group_list
  @Id
  @Column(name = "group_id")
  private int id = Integer.MAX_VALUE;

  // Связываем значение name объекта GroupData с полем group_name в таблице group_list
  @Expose
  @Column(name = "group_name")
  private String name;

  // Связываем значение header объекта GroupData с полем group_header в таблице group_list
  @Expose
  @Column(name = "group_header")
  @Type(type = "text")
  private String header;

  // Связываем значение footer объекта GroupData с полем group_footer в таблице group_list
  @Expose
  @Column(name = "group_footer")
  @Type(type = "text")
  private String footer;

  @ManyToMany(mappedBy = "groups") // это означает, что в парном классе ContactGroupData нужно найти атрибут groups и оттуда взять описание связей между таблицами и полями
  private Set<ContactGroupData> contacts = new HashSet<ContactGroupData>();

  public int getId() {
    return id;
  }

  public Contacts getContacts() {
    return new Contacts(contacts);
  }

  // Setter заменили на withid
  public GroupData withId(int id) {
    this.id = id;
    // метод будет возвращать тот обЪект, в котором он вызван. Это необходимо для постройки каскадов; возможность вытягивать вызовы методов в цепочку
    return this;
  }

  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != groupData.id) return false;
    if (name != null ? !name.equals(groupData.name) : groupData.name != null) return false;
    if (header != null ? !header.equals(groupData.header) : groupData.header != null) return false;
    return footer != null ? footer.equals(groupData.footer) : groupData.footer == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (header != null ? header.hashCode() : 0);
    result = 31 * result + (footer != null ? footer.hashCode() : 0);
    return result;
  }
}
