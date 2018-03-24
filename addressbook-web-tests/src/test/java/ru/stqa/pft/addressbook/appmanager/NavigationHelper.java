package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// класс, который помогает работать с меню

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  // Выбор пункта меню "groups" для создания новой группы контактов
  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  // Выбор пункта меню "home"
  public void goToHomePage() {
    click(By.linkText("home"));
  }

  // Выбор пункта меню "add new" для создания нового контакта
  public void gotoContactPage() {
    click(By.linkText("add new"));
  }
}
