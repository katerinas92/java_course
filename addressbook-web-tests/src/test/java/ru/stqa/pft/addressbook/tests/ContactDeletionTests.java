package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

// Тест для удаления контакта
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    // Отмечаем чек-боксами контакты для удаления и удаляем их по кнопке "Delete)"
    app.getContactHelper().deleteSelectedContacts();
    // Возвращаемся к списку всех контактов; видим, что выбранная группа удалена
    app.getNavigationHelper().goToHomePage();
  }
}
