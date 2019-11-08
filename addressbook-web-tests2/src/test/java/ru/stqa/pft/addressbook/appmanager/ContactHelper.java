package ru.stqa.pft.addressbook.appmanager;
importimport org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }


  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
 //   type(By.name("nickname"), contactData.getNickname());
//    attach(By.name("photo"),contactData.getPhoto());
 //   type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("email"), contactData.getFirstemail());
    type(By.name("email2"), contactData.getSecondemail());
    type(By.name("email3"), contactData.getThirdemail());

    Set<GroupData> group1 = contactData.getGroups();
    if (creation){
      if (group1.size() != 0) {
        GroupData selectedGroup = group1.iterator().next();
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
 //       new Select(wd.findElement(By.name("new_group"))).selectByIndex(selectedGroup.getId());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void gotoContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void addSelectedContactToGroup(ContactData contact, GroupData group1) {
    String name = group1.getName();
//    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group1.getName());
    String myvalue = String.valueOf(group1.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByValue(myvalue);
    click(By.xpath("//input[@value='Add to']"));
//    wd.switchTo().alert().accept();
 //   wd.findElement(By.cssSelector("div.msgbox"));
    System.out.println("Added contact " + contact.getId() + " to a group " + group1.getId() + " " + group1.getName());
  }

  private void deleteSelectedContactFromGroup(String groupname) {
//    click(By.xpath("//input[@value='Remove from \"" + groupname + "\"']"));
    click(By.xpath("//input[@name='remove']"));
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initContactModificationById(int index) {
    click(By.xpath("//a[@href='edit.php?id=" + index + "']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact) {
    gotoContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    returnToHomePage();
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }

  public void addtogroup(ContactData contact, GroupData groupToAdd) {
    returnToHomePage();
    selectContactById(contact.getId());
    addSelectedContactToGroup(contact, groupToAdd);

    returnToHomePage();
  }

  public void removefromgroup(ContactData contact, GroupData group1) {
    selectContactById(contact.getId());
    String myvalue1 = String.valueOf(group1.getId());
    new Select(wd.findElement(By.name("group"))).selectByValue(myvalue1);
    selectContactById(contact.getId());
    String groupname = group1.getName();
    deleteSelectedContactFromGroup(groupname);
    System.out.println("Removed contact " + contact.getId() + " from a group " + group1.getId() + " " + group1.getName());
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements){
      List<WebElement> cells = element.findElements(By.cssSelector("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
      .withAddress(address).withAllPhones(allPhones).withAllemails(allEmails));
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String firstmail = wd.findElement(By.name("email")).getAttribute("value");
    String secondmail = wd.findElement(By.name("email2")).getAttribute("value");
    String thirdmail = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withAddress(address)
            .withHomephone(home).withMobilephone(mobile).withWorkphone(work)
            .withFirstemail(firstmail).withSecondemail(secondmail).withThirdemail(thirdmail);
  }


}
