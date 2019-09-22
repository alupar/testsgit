package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactRemoveFromGroup extends TestBase {
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
  }

  @Test
  public void testContactDeletionFromGroup(){
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData removedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(removedContact.getId()).inGroup(groups.iterator().next());
    if(contact.getGroups().size() == 0){
      app.contact().addtogroup(contact);
    }
    Groups contactgroups = (Groups) contact.getGroups();
    GroupData groupToRemove = contactgroups.iterator().next();
    GroupData group = new GroupData().withId(groupToRemove.getId());
    app.contact().removefromgroup(contact, group);
    }
}
