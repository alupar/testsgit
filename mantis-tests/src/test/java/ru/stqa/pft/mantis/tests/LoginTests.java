package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import java.io.IOException;
import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.firstlogin("administrator"));
    assertTrue(session.secondlogin("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
