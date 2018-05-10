package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.dialect.Dialect;
import org.hibernate.sql.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.ArrayList;
import java.util.List;

// класс, который помогает работать с набором групп данных (заполняемых полей веб-приложения)
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    // обращение к конструктору базового класса
    super(wd);
  }

  // создаем кэш данных
  private Groups groupCache = null;

  // метод выполняющий возвращение в общий список групп после создания группы контактов
  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  // метод выполняющий возвращение на главную страницу после создания группы контактов
  public void returnToHomePage() {
    click(By.linkText("home page"));
  }
  // метод выполняющий нажатие кнопки "Enter information" при создании группы контактов
  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  // метод выполняющий заполнение полей при создании группы контактов
  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  // нажатие кнопки "New group" при создании новой группы контактов
  public void initGroupCreation() {
    click(By.name("new"));
  }

  // выбор групп для удаления и нажатие кнопки "Delete group(s)"
  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  // проставление чекбоксов напротив групп контактов (выбор групп контактов из списка)
  public void selectGroup(int index) {
    // находим элемент по локатору и выбираем нужный
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  // выбор группы по идентификатору
  public void selectGroupById(int id) {
    // находим элемент по локатору и выбираем нужный
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  // нажатие кнопки "Edit Group"
  public void initGroupModification() {
    click(By.name("edit"));
  }

  // нажатие кнопки "update" для модификации группы контактов
  public void submitGroupModification() {
    click(By.name("update"));
  }

  // метод для создания группы
  public void create(GroupData group) {
    // Нажимаем кнопку "New group" для создания новой группы контактов
    initGroupCreation();
    // Заполняем необходимые поля значениями
    fillGroupForm(group);
    // Нажимаем кнопку "Enter information" для создания новой группы
    submitGroupCreation();
    groupCache = null;
    // Возвращаемся к списку всех групп; видим созданную группу
    returnToGroupPage();
  }

  // метод для модификации группы
  public void modify(GroupData group) {
    // Отмечаем чек-боксами группы контактов
    // В качестве index передаем порядковый номер элемента, который нужно выбрать
    selectGroupById(group.getId());
    // Нажимаем кнопку "Edit Groups"
    initGroupModification();
    // Редактируем группу (меняем значения полей)
    fillGroupForm(group);
    // Нажимаем кнопку "Update"
    submitGroupModification();
    groupCache = null;
    // Возвращаемся к списку всех групп
    returnToGroupPage();
  }

  // метод для удаления группы по порядковому номеру
  public void delete(int index) {
    // Отмечаем чек-боксами группы контактов для удаления
    // В качестве index передаем порядковый номер элемента, который нужно выбрать
    selectGroup(index);
    // и удаляем их по кнопке "Delete group(s)"
    deleteSelectedGroups();
    // Возвращаемся к списку всех групп; видим, что выбранная группа удалена
    returnToGroupPage();
  }

  // метод для удаления группы по идентификатору
  public void delete(GroupData group) {
    // Отмечаем чек-боксами группы контактов для удаления
    // В качестве index передаем идентификатор, который нужно выбрать
    selectGroupById(group.getId());
    // и удаляем их по кнопке "Delete group(s)"
    deleteSelectedGroups();
    groupCache = null;
    // Возвращаемся к списку всех групп; видим, что выбранная группа удалена
    returnToGroupPage();
  }

  // метод для проверки наличия элемента на странице
  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  // метод, возвращающий количество групп
  public int count() {
    // в качестве findElements выбран List<>
    return wd.findElements(By.name("selected[]")).size();
  }

  // Формируем список из групп
  public List<GroupData> list() {
    // создаем лист для списка групп
    List<GroupData> groups = new ArrayList<GroupData>();
    // заполняем лист значениями с веб-страницы
    // находим все элементы, которые имеют тег span и класс group
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    // проходим по всем элементам в цикле
    for (WebElement element : elements) {
      // извлекаем имя элемента
      String name = element.getText();
      // извлекаем id элемента из тега input параметр value
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      // создаем обект GroupData и заполняем его значениями
      GroupData group = new GroupData().withId(id).withName(name);
      // добавляем созданный объект в список
      groups.add(group);
    }
    // возвращаем лист с группами
    return groups;
  }

  // Формируем множество
  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    // заполняем лист значениями с веб-страницы
    // находим все элементы, которые имеют тег span и класс group
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    // проходим по всем элементам в цикле
    for (WebElement element : elements) {
      // извлекаем имя элемента
      String name = element.getText();
      // извлекаем id элемента из тега input параметр value
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      // создаем обект GroupData и заполняем его значениями
      GroupData group = new GroupData().withId(id).withName(name);
      // добавляем созданный объект в список
      groupCache.add(group);
    }
    // возвращаем лист с группами
    return new Groups(groupCache);
  }
  public void groupSelect(GroupData group) {
    new org.openqa.selenium.support.ui.Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
  }
}
