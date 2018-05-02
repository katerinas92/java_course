package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;
import ru.stqa.pft.addressbook.model.Contacts;
//import ru.stqa.pft.addressbook.model.Groups;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// Тест для создания нового контакта
public class ContactCreationTests extends TestBase {

  // загрузка данных из файла XML
  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    // Заполняем список массивом. Каждый массив содержит набор данных для одного запуска тестового метода.
    List<Object[]> list = new ArrayList<Object[]>();
    // Передаем в тест массивы с данными.
    // list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
    // list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
    // list.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
    // Или считываем набор данных из csv файла
     try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
       String xml = "";
       String line = reader.readLine();
       // считываем данные из файла, пока не закончатся строки
       while (line != null) {
         xml += line;
         line = reader.readLine();
       }
       XStream xstream = new XStream();
       xstream.processAnnotations(ContactGroupData.class);
       List<ContactGroupData> contacts = (List<ContactGroupData>) xstream.fromXML(xml);
       return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
     }
  }

  // загрузка данных из файла JSON
  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    // Заполняем список массивом. Каждый массив содержит набор данных для одного запуска тестового метода.
    List<Object[]> list = new ArrayList<Object[]>();
    // Считываем набор данных из csv файла
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      // считываем данные из файла, пока не закончатся строки
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactGroupData> contacts = gson.fromJson(json, new TypeToken<List<ContactGroupData>>(){}.getType());  //List<GroupData>.class
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  // передаем в тест имя провайдера, который будет подгружать данные из файлов
  @Test(dataProvider = "validContactsFromXml")
    public void testContactCreation(ContactGroupData contact) {
        // получаем список всех групп из базы данных
        // Groups groups = app.db().groups();
        // Формируем множество из контактов до создания нового
       Contacts before = app.db().contacts();
       // указываем относительный путь до файла с фото
       // File photo = new File("src/test/resources/stru.jpg");
        // Задаем значения для нового контакта
        //ContactGroupData contact = new ContactGroupData().withFirstname("Suslova").withMiddlename("Igorevna").withLastname("Ekaterina").withAddress("Russia").withEmail1("e_suslova@mail.ru").withHome("12-12-12").withMobile("999-999-999-99").withWork("123-123-456").withPhoto(photo).withGroup("test1");
        // Выбираем пункт меню "add new"
        app.goTo().gotoContactPage();
        app.contact().createContact(contact);
        // Формируем множество из контактов после создания нового
        Contacts after = app.db().contacts();
        // проверка, что количество контактов после добавления увеличилось на 1 с помощью hamcrest
        assertThat(after.size(), equalTo(before.size()+1));
        // вычисляем максимальный идентификатор среди контактов в множестве
        // и сравниваем множества с помощью hamcrest
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    // метод для запуска проверки множеств контактов через пользовательский интерфейс
    verifyContactListInUI();
    }

    // тест для определения текущей рабочей директории и соответствия того, что файл существует
    @Test (enabled = false)
    public void testCurrentDir() {
      File currentDir = new File (".");
      // выводим на консоль директорию модуля
      System.out.println(currentDir.getAbsolutePath());
      File photo = new File("src/test/resources/stru.jpg");
      System.out.println(photo.getAbsolutePath());
      System.out.println(photo.exists());
    }
}
