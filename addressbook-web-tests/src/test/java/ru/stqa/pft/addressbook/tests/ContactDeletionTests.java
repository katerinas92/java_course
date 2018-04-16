package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

// Тест для удаления контакта
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // Проверяем, есть ли хотя бы один контакт, который можно удалить
    // Если его нет, то
    if (app.contact().all().size() == 0) {
      // Выбираем пункт меню "add new"
      app.goTo().gotoContactPage();
      // создаем новый контакт
      app.contact().createContact(new ContactGroupData().withFirstname("Suslova").withMiddlename("Igorevna").withLastname("Ekaterina").withAddress("Russia").withEmail1("e_suslova@mail.ru").withHome("12-12-12").withMobile("999-999-999-99").withWork("123-123-456").withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion() {
    // Формируем множество из контактов до удаления контакта
    Contacts before = app.contact().all();
    // вычисляем контакт для удаления из множества случайным образом
    ContactGroupData deletedContact = before.iterator().next();
    // и удаляем их по кнопке "Delete"
    app.contact().delete(deletedContact);
    // Возвращаемся к списку всех контактов; видим, что выбранная группа удалена
    app.goTo().goToHomePage();
    // Формируем множество из групп после создания новой
    Contacts after = app.contact().all();
    // проверяем, что количество групп после удаления увеличилось на 1
    assertEquals(after.size(), before.size() - 1);
    // удаляем выбранный элемент множества и проверяем, что элементы в множествах совпадают
    assertThat(after, equalTo(before.withOut(deletedContact)));
  }
}