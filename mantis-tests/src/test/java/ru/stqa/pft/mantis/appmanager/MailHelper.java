package ru.stqa.pft.mantis.appmanager;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {

  private ApplicationManager app;
  private final Wiser wiser;

  public MailHelper(ApplicationManager app) {
    this.app = app;
    // при инициализации создается объект типа Wiser. Это и есть почтовый сервер
    wiser = new Wiser();
  }

  // метод ожидания поступления новых писем
  // count - это количество писем, которое должно прийти; timeout - задает время ожидания
  public List<MailMessage> waitForMail(int count, long timeout) throws MessagingException, IOException {
    // запоминаем текущее время
    long start = System.currentTimeMillis();
    // с помощью цикла while проверяем, что новое текущее время не превышает момент старта + timeout
    while (System.currentTimeMillis() < start + timeout) {
      // если почты пришло достаточно много
      if (wiser.getMessages().size() >= count) {
        // прекращаем ожидание; преобразовываем реальные объекты в модельные
        return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new Error("No mail :(");
  }

  private MailMessage toModelMail(WiserMessage m) {
    try {
      // берем список получателей
      MimeMessage mm = m.getMimeMessage();
      // оставляем первого из них
      return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
    } catch (javax.mail.MessagingException e) {
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  // запускаем почтовый сервер
  public void start() {
    wiser.start();
  }

  // останавливаем почтовый сервер
  public void stop() {
    wiser.stop();
  }
}
