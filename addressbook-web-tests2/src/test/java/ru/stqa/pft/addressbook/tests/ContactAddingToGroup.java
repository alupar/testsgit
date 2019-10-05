package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertEquals;

public class ContactAddingToGroup extends TestBase {

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
  public void testContactAddingToGroup(){
    Groups allgroups = app.db().groups();
    Contacts before = app.db().contacts();
    GroupData groupToAdd = allgroups.iterator().next();

    for (ContactData addedContact : before){
      Groups contactGroups = addedContact.getGroups();
      int i = 0;
      for (GroupData groupOfContact : contactGroups) {
        if (groupOfContact.getId() != groupToAdd.getId()) {
          i++;
        }else break;
      }
      if (i == contactGroups.size()){
        int size1 = addedContact.getGroups().size();
        Groups group1 = addedContact.getGroups();
        app.contact().addtogroup(addedContact, groupToAdd);
        int size2 = addedContact.getGroups().size();
        Groups group2 = addedContact.getGroups();
        assertEquals(size1, size2);
        assertEquals(group1, group2);
        break;
      }
    }
  }
}

