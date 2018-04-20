package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// Тест для создания новой группы контактов
public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    // Заполняем список массивом. Каждый массив содержит набор данных для одного запуска тестового метода.
    List<Object[]> list = new ArrayList<Object[]>();
    // Передаем в тест массивы с данными.
    // list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
    // list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
    // list.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
    // Или считываем набор данных из csv файла
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    // считываем данные из файла, пока не закончатся строки
    while (line != null) {
      // for xml
      xml += line;
      line = reader.readLine();
      // for csv
      // считываем данные через разделитель ;
      // String[] split = line.split(";");
      // создаем массив, который состоит из одного элемента и помещаем его в список
      // list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      // line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
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
