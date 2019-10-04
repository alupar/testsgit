package ru.stqa.pft.rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

public class TestBase {
  boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%d.json", issueId)))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    JsonArray issues1 = issues.getAsJsonArray();
    String state_name = null;

    for (JsonElement issue1 : issues1 ){
      state_name = issue1.getAsJsonObject().get("state_name").getAsString();
    }
    if(state_name == "open"){
      return true;
    }else{
      return false;
    }

  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private Executor getExecutor(){
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }
}

