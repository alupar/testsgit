package ru.stqa.pft.rest;

import org.testng.SkipException;

public class TestBase {
  boolean isIssueFixed(int issueId){
    return true;
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueFixed(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
