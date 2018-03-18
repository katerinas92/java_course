package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        // Выбираем пункт меню "add new"
        app.getNavigationHelper().gotoContactPage();
        // Заполняем необходимые поля значениями
        app.getGroupHelper().fillContactForm(new ContactGroupData("Suslova", "Igorevna", "Ekaterina", "Russia", "e_suslova@mail.ru", "12-12-12", "999-999-999-99", "123-123-456"));
        // Нажимаем кнопку "Enter" для создания нового контакта
        app.getGroupHelper().submitContactCreation();
        // Возвращаемся к списку всех контактов
        app.getGroupHelper().returnToHomePage();
    }
}