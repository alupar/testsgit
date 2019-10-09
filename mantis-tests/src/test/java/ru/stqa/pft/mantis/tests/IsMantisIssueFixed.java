package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.Set;

public class IsMantisIssueFixed extends TestBase {
  private int myid1;
  private int myprojectid1;

  @BeforeMethod
  public void getIssueAndProjectIds() throws IOException, ServiceException {
    Set<Project> projects = app.soap().getProjects();
    Project project = projects.iterator().next();
    Set<Issue> Issues = app.soap().getIssues(project);
    Issue myissue1 = Issues.iterator().next();
    myid1 = myissue1.getId();
    myprojectid1 = project.getId();
  }

  @Test
  private void IsMantisIssueFixed() throws IOException, ServiceException {
    System.out.println("Id = " + myid1);
    skipIfNotFixed(myid1);
  }

  @Test
  private void IsMantisIssueOpen() throws IOException, ServiceException {
    System.out.println("Id = " + myid1);
    skipIfOpen(myid1);
  }
}
