package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

// класс, который помогает работать с набором групп данных (заполняемых полей веб-приложения)
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    // обращение к конструктору базового класса
    super(wd);
  }

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

  // нажатие кнопки "Edit Group"
  public void initGroupModification() {
    click(By.name("edit"));
  }

  // нажатие кнопки "update" для модификации группы контактов
  public void submitGroupModification() {
    click(By.name("update"));
  }

  // метод для создания группы
  public void createGroup(GroupData group) {
    // Нажимаем кнопку "New group" для создания новой группы контактов
    initGroupCreation();
    // Заполняем необходимые поля значениями
    fillGroupForm(group);
    // Нажимаем кнопку "Enter information" для создания новой группы
    submitGroupCreation();
    // Возвращаемся к списку всех групп; видим созданную группу
    returnToGroupPage();
  }

  // метод для модификации группы

  public void modifyGroup(int index, GroupData group) {
    // Отмечаем чек-боксами группы контактов
    // В качестве index передаем порядковый номер элемента, который нужно выбрать
    selectGroup(index);
    // Нажимаем кнопку "Edit Groups"
    initGroupModification();
    // Редактируем группу (меняем значения полей)
    fillGroupForm(group);
    // Нажимаем кнопку "Update"
    submitGroupModification();
    // Возвращаемся к списку всех групп
    returnToGroupPage();
  }

  // метод для проверки наличия элемента на странице
  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  // метод, возвращающий количество групп
  public int getGroupCount() {
    // в качестве findElements выбран List<>
    return wd.findElements(By.name("selected[]")).size();
  }

  // Формируем список из групп
  public List<GroupData> getGroupList() {
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
      GroupData group = new GroupData(id, name, null, null);
      // добавляем созданный объект в список
      groups.add(group);
    }
    // возвращаем лист с группами
    return groups;
  }
}
