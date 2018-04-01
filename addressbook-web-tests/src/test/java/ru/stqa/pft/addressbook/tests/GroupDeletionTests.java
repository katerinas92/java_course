package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

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
    // вычисляем количество групп до удаления
    // int before = app.getGroupHelper().getGroupCount();
    // Формируем список из групп до создания новой
    List<GroupData> before = app.getGroupHelper().getGroupList();
    // Отмечаем чек-боксами группы контактов для удаления
    // В качестве index передаем порядковый номер элемента, который нужно выбрать
    app.getGroupHelper().selectGroup(before.size() - 1);
    // и удаляем их по кнопке "Delete group(s)"
    app.getGroupHelper().deleteSelectedGroups();
    // Возвращаемся к списку всех групп; видим, что выбранная группа удалена
    app.getGroupHelper().returnToGroupPage();
    // вычисляем количество групп после удаления
    // int after = app.getGroupHelper().getGroupCount();
    // Формируем список из групп после создания новой
    List<GroupData> after = app.getGroupHelper().getGroupList();
    // проверяем, что количество групп после удаления увеличилось на 1
    Assert.assertEquals(after.size(), before.size() - 1);
  }
}
