package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroupData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
    Contacts contacts = app.db().contactsInGroupByName(group.getName());
    ContactGroupData contact = contacts.iterator().next();

    app.group().groupSelect(group);
    app.contact().deleteContactFromGroup(contact);
    }

   // метод, который выбирает группу, в которую можно добавить выбранный контакт
  private GroupData selectGroupToTest() {
    GroupData selectedGroup = null;
    Groups allGroups = app.db().groups();
    // Если в какой-то группе есть существующие контакты, то вернем ее
    for (GroupData group : allGroups) {
      if (app.db().contactsInGroupByName(group.getName()).size() > 0) {
        selectedGroup = group;
      }
    }
    return selectedGroup;
  }
}