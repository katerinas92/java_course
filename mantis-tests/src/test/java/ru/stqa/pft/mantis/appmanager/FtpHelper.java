package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {
  private final ApplicationManager app;
  private FTPClient ftp;

  // метод инициализации объекта
  public FtpHelper(ApplicationManager app) {
    this.app = app;
    // создается FTPClient, который будет устанавливать соединение, передавать файлы и т.д.
    ftp = new FTPClient();
  }

  // метод, который загружает новый файл, а старый временно переименовывает
  public void upload(File file, String target, String backup) throws IOException {
    // устанавливаем соединение с удаленным сервером
    ftp.connect(app.getProperty("ftp.host"));
    // выполняем логин
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    // сперва удаляем резервную копию файла
    ftp.deleteFile(backup);
    // затем переименовываем удаленный файл
    ftp.rename(target, backup);
    // включается пассивный режим передачи данных (техническая манипуляция, связанная с ограничением ftp-сервера, используемом в лекции 8.5)
    ftp.enterLocalPassiveMode();
    // передается файл. FileInputStream - побайтовое чтение данных. Данные читаются из локального файла, передаются на удаленную машину и там сохраняются в удаленном файле target
    ftp.storeFile(target, new FileInputStream(file));
    // после окончания передачи данных соединение разрывается
    ftp.disconnect();
  }

  // метод, который восстанавливает название старого файла
  public void restore(String target, String backup) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(target);
    ftp.rename(backup, target);
    ftp.disconnect();
  }
}
