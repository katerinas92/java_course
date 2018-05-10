package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class ContactDeleteFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // получаем список всех групп из базы данных
    Groups groups = app.db().groups();
    // Проверяем, есть ли хотя бы один контакт, который можно удалить из группы
    // Если его нет, то
    if (app.db().contacts().size() == 0) {
      // Выбираем пункт меню "add new"
      app.goTo().gotoContactPage();
      // создаем новый контакт
      app.contact().createContact(new ContactGroupData().withFirstname("Suslova").withMiddlename("Igorevna").withLastname("Ekaterina").withAddress("Russia").withEmail1("e_suslova@mail.ru").withHome("12-12-12").withMobile("999-999-999-99").withWork("123-123-456").inGroup(groups.iterator().next()));
    }

    // Проверяем, есть ли хотя бы одна группа, из которой можно удалить контакт
    // Если ее нет, то
    if(app.db().groups().size() == 0) {
      // Выбираем пункт меню "groups"
      app.goTo().groupPage();
      // создаем новую группу
      app.group().create(new GroupData().withName("new_group"));
    }
  }

  @Test
  public void testContactDeleteFromGroup() {
    GroupData group = selectGroupToTest();
    Contacts groupContactsBefore = app.db().contactsInGroupByName(group.getName());

    ContactGroupData contact = groupContactsBefore.iterator().next();
    Groups contactGroupsBefore = app.db().contactById(contact.getId()).getGroups();

    app.group().groupSelect(group);
    app.contact().deleteContactFromGroup(contact);

    Contacts groupContactsAfter = app.db().contactsInGroupByName(group.getName());
    Groups contactGroupsAfter = app.db().contactById(contact.getId()).getGroups();

    // Проверяем изменившиеся списки групп с контактами и контакты с группами на размер и состав данных
    assertEquals(contactGroupsAfter.size(), contactGroupsBefore.size() - 1);
    assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withOut(app.db().groupByName(group.getName()))));
    assertEquals(groupContactsAfter.size(), groupContactsBefore.size() - 1);
    assertThat(groupContactsAfter, equalTo(groupContactsBefore.withOut(app.db().contactById(contact.getId()))));
    }

   // метод, который выбирает группу, из которой можно удалить контакт
  private GroupData selectGroupToTest() {
    GroupData selectedGroup = null;
    Groups allGroups = app.db().groups();
    // Если в какой-то группе есть существующие контакты, то вернем ее
    for (GroupData group : allGroups) {
      if (app.db().contactsInGroupByName(group.getName()).size() > 0) {
        selectedGroup = group;
      } else {
        GroupData groupWithContact = allGroups.iterator().next();
        Contacts contacts = app.db().contacts();
        app.contact().addToGroup(contacts.iterator().next(), groupWithContact);
        selectedGroup = groupWithContact;
        break;
      }
    }
    return selectedGroup;
  }
}