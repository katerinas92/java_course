package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// Тест для создания новой группы контактов
public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    // Выбираем пункт меню "groups"
    app.goTo().groupPage();
    // Формируем множество из групп до создания новой
    Groups before = app.group().all();
    // Задаем значения для новой группы
    GroupData group = new GroupData().withName("test2");
    // Нажимаем кнопку "New group" для создания новой группы контактов; вызываем отдельный метод create()
    app.group().create(group);
    // Формируем список из групп после создания новой
    Groups after = app.group().all();
    // проверка, что количество групп после добавления увеличилось на 1 с помощью hamcrest
    assertThat(after.size(), equalTo(before.size()+1));
    // вычисляем максимальный идентификатор среди групп в множестве
    // и сравниваем множества с помощью hamcrest
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
