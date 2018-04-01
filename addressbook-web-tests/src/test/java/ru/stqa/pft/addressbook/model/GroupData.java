package ru.stqa.pft.addressbook.model;

public class GroupData {
  private final String name;
  private final String header;
  private final String footer;

  public GroupData(String name, String header, String footer) {
    this.name = name;
    this.header = header;
    this.footer = footer;
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

  // создаем метод toString ( с помощью меню Code >> Generate >> выбираем поля >> жмем ОК )
  // этот метод нужен для явного отображения текста элемента при выполнении тестов, для случаев, если тест упал с ошибкой и наименования элементов определены не явно
  @Override
  public String toString() {
    return "GroupData{" +
            "name='" + name + '\'' +
            '}';
  }

  // создаем метод equals() для объекта GroupData ( с помощью меню Code >> Generate >> equals() and hashcode() >> жмем ОК )
  // метод сравнивает атрибуты GroupData
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    return name != null ? name.equals(groupData.name) : groupData.name == null;
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }
}
