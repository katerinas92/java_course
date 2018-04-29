package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// Тест для удаления групп(-ы) контактов
public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().groups().size() == 0) {
      // Выбираем пункт меню "groups"
      app.goTo().groupPage();
      // Если ее нет, то создаем новую группу
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() {
    // Формируем множество из групп до создания новой
    Groups before = app.db().groups();
    // вычисляем группу для удаления из множества случайным образом
    GroupData deletedGroup = before.iterator().next();
    // Выбираем пункт меню "groups"
    app.goTo().groupPage();
    // запускаем метод для удаления групп
    app.group().delete(deletedGroup);
    // проверка, что количество групп после добавления уменьшилось на 1 с помощью hamcrest
    assertThat(app.group().count(), equalTo(before.size()-1));
    // Формируем список из групп после создания новой
    Groups after = app.db().groups();
    // удаляем выбранный элемент множества и проверяем, что элементы в множествах совпадают
    assertThat(after, equalTo(before.withOut(deletedGroup)));
    // метод для запуска проверки множеств групп через пользовательский интерфейс
    verifyGroupListInUI();
  }
}
