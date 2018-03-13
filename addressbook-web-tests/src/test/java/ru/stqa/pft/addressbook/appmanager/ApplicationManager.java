package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  FirefoxDriver wd;

  // то, что осталось после замены наследования на делегирование
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;

  public void init() {
    wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost:8081/addressbook/");
    // чтобы класс GroupHelper получил доступ к этому драйверу, передаем ссылку в конструктор
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin","secret");
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
