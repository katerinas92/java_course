package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

// Тест для создания новой группы контактов
public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    // Выбираем пункт меню "groups"
    app.getNavigationHelper().gotoGroupPage();
    // Нажимаем кнопку "New group" для создания новой группы контактов; вызываем отдельный метод createGroup()
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
  }
}
