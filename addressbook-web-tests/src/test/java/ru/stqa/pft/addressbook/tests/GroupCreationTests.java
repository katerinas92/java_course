package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

// Тест для создания новой группы контактов
public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    // Выбираем пункт меню "groups"
    app.goTo().groupPage();
    // вычисляем количество групп до добавления
    // int before = app.group().getGroupCount();
    // Формируем список из групп до создания новой
    List<GroupData> before = app.group().list();
    // Задаем значения для новой группы
    GroupData group = new GroupData("test2", null, null);
    // Нажимаем кнопку "New group" для создания новой группы контактов; вызываем отдельный метод create()
    app.group().create(group);
    // вычисляем количество групп после добавления
    // int after = app.group().getGroupCount();
    // Формируем список из групп после создания новой
    List<GroupData> after = app.group().list();
    // проверяем, что количество групп после добавления увеличилось на 1
    Assert.assertEquals(after.size(), before.size()+1);

    // новые возможности для Java 8
    // превращаем список в поток, а потом с помощью компаратора сравниваем 2 объекта GroupData и вычисляем максимальный Id
    // Компаратор объекта GroupData, который сравнивает несколько таких объектов
    // group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    before.add(group);
    // сортируем списки с помощью компаратора и функции sort()
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    // сортируем старый и новый списки
    before.sort(byId);
    after.sort(byId);
    // сравниваем отсортированные группы
    Assert.assertEquals(before, after);
    // сравниваем множества
    // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
