package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    // Формируем список из групп до создания новой
    Groups before = app.group().all();
    // вычисляем группу для модификации из множества случайным образом
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test_modification_1").withHeader("test_modification_2").withFooter("test_modification_3");
    // запускаем метод для модификации группы
    app.group().modify(group);
    // проверка, что количество групп после модификации не изменилось с помощью hamcrest
    assertThat(app.group().count(), equalTo(before.size()));
    // Формируем список из групп после создания новой
    Groups after = app.group().all();
    // сравниваем отсортированные группы
    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
  }
}
