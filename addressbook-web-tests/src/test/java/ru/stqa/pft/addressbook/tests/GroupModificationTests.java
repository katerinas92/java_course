package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // Выбираем пункт меню "groups"
    app.goTo().groupPage();
    //if (! app.group().isThereAGroup())
    // Проверяем, есть ли хотя бы одна группа, которую можно отредактировать
      if (app.group().all().size() == 0){
      // Если ее нет, то создаем новую группу
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModificationTests() {
    // вычисляем количество групп до модификации
    // int before = app.group().getGroupCount();
    // Формируем список из групп до создания новой
    Set<GroupData> before = app.group().all();
    // вычисляем группу для модификации из множества случайным образом
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test_modification_1").withHeader("test_modification_2").withFooter("test_modification_3");
    // запускаем метод для модификации группы
    app.group().modify(group);
    // вычисляем количество групп после удаления
    // int after = app.group().getGroupCount();
    // Формируем список из групп после создания новой
    Set<GroupData> after = app.group().all();
    // проверяем, что количество групп после удаления увеличилось на 1
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(group);
    // сравниваем отсортированные группы
    Assert.assertEquals(before, after);
    // сравниваем множества
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
