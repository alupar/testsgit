package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().returnToHomePage();
    if(app.contact().list().size() == 0){
      app.contact().create(new ContactData("first2", "last2", "nick2", "company2", "address2", "12345", "test111"));
    }
  }

  @Test
    public void testContactModification(){
    List<ContactData> before = app.contact().list();
    int indexc = before.size() - 1;
    ContactData contact = new ContactData(before.get(indexc).getId(),"first2", "last2", "nick2", "company2", "address2", "12345", null);

    app.contact().modify(before, indexc, contact);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(indexc);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
