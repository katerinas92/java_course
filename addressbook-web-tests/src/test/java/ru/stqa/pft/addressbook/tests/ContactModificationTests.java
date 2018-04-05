package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModificationTests() {
    // Проверяем, есть ли хотя бы один контакт, который можно удалить
    // Если его нет, то
    if (! app.getContactHelper().isThereAContact()) {
      // Выбираем пункт меню "add new"
      app.getNavigationHelper().gotoContactPage();
      // создаем новый контакт
      app.getContactHelper().createContact(new ContactGroupData("Suslova", "Igorevna", "Ekaterina", "Russia", "e_suslova@mail.ru", "12-12-12", "999-999-999-99", "123-123-456", "test1"));
    }
    // Формируем список из контактов до модификации
    List<ContactGroupData> before = app.getContactHelper().getContactList();
    // Открываем контакт на редактирование по кнопке с карандашом
    // В качестве index передаем порядковый номер элемента, который нужно выбрать
    app.getContactHelper().updateSelectedContacts();
    // Редактируем контакт (меняем значения полей);
    // в качестве значения group передаем значение null, т.к. при модификации контактак изменить группу нельзя
    // в качестве значения creation передаем false, т.к. на форме редактирования контакта поле new_group отсутствует
    ContactGroupData contact = new ContactGroupData(before.get(before.size() - 1).getId(), "Ivanov", "Ivanovich","Ivan", "USA", "test@mail.ru", "33-33-33", "65-65-65", null, "test1");
    // Редактируем контакт (меняем значения полей);
    // в качестве значения group передаем значение null, т.к. при модификации контактак изменить группу нельзя
    // в качестве значения creation передаем false, т.к. на форме редактирования контакта поле new_group отсутствует
    app.getContactHelper().fillContactForm(contact, false);
    // Нажимаем кнопку "Update"
    app.getContactHelper().updateContact();
    // Возвращаемся к списку всех контактов
    app.getContactHelper().returnToHomePage();

    // Формируем список из контактов после модификации
    List<ContactGroupData> after = app.getContactHelper().getContactList();
    // проверяем, что количество контактов после редактирования не изменилось
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size() - 1);
    before.add(contact);
    // сортируем списки с помощью компаратора и функции sort()
    Comparator<? super ContactGroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    // сортируем старый и новый списки
    before.sort(byId);
    after.sort(byId);
    // сравниваем отсортированные списки с контактами
    Assert.assertEquals(before, after);
  }
}
