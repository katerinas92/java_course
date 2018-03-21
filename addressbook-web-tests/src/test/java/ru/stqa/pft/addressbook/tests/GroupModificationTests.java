package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {
  @Test

  public void testGroupModificationTests() {
    // Выбираем пункт меню "groups"
    app.getNavigationHelper().gotoGroupPage();
    // Отмечаем чек-боксами группы контактов
    app.getGroupHelper().selectGroup();
    // Нажимаем кнопку "Edit Groups"
    app.getGroupHelper().initGroupModification();
    // Редактируем группу (меняем значения полей)
    app.getGroupHelper().fillGroupForm(new GroupData("test_modification_1", "test_modification_2", "test_modification_3"));
    // Нажимаем кнопку "Update"
    app.getGroupHelper().submitGroupModification();
    // Возвращаемся к списку всех групп
    app.getGroupHelper().returnToGroupPage();
  }
}
