package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;

// Тест для удаления контакта
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    // Проверяем, есть ли хотя бы один контакт, который можно удалить
    // Если е нет, то
    if (! app.getContactHelper().isThereAContact()) {
      // создаем новый контакт
      app.getContactHelper().createContact(new ContactGroupData("Suslova", "Igorevna", "Ekaterina", "Russia", "e_suslova@mail.ru", "12-12-12", "999-999-999-99", "123-123-456", "test1"));
    }
    // Отмечаем чек-боксами контакты для удаления и удаляем их по кнопке "Delete)"
    app.getContactHelper().deleteSelectedContacts();
    // Возвращаемся к списку всех контактов; видим, что выбранная группа удалена
    app.getNavigationHelper().goToHomePage();
  }
}
