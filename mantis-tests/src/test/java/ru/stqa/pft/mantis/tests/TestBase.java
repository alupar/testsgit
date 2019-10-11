package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.back");
  }

  boolean isIssueNotFixed(int issueId) throws IOException, ServiceException {
    Issue testIssue = app.soap().getIssue(issueId);
    ObjectRef issuestatus = testIssue.getStatus();
    int statusid = issuestatus.getId().intValue();
    String statusname = issuestatus.getName();
    System.out.println(issuestatus);
    if (statusname != "решена"){
      return true;
    } else {
      return false;
    }
  }

    boolean isIssueOpen(int issueId) throws IOException, ServiceException {
      Issue testIssue = app.soap().getIssue(issueId);
      ObjectRef issuestatus = testIssue.getStatus();
      int statusid = issuestatus.getId().intValue();
      String statusname = issuestatus.getName();
      System.out.println(issuestatus);
      if(statusname == "новая"){
        return true;
      }else{
        return false;
      }
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.ftp().restore("config_inc.php.back", "config_inc.php");
    app.stop();
  }

  public void skipIfNotFixed(int issueId) throws IOException, ServiceException {
    if (isIssueNotFixed(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId + " is not fixed");
    }
  }

  public void skipIfOpen(int issueId) throws IOException, ServiceException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId + " is open");
    }
  }

}



