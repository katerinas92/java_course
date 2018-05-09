package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactGroupData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper(){
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    // код, отвечающий за извлечение данных из базы данных
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts() {
    // код, отвечающий за извлечение данных из базы данных
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactGroupData> result = session.createQuery("from ContactGroupData where deprecated='0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public ContactGroupData contactById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactGroupData> result = session.createQuery("from ContactGroupData where id = " + id).list();
    System.out.println(result);
    session.getTransaction().commit();
    session.close();
    return result.get(0);
  }

  public Contacts contactsInGroupByName(String groupName) {
    Contacts actualContactsInGroup = new Contacts(); //Сюда будем складывать контакты, которые существуют на данный момент и входят в требуемую группу

    Contacts allContactsInGroup = groupByName(groupName).getContacts(); // Все контакты, которые в группе (существующие и уже удаленные из приложения)

    Session session = sessionFactory.openSession();
    session.beginTransaction();

    for (ContactGroupData contact : allContactsInGroup) {
      // Проверка существует ли сейчас этот контакт
      List<ContactGroupData> resultOfActualityContact = session.createQuery("from ContactData where id = '" + contact.getId()
              + "' and deprecated = '0000-00-00'").list();
      // Если существует, то добавим его
      if (resultOfActualityContact.size() == 1) {
        actualContactsInGroup.add(resultOfActualityContact.get(0));
      }
    }

    session.getTransaction().commit();
    session.close();

    return actualContactsInGroup;
  }

  public GroupData groupByName(String groupName) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData where group_name = '" + groupName + "'" ).list();
    session.getTransaction().commit();
    session.close();
    return result.get(0);
  }
}
