package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

// Тест для удаления групп(-ы) контактов
public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    // Выбираем пункт меню "groups"
    app.getNavigationHelper().gotoGroupPage();
    // Отмечаем чек-боксами группы контактов для удаления и удаляем их по кнопке "Delete group(s)"
    app.getGroupHelper().deleteSelectedGroups();
    // Возвращаемся к списку всех групп; видим, что выбранная группа удалена
    app.getGroupHelper().returnToGroupPage();
  }
}
