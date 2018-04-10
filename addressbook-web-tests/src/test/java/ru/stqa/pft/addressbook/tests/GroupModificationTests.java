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
    app.getNavigationHelper().gotoGroupPage();
    // Проверяем, есть ли хотя бы одна группа, которую можно отредактировать
    // Если ее нет, то
    if (! app.getGroupHelper().isThereAGroup()) {
      // создаем новую группу
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupModificationTests() {
    // вычисляем количество групп до модификации
    // int before = app.getGroupHelper().getGroupCount();
    // Формируем список из групп до создания новой
    List<GroupData> before = app.getGroupHelper().getGroupList();
    // задаем значение выбранного элемента для редактирования
    int index = before.size() - 1;
    GroupData group = new GroupData(before.get(index).getId(), "test_modification_1", "test_modification_2", "test_modification_3");
    // запускаем метод для модификации группы
    app.getGroupHelper().modifyGroup(index, group);
    // вычисляем количество групп после удаления
    // int after = app.getGroupHelper().getGroupCount();
    // Формируем список из групп после создания новой
    List<GroupData> after = app.getGroupHelper().getGroupList();
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
