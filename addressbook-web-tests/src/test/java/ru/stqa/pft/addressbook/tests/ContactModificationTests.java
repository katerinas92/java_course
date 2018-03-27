package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModificationTests() {

    // Открываем контакт на редактирование по кнопке с карандашом
    app.getContactHelper().updateSelectedContacts();
    // Редактируем контакт (меняем значения полей);
    // в качестве значения group передаем значение null, т.к. при модификации контактак изменить группу нельзя
    // в качестве значения creation передаем false, т.к. на форме редактирования контакта поле new_group отсутствует
    app.getContactHelper().fillContactForm(new ContactGroupData("Ivanov", "Ivanovich", "Ivan", "Russia", "ivan@mail.ru", "333-333", "8-800-555-55-55", "80-80-80", null), false);
    // Нажимаем кнопку "Update"
    app.getContactHelper().updateContact();
    // Возвращаемся к списку всех контактов
    app.getContactHelper().returnToHomePage();
  }



}
