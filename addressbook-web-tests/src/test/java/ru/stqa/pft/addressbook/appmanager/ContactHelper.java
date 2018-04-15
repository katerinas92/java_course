package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactGroupData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

// класс, который помогает работать с контактами
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    // обращение к конструктору базового класса
    super(wd);
  }

  // метод выполняющий нажатие кнопки "Enter" при создании нового контакта
  public void submitContactCreation() {
    click(By.name("submit"));
  }

  // метод выполняющий возвращение на главную страницу после создания группы контактов
  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  // метод выполняющий заполнение полей при создании нового контакта
  // параметр boolean creation принимает на вход 2 значения:
  // true - означает, что это форма создания и там есть поле new_group
  // false - означает, что это форма редактирования и поле new_group отсутствует
  public void fillContactForm(ContactGroupData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());

    if (creation) {
      // выбор элемента из выпадающего списка
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      // проверка, что элемента быть не должно
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    /*
    // проверяем, что на странице есть элемент с локатором "new_group" с помощью метода isElementPresent
    if (isElementPresent(By.name("new_group"))) {
      // выбор элемента из выпадающего списка
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }
    */
  }

  // проставление чекбоксов напротив контактов из списка
  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  // выбор контакта по идентификатору
  public void selectContactById(int id) {
    // находим элемент по локатору и выбираем нужный
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  // выбор контактов для удаления и нажатие кнопки "Delete"
  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    //закрытие диалогового окна (alert)
    wd.switchTo().alert().accept();
  }
  // метод для удаления контакта по идентификатору
  public void delete(ContactGroupData contact) {
    // Отмечаем чек-боксами группы контактов для удаления
    // В качестве index передаем идентификатор, который нужно выбрать
    selectContactById(contact.getId());
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    //закрытие диалогового окна (alert)
    wd.switchTo().alert().accept();
  }

  // выбор контакта на редактирование
  public void updateSelectedContacts() {
    //wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).get(index).click();
    //wd.findElements(By.xpath("//div[@id='content']/form[1]")).get(index).click();
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    //click(By.xpath("//div[@id='content']/form[1]"));
  }

  public void modify(ContactGroupData contact) {
    // выбираем контакт для модификации
    selectContactById(contact.getId());
    // Открываем контакт на редактирование по кнопке с карандашом
    // В качестве index передаем порядковый номер элемента, который нужно выбрать
    updateSelectedContacts();
    // Редактируем контакт (меняем значения полей);
    // в качестве значения group передаем значение null, т.к. при модификации контактак изменить группу нельзя
    // в качестве значения creation передаем false, т.к. на форме редактирования контакта поле new_group отсутствует
    fillContactForm(contact, false);
    // Нажимаем кнопку "Update"
    updateContact();
    // Возвращаемся к списку всех контактов
    returnToHomePage();
  }

  // метод выполняющий нажатие кнопки "update" при модификации контакта
  public void updateContact() {
    click(By.name("update"));
  }

  // метод для проверки наличия элемента на странице
  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  // метод для создания нового контакта
  public void createContact(ContactGroupData contact) {
    // Заполняем необходимые поля значениями; передаем значение группы
    // в качестве значения creation передаем true, т.к. на форме редактирования контакта поле new_group присутствует
    fillContactForm(contact, true);
    // Нажимаем кнопку "Enter" для создания нового контакта
    submitContactCreation();
    // Возвращаемся к списку всех контактов
    returnToHomePage();
  }

  // формируем список из контактов
  public List<ContactGroupData> getContactList() {
    // создаем лист для списка контактов
    List<ContactGroupData> contacts = new ArrayList<ContactGroupData>();
    // заполняем лист значениями с веб-страницы
    // получаем список строк таблицы
    List<WebElement> elements = wd.findElements(By.name("entry"));
    // проходим по всем строкам в цикле
    for (WebElement element : elements) {
      // каждую строку разбиваем на ячейки
      List<WebElement> cells = element.findElements(By.tagName("td"));
      // из этого списка по номеру столбца берем нужные ячейки и получаем их текст
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      // создаем обект ContactGroupData и заполняем его значениями
      ContactGroupData contact = new ContactGroupData().withId(id).withFirstname(firstName).withMiddlename(null).withLastname(lastName).withAddress(null).withEmail(null).withHome(null).withMobile(null).withWork(null).withGroup("test1");
      // добавляем созданный объект в список
      contacts.add(contact);
    }
    // возвращаем лист с контактами
    return contacts;
  }

  // Формируем множество
  public Contacts all() {
    Contacts contacts = new Contacts();
    // заполняем лист значениями с веб-страницы
    // получаем список строк таблицы
    List<WebElement> elements = wd.findElements(By.name("entry"));
    // проходим по всем строкам в цикле
    for (WebElement element : elements) {
      // каждую строку разбиваем на ячейки
      List<WebElement> cells = element.findElements(By.tagName("td"));
      // из этого списка по номеру столбца берем нужные ячейки и получаем их текст
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      // создаем обект ContactGroupData и заполняем его значениями
      ContactGroupData contact = new ContactGroupData().withId(id).withFirstname(firstName).withMiddlename(null).withLastname(lastName).withAddress(null).withEmail(null).withHome(null).withMobile(null).withWork(null).withGroup("test1");
      // добавляем созданный объект в список
      contacts.add(contact);
    }
    // возвращаем множество с контактами
    return contacts;
  }
}
