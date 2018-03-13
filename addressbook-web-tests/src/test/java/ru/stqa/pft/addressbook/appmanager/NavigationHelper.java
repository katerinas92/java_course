package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

// класс, который помогает работать с меню

public class NavigationHelper {

  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  // Выбор пункта меню "groups"
  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }
}
