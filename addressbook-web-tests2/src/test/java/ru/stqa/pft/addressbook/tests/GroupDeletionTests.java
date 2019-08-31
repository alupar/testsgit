package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0){
      app.group().create(new GroupData().withName("test111"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(deletedGroup)));
  }


}
