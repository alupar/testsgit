package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertEquals;

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
    ContactData contact = new ContactData().withId(removedContact.getId());
    Groups group1 = removedContact.getGroups();

    if(contact.getGroups().size() == 0){
      GroupData groupToAdd = groups.iterator().next();
      app.contact().addtogroup(removedContact, groupToAdd);
    }

    app.contact().removefromgroup(removedContact, groups);
    Groups group2 = removedContact.getGroups();
    assertEquals(group1.size(), group2.size());
    assertEquals(group1, group2);
    }
}
