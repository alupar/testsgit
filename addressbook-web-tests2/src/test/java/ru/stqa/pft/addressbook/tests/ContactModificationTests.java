package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().returnToHomePage();
    if(app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstname("first2").withLastname("last2").withAddress("address2")
              .withHomephone("12345").withMobilephone("12345").withWorkphone("12345")
              .withFirstemail("qq@qq.qq").withSecondemail("ww@ww.ww").withThirdemail("ee@ee.ee"));
    }
  }

  @Test
    public void testContactModification(){
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    int newid = modifiedContact.getId();
    ContactData contact = new ContactData().withId(newid)
            .withFirstname("first2").withLastname("last2").withAddress("address2")
            .withHomephone("12345").withMobilephone("12345").withWorkphone("12345")
            .withFirstemail("qq@qq.qq").withSecondemail("ww@ww.ww").withThirdemail("ee@ee.ee");
    app.contact().modify(contact);

    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
