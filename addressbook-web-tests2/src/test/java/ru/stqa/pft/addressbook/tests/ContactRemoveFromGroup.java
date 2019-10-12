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
    Groups groupsbefore = app.db().groups();
    Contacts contactsbefore = app.db().contacts();
    ContactData removedContact = contactsbefore.iterator().next();

    for (ContactData removedContact1 : contactsbefore){
      Groups contactGroups = removedContact1.getGroups();
      if (contactGroups.size() != 0){
        removedContact = removedContact1;
        break;
      }
    }

    if(removedContact.getGroups().size() == 0){
      GroupData groupToAdd = groupsbefore.iterator().next();
      app.contact().addtogroup(removedContact, groupToAdd);
    }

    Groups contactgroups = removedContact.getGroups();
    GroupData grouptoremove = contactgroups.iterator().next();
    ContactData contactbefore = removedContact;
    int beforeid = contactbefore.getId();
    Groups contactgroupsbefore = contactbefore.getGroups();

    app.contact().removefromgroup(removedContact, grouptoremove);

    Contacts allcontactsafter = app.db().contacts();
    ContactData contactafter = null;

    for(ContactData contactafter1 : allcontactsafter){
      if (contactafter1.getId() == beforeid){
        contactafter = contactafter1;
        break;
      }
    }

    assert (contactgroupsbefore.equals(contactafter.getGroups().withAdded(grouptoremove)));
    assert (contactbefore.equals(contactafter));
  }
}
