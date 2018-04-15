package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

// Тест для удаления групп(-ы) контактов
public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // Выбираем пункт меню "groups"
    app.goTo().groupPage();
    // Проверяем, есть ли хотя бы одна группа, которую можно удалить
    // Если ее нет, то
    if (app.group().all().size() == 0) {
      // создаем новую группу
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() {
    // вычисляем количество групп до удаления
    // int before = app.group().getGroupCount();
    // Формируем множество из групп до создания новой
    Set<GroupData> before = app.group().all();
    // вычисляем группу для удаления из множества случайным образом
    GroupData deletedGroup = before.iterator().next();
    // запускаем метод для удаления групп
    app.group().delete(deletedGroup);
    // вычисляем количество групп после удаления
    // int after = app.group().getGroupCount();
    // Формируем список из групп после создания новой
    Set<GroupData> after = app.group().all();
    // проверяем, что количество групп после удаления увеличилось на 1
    Assert.assertEquals(after.size(), before.size() - 1);
    // удаляем выбранный элемент множества
    before.remove(deletedGroup);
    // с помощью assertEquals проверяем, что элементы в листах совпадают
      Assert.assertEquals(before, after);
  }
}
