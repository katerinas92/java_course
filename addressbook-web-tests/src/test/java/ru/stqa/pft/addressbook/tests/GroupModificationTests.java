package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // Выбираем пункт меню "groups"
    app.goTo().groupPage();
    //if (! app.group().isThereAGroup())
    // Проверяем, есть ли хотя бы одна группа, которую можно отредактировать
      if (app.group().list().size() == 0){
      // Если ее нет, то создаем новую группу
      app.group().create(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupModificationTests() {
    // вычисляем количество групп до модификации
    // int before = app.group().getGroupCount();
    // Формируем список из групп до создания новой
    List<GroupData> before = app.group().list();
    // задаем значение выбранного элемента для редактирования
    int index = before.size() - 1;
    GroupData group = new GroupData(before.get(index).getId(), "test_modification_1", "test_modification_2", "test_modification_3");
    // запускаем метод для модификации группы
    app.group().modify(index, group);
    // вычисляем количество групп после удаления
    // int after = app.group().getGroupCount();
    // Формируем список из групп после создания новой
    List<GroupData> after = app.group().list();
    // проверяем, что количество групп после удаления увеличилось на 1
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    // сортируем списки с помощью компаратора и функции sort()
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    // сортируем старый и новый списки
    before.sort(byId);
    after.sort(byId);
    // сравниваем отсортированные группы
    Assert.assertEquals(before, after);
    // сравниваем множества
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
