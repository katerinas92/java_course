package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModificationTests() {

    // Открываем контакт на редактирование по кнопке с карандашом
    app.getContactHelper().updateSelectedContacts();
    // Редактируем контакт (меняем значения полей)
    app.getContactHelper().fillContactForm(new ContactGroupData("Ivanov", "Ivanovich", "Ivan", "Russia", "ivan@mail.ru", "333-333", "8-800-555-55-55", "80-80-80"));
    // Нажимаем кнопку "Update"
    app.getContactHelper().updateContact();
    // Возвращаемся к списку всех контактов
    app.getContactHelper().returnToHomePage();
  }



}
