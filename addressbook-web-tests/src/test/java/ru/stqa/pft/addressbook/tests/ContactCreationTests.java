package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// Тест для создания нового контакта
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        // Формируем множество из контактов до создания нового
       Contacts before = app.contact().all();
       // указываем относительный путь до файла с фото
       File photo = new File("src/test/resources/stru.jpg");
        // Задаем значения для нового контакта
        ContactGroupData contact = new ContactGroupData().withFirstname("Suslova").withMiddlename("Igorevna").withLastname("Ekaterina").withAddress("Russia").withEmail1("e_suslova@mail.ru").withHome("12-12-12").withMobile("999-999-999-99").withWork("123-123-456").withPhoto(photo).withGroup("test1");
        // Выбираем пункт меню "add new"
        app.goTo().gotoContactPage();
        app.contact().createContact(contact);
        // Формируем множество из контактов после создания нового
        Contacts after = app.contact().all();
        // проверка, что количество контактов после добавления увеличилось на 1 с помощью hamcrest
        assertThat(after.size(), equalTo(before.size()+1));
        // вычисляем максимальный идентификатор среди контактов в множестве
        // и сравниваем множества с помощью hamcrest
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
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
