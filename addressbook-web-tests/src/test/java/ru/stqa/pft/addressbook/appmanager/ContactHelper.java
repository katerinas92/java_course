package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
  public void fillContactForm(ContactGroupData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());
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
}
