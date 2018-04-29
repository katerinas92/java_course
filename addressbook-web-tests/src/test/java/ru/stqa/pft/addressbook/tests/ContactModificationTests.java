package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // Проверяем, есть ли хотя бы один контакт, который можно удалить
    // Если его нет, то
    if (app.db().contacts().size() == 0) {
      // Выбираем пункт меню "add new"
      app.goTo().gotoContactPage();
      // создаем новый контакт
      app.contact().createContact(new ContactGroupData().withFirstname("Suslova").withMiddlename("Igorevna").withLastname("Ekaterina").withAddress("Russia").withEmail1("e_suslova@mail.ru").withHome("12-12-12").withMobile("999-999-999-99").withWork("123-123-456").withGroup("test1"));
    }
  }

  @Test
  public void testContactModificationTests() {
    // Формируем множество из контактов до модификации
    Contacts before = app.db().contacts();
    // вычисляем группу для модификации из множества случайным образом
    ContactGroupData modifiedContact = before.iterator().next();
    // Редактируем контакт (меняем значения полей);
    // в качестве значения group передаем значение null, т.к. при модификации контактак изменить группу нельзя
    // в качестве значения creation передаем false, т.к. на форме редактирования контакта поле new_group отсутствует
    ContactGroupData contact = new ContactGroupData().withId(modifiedContact.getId()).withFirstname("Ivanov").withMiddlename("Ivanovich").withLastname("Ivan").withAddress("USA").withEmail1("ivani@mail.ru").withHome("333-333").withMobile("111-111-111-11").withWork("22-22-22").withGroup("test1");
    // запускаем метод для модификации контакта
    app.contact().modify(contact);
    // Формируем множество из контактов после модификации
    Contacts after = app.db().contacts();
    // проверяем, что количество контактов после редактирования не изменилось
    assertEquals(after.size(), before.size());
    // сравниваем отсортированные множества
    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    // метод для запуска проверки множеств контактов через пользовательский интерфейс
    verifyContactListInUI();
  }
}
