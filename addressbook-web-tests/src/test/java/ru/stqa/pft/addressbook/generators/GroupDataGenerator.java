package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.GroupData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

// класс для генерации данных для групп
public class GroupDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    // путь к файлу
    File file = new File(args[1]);

    List<GroupData> groups = generateGroups(count);
    save(groups, file);

  }

  // генерируем список данных
  private static List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData().withName(String.format("test %s", i))
              .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
    }
    return groups;
  }

  // сохраняем в файл
  // throws IOException означает, что во время работы метода может возникнуть искл.ситуация и обработка должна быть в main
  private static void save(List<GroupData> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    // проходим в цикле и записываем данные
    for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
    }
    // закрываем файл
    writer.close();
  }

}
