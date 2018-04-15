package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactGroupData> {

  private Set<ContactGroupData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactGroupData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactGroupData>();
  }

  @Override
  protected Set<ContactGroupData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactGroupData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts withOut(ContactGroupData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
