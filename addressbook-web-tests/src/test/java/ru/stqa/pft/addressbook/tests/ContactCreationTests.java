package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;

// Тест для создания нового контакта
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        // Выбираем пункт меню "add new"
        app.getNavigationHelper().gotoContactPage();
        // Заполняем необходимые поля значениями
        app.getContactHelper().fillContactForm(new ContactGroupData("Suslova", "Igorevna", "Ekaterina", "Russia", "e_suslova@mail.ru", "12-12-12", "999-999-999-99", "123-123-456"));
        // Нажимаем кнопку "Enter" для создания нового контакта
        app.getContactHelper().submitContactCreation();
        // Возвращаемся к списку всех контактов
        app.getContactHelper().returnToHomePage();
    }
}