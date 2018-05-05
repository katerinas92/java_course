package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  // Выбор пункта меню "Manage" для создания нового контакта
  public void manage() {
    click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_overview_page.php']"));
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Login']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }
}

