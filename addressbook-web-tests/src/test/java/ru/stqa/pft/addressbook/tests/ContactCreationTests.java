package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;

import java.util.Comparator;
import java.util.List;

// Тест для создания нового контакта
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        // Формируем список из контактов до создания нового
        List<ContactGroupData> before = app.getContactHelper().getContactList();
        // Задаем значения для нового контакта
        ContactGroupData contact = new ContactGroupData("Suslova", "Igorevna", "Ekaterina", "Russia", "e_suslova@mail.ru", "12-12-12", "999-999-999-99", "123-123-456", "test1");
        // Выбираем пункт меню "add new"
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().createContact(contact);
        // Формируем список из контактов после создания нового
        List<ContactGroupData> after = app.getContactHelper().getContactList();
        // проверяем, что количество контактов после добавления увеличилось на 1
        Assert.assertEquals(after.size(), before.size() + 1);
        // добавляем новый контакт в лист before
        before.add(contact);
        // сортируем списки с помощью компаратора и функции sort()
        Comparator<? super ContactGroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        // сортируем старый и новый списки
        before.sort(byId);
        after.sort(byId);
        // сравниваем отсортированные списки контактов
        Assert.assertEquals(before, after);
    }
}