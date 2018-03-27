package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

// Тест для удаления групп(-ы) контактов
public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    // Выбираем пункт меню "groups"
    app.getNavigationHelper().gotoGroupPage();
    // Проверяем, есть ли хотя бы одна группа, которую можно удалить
    // Если ее нет, то
    if (! app.getGroupHelper().isThereAGroup()) {
      // создаем новую группу
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    // Отмечаем чек-боксами группы контактов для удаления
    app.getGroupHelper().selectGroup();
    // и удаляем их по кнопке "Delete group(s)"
    app.getGroupHelper().deleteSelectedGroups();
    // Возвращаемся к списку всех групп; видим, что выбранная группа удалена
    app.getGroupHelper().returnToGroupPage();
  }
}
