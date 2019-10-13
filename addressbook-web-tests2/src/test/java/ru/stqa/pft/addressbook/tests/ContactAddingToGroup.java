package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAddingToGroup extends TestBase {

  private GroupData groupToAdd;
  private ContactData contactToAdd;
  private int groupToAddId;
  private int contactToAddId;

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().returnToHomePage();
    if(app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstname("first2").withLastname("last2").withNickname("nick2").withCompany("company2").withAddress("address2")
              .withMobilephone("12345").withHomephone("22").withWorkphone("333")
              .withFirstemail("w@ww.ww").withSecondemail("e@e.e").withThirdemail("q@q.q"));
    }

    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test111").withFooter("footer1").withHeader("header1"));
    }

    Contacts allcontacts = app.db().contacts();
    Groups allgroups = app.db().groups();
    groupToAdd = null;

    for (ContactData contactToAdd1 : allcontacts) {
      Groups contactGroups = contactToAdd1.getGroups();
      if(contactGroups.size()!= allgroups.size()) {
        allgroups.removeAll(contactGroups);
        groupToAdd = allgroups.iterator().next();
        contactToAdd = contactToAdd1;
        break;
      }
    }

    if(groupToAdd == null){
      ContactData newcontact = new ContactData().withFirstname("first9").withLastname("last9").withNickname("nick9").withCompany("company9").withAddress("address9")
              .withMobilephone("12345").withHomephone("2992").withWorkphone("999")
              .withFirstemail("w@ww.ww").withSecondemail("e@e.e").withThirdemail("q@q.q");
      Contacts before = app.db().contacts();
      app.contact().create(newcontact);
      Contacts after = app.db().contacts();
      newcontact.withId(after.stream().mapToInt((g) -> (g).getId()).max().getAsInt());
      int newid = newcontact.getId();
      contactToAdd = newcontact;
      groupToAdd = allgroups.iterator().next();
    }

  }

  @Test
  public void testContactAddingToGroup(){
    int beforeid = contactToAdd.getId();
    Contacts allcontactsbefore = app.db().contacts();
    ContactData contactbefore = null;

    for(ContactData contactbefore1 : allcontactsbefore){
      if (contactbefore1.getId() == beforeid){
        contactbefore = contactbefore1;
        break;
      }
    }
    Groups before = contactbefore.getGroups();

    app.contact().addtogroup(contactToAdd, groupToAdd);

    Contacts allcontactsafter = app.db().contacts();
    ContactData contactafter = null;

    for(ContactData contactafter1 : allcontactsafter){
      if (contactafter1.getId() == beforeid){
        contactafter = contactafter1;
        break;
      }
    }
    Groups after = contactafter.getGroups();

    assert (after.equals(before.withAdded(groupToAdd)));
  }
}

