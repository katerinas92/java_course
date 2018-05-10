package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

public class ContactAddInGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // Проверяем, есть ли хотя бы один контакт, который можно добавить в группу
    // Если его нет, то
    if (app.db().contacts().size() == 0) {
      // Выбираем пункт меню "add new"
      app.goTo().gotoContactPage();
      // создаем новый контакт
      app.contact().createContact(new ContactGroupData().withFirstname("Suslova").withMiddlename("Igorevna").withLastname("Ekaterina").withAddress("Russia").withEmail1("e_suslova@mail.ru").withHome("12-12-12").withMobile("999-999-999-99").withWork("123-123-456"));
    }

    // Проверяем, есть ли хотя бы одна группа, в которую можно добавить контакт
    // Если ее нет, то
    if(app.db().groups().size() == 0) {
      // Выбираем пункт меню "groups"
      app.goTo().groupPage();
      // Если ее нет, то создаем новую группу
      app.group().create(new GroupData().withName("new_group"));
    }
  }

  @Test
  public void testContactAddInGroup() {

    ContactGroupData contact = selectContactToTest();
    GroupData group = selectGroupToTest(contact);

    Groups contactGroupsBefore = app.db().contactById(contact.getId()).getGroups();
    Contacts groupContactsBefore = app.db().contactsInGroupByName(group.getName());

    app.contact().addToGroup(contact, group);

    Groups contactGroupsAfter = app.db().contactById(contact.getId()).getGroups();
    Contacts groupContactsAfter = app.db().contactsInGroupByName(group.getName());

    // Проверяем изменившиеся списки групп с контактами и контакты с группами на размер и состав данных
    assertEquals(contactGroupsAfter.size(), contactGroupsBefore.size() + 1);
    assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(app.db().groupByName(group.getName()))));
    assertEquals(groupContactsAfter.size(), groupContactsBefore.size() + 1);
    assertThat(groupContactsAfter, equalTo(groupContactsBefore.withAdded(app.db().contactById(contact.getId()))));
    }

    // метод, который выбирает контакт, который можно добавить в группу или создает новый в случае отсутствия подходящего контакта
  private ContactGroupData selectContactToTest() {
    ContactGroupData selectedContact = null;
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    for (ContactGroupData contact : contacts) {
      if (contact.getGroups().size() < groups.size()) {
        selectedContact = contact;
      }
    }

    if (selectedContact == null) {
      app.goTo().gotoContactPage();
      app.contact().createContact(new ContactGroupData().withFirstname("Suslova").withMiddlename("Igorevna").withLastname("Ekaterina").withAddress("Russia").withEmail1("e_suslova@mail.ru").withHome("12-12-12").withMobile("999-999-999-99").withWork("123-123-456"));
      selectedContact = new ContactGroupData().withId(app.db().contacts().stream().mapToInt((g) -> g.getId()).max().getAsInt());
    }
    return selectedContact;
  }

  // метод, который выбирает группу, в которую можно добавить выбранный контакт
  private GroupData selectGroupToTest(ContactGroupData contact) {
    Groups allGroups = app.db().groups();
    Groups contactGroups = app.db().contactById(contact.getId()).getGroups();
    for (GroupData group : contactGroups) {
      allGroups.remove(group);
    }
    return allGroups.iterator().next();
  }
}