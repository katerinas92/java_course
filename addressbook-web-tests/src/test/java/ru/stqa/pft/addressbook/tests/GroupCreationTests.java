package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

// Тест для создания новой группы контактов
public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    // Выбираем пункт меню "groups"
    app.getNavigationHelper().gotoGroupPage();
    // вычисляем количество групп до добавления
    int before = app.getGroupHelper().getGroupCount();
    // Нажимаем кнопку "New group" для создания новой группы контактов; вызываем отдельный метод createGroup()
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    // вычисляем количество групп после добавления
    int after = app.getGroupHelper().getGroupCount();
    // проверяем, что количество групп после добавления увеличилось на 1
    Assert.assertEquals(after, before + 1);
  }
}
