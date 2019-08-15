package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().returnToHomePage();
  }

  private boolean isElementPresent(By by) {
    try {
      app.getGroupHelper().wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

}
