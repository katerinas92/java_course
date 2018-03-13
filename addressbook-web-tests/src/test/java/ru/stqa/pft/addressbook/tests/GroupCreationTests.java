package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

// Тест для создания новой группы контактов
public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    // Выбираем пункт меню "groups"
    app.getNavigationHelper().gotoGroupPage();
    // Нажимаем кнопку "New group" для создания новой группы контактов
    app.getGroupHelper().initGroupCreation();
    // Заполняем необходимые поля значениями
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    // Нажимаем кнопку "Enter information" для создания новой группы
    app.getGroupHelper().submitGroupCreation();
    // Возвращаемся к списку всех групп; видим созданную группу
    app.getGroupHelper().returnToGroupPage();
  }
}
