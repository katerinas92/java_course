package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ManageHelper extends HelperBase {

  public ManageHelper(ApplicationManager app) {
    super(app);
  }

  public void resetUserPassword(String username) {
    click(By.linkText("Manage Users"));
    waitLocator(By.name("username"));
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Manage User']"));
    waitLocator(By.cssSelector("input[value='Reset Password']"));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void confirmNewPassword(String link, String newPassword) {
    wd.get(link);
    waitLocator(By.name("password"));
    type(By.name("password"), newPassword);
    type(By.name("password_confirm"), newPassword);
    waitLocator(By.cssSelector("input[value='Update User']"));
    click(By.cssSelector("input[value='Update User']"));
  }
}
