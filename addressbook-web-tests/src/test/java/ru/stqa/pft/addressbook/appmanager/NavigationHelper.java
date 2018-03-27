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
    // проверяем наличие заголовка Groups на странице
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
      click(By.linkText("groups"));
  }

  // Выбор пункта меню "home"
  public void goToHomePage() {
    // проверяем наличие на главной странице таблицы maintable со списком всех контактов
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  // Выбор пункта меню "add new" для создания нового контакта
  public void gotoContactPage() {
    click(By.linkText("add new"));
  }
}
