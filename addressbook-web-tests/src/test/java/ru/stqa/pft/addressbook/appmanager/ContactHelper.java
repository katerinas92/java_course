package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactGroupData;

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
  public void selectContact() {
    click(By.name("selected[]"));
  }

  // выбор контактов для удаления и нажатие кнопки "Delete"
  public void deleteSelectedContacts() {
    selectContact();
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    //закрытие диалогового окна (alert)
    wd.switchTo().alert().accept();
  }

  // выбор контакта на редактирование
  public void updateSelectedContacts() {
    selectContact();
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    click(By.xpath("//div[@id='content']/form[1]"));
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
}
