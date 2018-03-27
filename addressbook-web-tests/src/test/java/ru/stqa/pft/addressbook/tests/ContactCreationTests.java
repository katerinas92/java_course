package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;

// Тест для создания нового контакта
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        // Выбираем пункт меню "add new"
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().createContact(new ContactGroupData("Suslova", "Igorevna", "Ekaterina", "Russia", "e_suslova@mail.ru", "12-12-12", "999-999-999-99", "123-123-456", "test1"));
    }
}