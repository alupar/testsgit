package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests2 extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    gotoContactCreation();
    fillContactForm(new ContactData("first2", "last2", "nick2", "company2", "address2", "12345"));
    submitContactCreation();
    returnToHomePage();
  }

}
