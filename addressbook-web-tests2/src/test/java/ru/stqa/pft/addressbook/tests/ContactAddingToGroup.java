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

    GroupData groupToAdd = app.db().groups().iterator().next();
    groupToAddId = groupToAdd.getId();
    System.out.println("group = " + groupToAdd.getId());
    Contacts allcontacts = app.db().contacts();

    for (ContactData contactToAdd1 : allcontacts) {
      Groups contactGroups = contactToAdd1.getGroups();
      int i = 0;
        for (GroupData groupOfContact : contactGroups){
          if (groupOfContact.getId() == groupToAdd.getId()){
            i=1;
          }
        }
        if (i != 1){
          ContactData contactToAdd = contactToAdd1;
          contactToAddId = contactToAdd.getId();
          System.out.println("contact = " + contactToAddId);
          break;
        }
    }
    System.out.println("contact = " + contactToAddId);
  }

  @Test
  public void testContactAddingToGroup(){
    ContactData contactToAdd = new ContactData().withId(contactToAddId);
    GroupData groupToAdd = new GroupData().withId(groupToAddId);

    Groups contactgroupsbefore = contactToAdd.getGroups();
    app.contact().addtogroup(contactToAdd, groupToAdd);
    Groups contactgroupsafter = contactToAdd.getGroups();
    assertThat(contactgroupsbefore, equalTo(contactgroupsafter.withAdded(groupToAdd)));
    assertEquals(contactgroupsbefore.size()+1, contactgroupsafter.size());

  }
}

