package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

  // передаем название браузера
  // параметр static значает, что app будет доступен для всех тестов, запускаемых одновременно в BeforeSuite
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }

  // функция, которая через Remote API получает из баг-трекера информацию о баг-репорте с заданным идентификатором
  // и возвращает значение false или true в зависимости от того, помечен он как исправленный или нет
  private boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    return (app.soap().getStatusIssue(issueId).equals("closed"));
  }

  // функция, которая пропускает тест, если баг ещё не исправлен
  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}