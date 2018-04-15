package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    // Формируем множество из групп до создания новой
    Groups before = app.group().all();
    // вычисляем группу для удаления из множества случайным образом
    GroupData deletedGroup = before.iterator().next();
    // запускаем метод для удаления групп
    app.group().delete(deletedGroup);
    // Формируем список из групп после создания новой
    Groups after = app.group().all();
    // проверяем, что количество групп после удаления увеличилось на 1
    assertEquals(after.size(), before.size() - 1);
    // удаляем выбранный элемент множества и проверяем, что элементы в множествах совпадают
    assertThat(after, equalTo(before.withOut(deletedGroup)));
  }
}
