package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class UserPwdChangeTests extends TestBase{
  private String username;
  private String email;
  private String password;
  private Cipher dcipher;

 // @BeforeMethod
 // public void startMailServer(){
 //   app.mail().start();
 // }

  @Test
  public void testUserPwdChange() throws IOException, MessagingException {
    String newpassword = "newpassword";
    String adminname = app.getProperty("web.adminLogin");
    String adminpwd = app.getProperty("web.adminPassword");
    String adminlogin = app.getProperty("mailserver.adminlogin");
    String adminpassword = app.getProperty("mailserver.adminpassword");
    HttpSession session = app.newSession();

    usersetup();
//    userfromdb();

 //   assertTrue(session.login(adminname, adminpwd));
    app.usersadmin().loginAsAnybody(adminname, adminpwd);
    app.usersadmin().gotoUsersAdministration();
    app.usersadmin().selectUser(username);
    app.usersadmin().resetPassword();
    app.usersadmin().logout();

    app.james().doesUserExist(username);
    String username1 = username;
    String password1 = password;
    List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
//    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);

    System.out.println("message = " + mailMessages);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    System.out.println(confirmationLink);
    app.usersadmin().finishPwdReset(confirmationLink, newpassword, username);

    assertTrue(session.login(username, newpassword));
    assertTrue(session.isLoggedIn(username));

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex()
            .find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  private void usersetup(){
    username = "user1569607874295";
    email = "user1569607874295@localhost";
    password = "password";
  }

  public void userfromdb() throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
    Users allusers = app.db().users();
    UserData user = null;
    String adminname1 = "administrator";
    for(UserData user1 : allusers){
      if(user1.getName()!=adminname1) {
        user = user1;
        break;
      }
    }
    username = user.getName();
    email = user.getEmail();
    password = user.getPassword();

  }

    @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}
