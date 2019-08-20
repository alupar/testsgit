package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests2 extends TestBase {

  @Test
  public void testGroupCreation2() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test111", null, null));
  }

}
