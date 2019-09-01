package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

@Test
public class ContactPhoneTests extends TestBase{
  app.goTo().gotoHomePage();
  app.contact().returnToHomePage();
  ContactData contact = app.contact().all().iterator().next();
  ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
}
