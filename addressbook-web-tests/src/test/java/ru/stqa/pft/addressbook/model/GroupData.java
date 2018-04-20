package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

// Задаем названия для тэгов XML файла
@XStreamAlias("group")

public class GroupData {
  // указываем, что поле ID не должно сохраняться в XML файл
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Expose
  private String name;
  @Expose
  private String header;
  @Expose
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
