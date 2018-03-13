package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

// класс, который помогает выполнять авторизацию в веб-приложении
public class SessionHelper extends HelperBase {

  public SessionHelper(FirefoxDriver wd) {
    super(wd);
  }

  // метод выполняющий ввод логина и пароля
  public void login(String username, String password) {
    type(By.name("user"), username);
    type(By.name("pass"), password);
    click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
