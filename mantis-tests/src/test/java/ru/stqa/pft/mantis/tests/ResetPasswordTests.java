package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testResetPasswordTests() throws IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
    /*String email = "userfortest@localhost";
    String user = "userfortest";*/
    String newPassword = "newpassword";

    List<UserData> users = app.db().users();
    UserData user = null;
    for (UserData u : users) {
      if (!u.getUsername().equals("administrator")) {
        user = u;
        break;
      }
    }

    app.goTo().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.goTo().manage();
    app.manage().resetUserPassword(user.getUsername());

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
    app.manage().confirmNewPassword(confirmationLink, newPassword);
    assertTrue(app.newSession().login(user.getUsername(), newPassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

   @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}