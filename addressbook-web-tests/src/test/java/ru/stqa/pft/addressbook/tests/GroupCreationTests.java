package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.List;

// Тест для создания новой группы контактов
public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    // Выбираем пункт меню "groups"
    app.getNavigationHelper().gotoGroupPage();
    // вычисляем количество групп до добавления
    // int before = app.getGroupHelper().getGroupCount();
    // Формируем список из групп до создания новой
    List<GroupData> before = app.getGroupHelper().getGroupList();
    // Нажимаем кнопку "New group" для создания новой группы контактов; вызываем отдельный метод createGroup()
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    // вычисляем количество групп после добавления
    // int after = app.getGroupHelper().getGroupCount();
    // Формируем список из групп после создания новой
    List<GroupData> after = app.getGroupHelper().getGroupList();
    // проверяем, что количество групп после добавления увеличилось на 1
    Assert.assertEquals(after.size(), before.size()+1);
  }
}
