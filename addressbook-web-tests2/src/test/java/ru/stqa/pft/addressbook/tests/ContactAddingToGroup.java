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

    for (ContactData contactToAdd1 : allcontacts) {
      Groups contactGroups = contactToAdd1.getGroups();
      if(contactGroups.size()!= allgroups.size()) {
        allgroups.removeAll(contactGroups);
        groupToAdd = allgroups.iterator().next();
        contactToAdd = contactToAdd1;
        break;
      }
    }
  }

  @Test
  public void testContactAddingToGroup(){
    ContactData contactbefore = contactToAdd;
    Groups contactgroupsbefore = contactToAdd.getGroups();
    app.contact().addtogroup(contactToAdd, groupToAdd);
    Groups contactgroupsafter = contactToAdd.getGroups();
    assert (contactgroupsbefore.equals(contactgroupsafter.without(groupToAdd)));
    assert (contactbefore.equals(contactToAdd));

//   assertThat(contactgroupsbefore, equalTo(contactgroupsafter.without(groupToAdd)));
//    assertEquals(contactgroupsbefore.size(), contactgroupsafter.without(groupToAdd).size());

  }
}

