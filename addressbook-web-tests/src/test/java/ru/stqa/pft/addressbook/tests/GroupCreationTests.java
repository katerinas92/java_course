package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// Тест для создания новой группы контактов
public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    // Заполняем список массивом. Каждый массив содержит набор данных для одного запуска тестового метода.
    List<Object[]> list = new ArrayList<Object[]>();
    // Передаем в тест массивы с данными.
    list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
    list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
    list.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
    // Возвращаем итератор этого списка
    return list.iterator();
  }

  // тест на успешное создание новой группы
  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    // Выбираем пункт меню "groups"
    app.goTo().groupPage();
    // Формируем множество из групп до создания новой
    Groups before = app.group().all();
    // Нажимаем кнопку "New group" для создания новой группы контактов; вызываем отдельный метод create()
    app.group().create(group);
    // проверка, что количество групп после добавления увеличилось на 1 с помощью hamcrest
    assertThat(app.group().count(), equalTo(before.size()+1));
    // Формируем список из групп после создания новой
    Groups after = app.group().all();
    // вычисляем максимальный идентификатор среди групп в множестве
    // и сравниваем множества с помощью hamcrest
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  // тест на то, что группа с именем, содержащее апостроф, не будет создана
  @Test
  public void testBadGroupCreation() {
    // Выбираем пункт меню "groups"
    app.goTo().groupPage();
    // Формируем множество из групп до создания новой
    Groups before = app.group().all();
    // Задаем значения для новой группы
    GroupData group = new GroupData().withName("test2'");
    // Нажимаем кнопку "New group" для создания новой группы контактов; вызываем отдельный метод create()
    app.group().create(group);
    // проверка, что количество групп после добавления увеличилось на 1 с помощью hamcrest
    assertThat(app.group().count(), equalTo(before.size()));
    // Формируем список из групп после создания новой
    Groups after = app.group().all();
    // вычисляем максимальный идентификатор среди групп в множестве
    // и сравниваем множества с помощью hamcrest
    assertThat(after, equalTo(before));
  }
}
