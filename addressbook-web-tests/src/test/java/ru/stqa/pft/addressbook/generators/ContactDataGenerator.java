package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactGroupData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactGroupData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  // генерируем список данных
  private List<ContactGroupData> generateContacts(int count) {
    List<ContactGroupData> contacts = new ArrayList<ContactGroupData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactGroupData().withFirstname(String.format("Morozova %s", i))
              .withLastname(String.format("Maria %s", i)).withMiddlename(String.format("Andreevna %s", i)).withEmail1(String.format("test@mail.ru %s", i)).withHome(String.format("22-33-44 %s", i)).withMobile(String.format("11-00-02 %s", i)).withWork(String.format("77777777-8 %s", i)));
    }
    return contacts;
  }

  // сохраняем в файл XML; используется библиотека XSTREAM
  private void saveAsXml(List<ContactGroupData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    // прочитать подсказки, которые прописаны в классе GroupData в @XStreamAlias
    xstream.processAnnotations(ContactGroupData.class);
    String xml = xstream.toXML(contacts);
    // если используется конструкция try, то writer.close() можно не прописывать, закрытие writer-а произойдет автоматически
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  // сохраняем в файл JSON; используется библиотека GSON от Google
  private void saveAsJson(List<ContactGroupData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  // сохраняем в файл CSV
  // throws IOException означает, что во время работы метода может возникнуть искл.ситуация и обработка должна быть в main
  private void saveAsCsv(List<ContactGroupData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)) {
      // проходим в цикле и записываем данные
      for (ContactGroupData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s\n", contact.getFirstname(), contact.getMiddlename(), contact.getLastname(), contact.getGroups().iterator().next().getName()));
      }
    }
  }
}
