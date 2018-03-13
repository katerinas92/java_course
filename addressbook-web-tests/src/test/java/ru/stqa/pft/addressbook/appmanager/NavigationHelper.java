package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

// класс, который помогает работать с меню

public class NavigationHelper extends HelperBase {

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  // Выбор пункта меню "groups"
  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }
}
