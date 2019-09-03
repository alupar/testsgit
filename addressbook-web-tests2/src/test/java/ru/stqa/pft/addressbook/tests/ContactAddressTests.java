package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

  @Test
  public void testContactAddress(){
    app.contact().returnToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(myAddress(contactInfoFromEditForm)));
  }

  private String myAddress(ContactData contact) {
    String addressedit = contact.getAddress();
    return addressedit;
  }

  public static String cleaned(String address){
    return address.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
