package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      // получаем значение поля ввода
      String existingText = wd.findElement(locator).getAttribute("value");
      // сравниваем значения; если текст не совпадает с уже заполненным полем
      if (! text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected boolean isElementPresent(By locator) {
    // пробуем найти элемент
    try {
      // проверяем есть ли элемент на странице с переданным локатором
      wd.findElement(locator);
      // если есть, то передаем true, иначе идем в условие catch
      return true;
    }
    // если такого элемента нет, то выбрасывается исключение false
    catch (NoSuchElementException ex) {
      return false;
    }
  }
}
