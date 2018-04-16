package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// Тест для создания нового контакта
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        // Формируем множество из контактов до создания нового
       Contacts before = app.contact().all();
        // Задаем значения для нового контакта
        ContactGroupData contact = new ContactGroupData().withFirstname("Suslova").withMiddlename("Igorevna").withLastname("Ekaterina").withAddress("Russia").withEmail1("e_suslova@mail.ru").withHome("12-12-12").withMobile("999-999-999-99").withWork("123-123-456").withGroup("test1");
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
}