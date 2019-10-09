package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
  private ApplicationManager app;
  public SoapHelper(ApplicationManager app){
    this.app = app;
  }

  public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    return Arrays.asList(projects).stream()
            .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
  }

  public Set<Issue> getIssues(Project project) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData[] issues = mc.mc_project_get_issues("administrator", "root", BigInteger.valueOf(project.getId()), BigInteger.valueOf(0), BigInteger.valueOf(100));
    return Arrays.asList(issues).stream()
            .map((i) -> new Issue().withId(i.getId().intValue()).withSummary(i.getSummary()).withDescritpion(i.getDescription())
                    .withProject(project))
            .collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
              .getMantisConnectPort(new URL("http://localhost/mantisbt-2.22.0/api/soap/mantisconnect.php"));
  }

  public Issue getIssue(int issueid) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData issueData = new IssueData();
    IssueData myIssue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueid));
    return new Issue().withId(myIssue.getId().intValue()).withSummary(myIssue.getSummary())
            .withDescritpion(myIssue.getDescription()).withStatus(myIssue.getStatus())
            .withProject(new Project().withId(myIssue.getProject().getId().intValue())
                    .withName(myIssue.getProject().getName()));
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescritpion());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
    IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary()).withDescritpion(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                      .withName(createdIssueData.getProject().getName()));

  }
}
