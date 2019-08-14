package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests2 extends TestBase {

  @Test
  public void testGroupCreation2() throws Exception {

    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test111", "test222", "test333"));
    app.submitGroupCreation();
    app.returnToGroupPage();
    app.wd.findElement(By.linkText("Logout")).click();
  }

}
