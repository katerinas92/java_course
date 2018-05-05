package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

  @Test
  public void testResetPasswordTests() throws IOException, MessagingException {
    String email = "userfortest@localhost";
    String user = "userfortest";
    String password = "password";
    String newPassword = "newpassword";

    app.goTo().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.goTo().manage();
    app.manage().resetUserPassword(user);

    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.manage().confirmNewPassword(confirmationLink, newPassword);
    assertTrue(app.newSession().login(user, newPassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}