package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.GroupData;

// класс, который помогает работать с набором групп данных (заполняемых полей веб-приложения)
public class GroupHelper extends HelperBase {

  public GroupHelper(FirefoxDriver wd) {
    // обращение к конструктору базового класса
    super(wd);
  }

  // метод выполняющий возвращение в общий список групп после создания группы контактов
  public void returnToGroupPage() {
    click(By.linkText("group page"));
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
    selectGroup();
    click(By.name("delete"));
  }

  // проставление чекбоксов напротив групп контактов (выбор групп контактов из списка)
  public void selectGroup() {
    click(By.name("selected[]"));
  }
}
