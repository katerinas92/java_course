package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != groupData.id) return false;
    return name != null ? name.equals(groupData.name) : groupData.name == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
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

}
