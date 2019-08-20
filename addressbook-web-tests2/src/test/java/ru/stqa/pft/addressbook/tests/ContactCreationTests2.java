package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests2 extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().gotoContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("first2", "last2", "nick2", "company2", "address2", "12345", "test111"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }

}
