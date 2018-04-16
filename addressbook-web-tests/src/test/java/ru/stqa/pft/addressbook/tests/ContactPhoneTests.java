package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhones() {
    app.goTo().goToHomePage();
    ContactGroupData contact = app.contact().all().iterator().next();
    ContactGroupData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    // сравниваем "склееные" объекты emails
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    // сравниваем "склееные" объекты phones
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    // сравниваем адрес
    assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactGroupData contact) {
    // формируем коллекцию; фильтруем и склеиваем
    return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactGroupData contact) {
    // формируем коллекцию; фильтруем и склеиваем
    return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergeAddress(ContactGroupData contact) {
    // формируем коллекцию; фильтруем и склеиваем
    return Arrays.asList(contact.getAddress())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  // удаляем "лишние символы"
  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
