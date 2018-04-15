package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

// Тест для создания новой группы контактов
public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    // Выбираем пункт меню "groups"
    app.goTo().groupPage();
    // вычисляем количество групп до добавления
    // int before = app.group().getGroupCount();
    // Формируем множество из групп до создания новой
    Set<GroupData> before = app.group().all();
    // Задаем значения для новой группы
    GroupData group = new GroupData().withName("test2");
    // Нажимаем кнопку "New group" для создания новой группы контактов; вызываем отдельный метод create()
    app.group().create(group);
    // вычисляем количество групп после добавления
    // int after = app.group().getGroupCount();
    // Формируем список из групп после создания новой
    Set<GroupData> after = app.group().all();
    // проверяем, что количество групп после добавления увеличилось на 1
    Assert.assertEquals(after.size(), before.size()+1);

    // новые возможности для Java 8
    // превращаем список в поток, а потом с помощью компаратора сравниваем 2 объекта GroupData и вычисляем максимальный Id
    // Компаратор объекта GroupData, который сравнивает несколько таких объектов
    // group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    // вычисляем максимальный идентификатор среди групп в множестве
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    // сравниваем множества
    Assert.assertEquals(before, after);
    // сравниваем множества
    // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
