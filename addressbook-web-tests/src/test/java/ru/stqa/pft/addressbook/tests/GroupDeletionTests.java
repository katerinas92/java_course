package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.List;

// Тест для удаления групп(-ы) контактов
public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // Выбираем пункт меню "groups"
    app.goTo().groupPage();
    // Проверяем, есть ли хотя бы одна группа, которую можно удалить
    // Если ее нет, то
    if (app.group().list().size() == 0) {
      // создаем новую группу
      app.group().create(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupDeletion() {
    // вычисляем количество групп до удаления
    // int before = app.group().getGroupCount();
    // Формируем список из групп до создания новой
    List<GroupData> before = app.group().list();
    // задаем значение выбранного элемента для удаления
    int index = before.size() - 1;
    // запускаем метод для удаления групп
    app.group().delete(index);
    // вычисляем количество групп после удаления
    // int after = app.group().getGroupCount();
    // Формируем список из групп после создания новой
    List<GroupData> after = app.group().list();
    // проверяем, что количество групп после удаления увеличилось на 1
    Assert.assertEquals(after.size(), index);
    // удаляем последний элемент листа
    before.remove(index);
    // с помощью assertEquals проверяем, что элементы в листах совпадают
      Assert.assertEquals(before, after);
  }
}
