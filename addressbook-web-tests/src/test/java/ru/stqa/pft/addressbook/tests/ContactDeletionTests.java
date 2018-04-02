package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;

import java.util.List;

// Тест для удаления контакта
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    // Проверяем, есть ли хотя бы один контакт, который можно удалить
    // Если его нет, то
    if (! app.getContactHelper().isThereAContact()) {
      // создаем новый контакт
      app.getContactHelper().createContact(new ContactGroupData("Suslova", "Igorevna", "Ekaterina", "Russia", "e_suslova@mail.ru", "12-12-12", "999-999-999-99", "123-123-456", "test1"));
    }
    // Формируем список из контактов до удаления контакта
    List<ContactGroupData> before = app.getContactHelper().getContactList();
    // Отмечаем чек-боксами контакты для удаления
    app.getContactHelper().selectContact(before.size() - 1);
    // и удаляем их по кнопке "Delete"
    app.getContactHelper().deleteSelectedContacts();
    // Возвращаемся к списку всех контактов; видим, что выбранная группа удалена
    app.getNavigationHelper().goToHomePage();
    // Формируем список из групп после создания новой
    List<ContactGroupData> after = app.getContactHelper().getContactList();
    // проверяем, что количество групп после удаления увеличилось на 1
    Assert.assertEquals(after.size(), before.size() - 1);
    // удаляем последний элемент листа
    before.remove(before.size() - 1);
    // с помощью assertEquals проверяем, что элементы в листах совпадают
    Assert.assertEquals(before, after);
  }
}
