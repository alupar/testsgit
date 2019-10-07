package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactAddingToGroup extends TestBase {

  GroupData groupToAdd;
  ContactData contactToAdd;

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

    groupToAdd = app.db().groups().iterator().next();
    System.out.println("group = " + groupToAdd.getId());

    for (ContactData contactToAdd1 : app.db().contacts()) {
      Groups contactGroups = contactToAdd1.getGroups();
      int i = 0;
      for (GroupData groupOfContact : contactGroups) {
        if (groupOfContact.getId() != groupToAdd.getId()) {
          i++;
        } else break;
      }
      if (i == contactGroups.size()) break;
      contactToAdd = contactToAdd1;
    }
    System.out.println("contact = " + contactToAdd.getId());
  }

  @Test
  public void testContactAddingToGroup(){
    Groups groupsbefore = app.db().groups();
    Contacts contactsbefore = app.db().contacts();

    app.contact().addtogroup(contactToAdd, groupToAdd);

    Groups groupsafter = app.db().groups();
    Contacts contactsafter = app.db().contacts();

    assertEquals(groupsbefore.size(), groupsafter.size());
    assertEquals(contactsbefore.size(), contactsafter.size());
    assertThat(groupsafter, equalTo(groupsbefore));
    assertThat(contactsafter, equalTo(contactsbefore));

  }
}

