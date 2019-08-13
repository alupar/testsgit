package ru.stqa.pft.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;

public class GroupCreationTests2 extends TestBase {

  @Test
  public void testGroupCreation2() throws Exception {

    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test111", "test222", "test333"));
    submitGroupCreation();
    returnToGroupPage();
    wd.findElement(By.linkText("Logout")).click();
  }

}
