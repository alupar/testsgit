package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().returnToHomePage();
    if(app.contact().list().size() == 0){
      app.contact().create(new ContactData().withFirstname("first2").withLastname("last2").withNickname("nick2").withCompany("company2").withAddress("address2").withMobilephone("12345").withGroup("test111"));
    }
  }

  @Test
  public void testContactDeletion() throws Exception {

    List <ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), index);

    before.remove(index);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
