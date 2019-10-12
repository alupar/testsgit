package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.IOException;

public class UsersAdministrationHelper extends HelperBase {
  public UsersAdministrationHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAsAnybody(String name, String password) throws IOException {
    wd.get(app.getProperty("web.baseUrl"));
  type(By.name("username"), name);
    click(By.cssSelector("input[value='Войти']"));

    type(By.name("password"), password);
    click(By.cssSelector("input[value='Войти']"));
  }


  public void gotoUsersAdministration() {
    click(By.linkText("Управление"));
    click(By.linkText("Управление пользователями"));
  }

  public void selectUser(String username) {
    click(By.linkText(username));
  }

  public void resetPassword() {
    click(By.cssSelector("input[value='Сбросить пароль']"));
  }

  public void finishPwdReset(String confirmationLink, String newpassword, String username) {
    wd.get(confirmationLink);
    type(By.name("realname"),username);
    type(By.name("password"), newpassword);
    type(By.name("password_confirm"), newpassword);
    click(By.cssSelector("button[type='submit']"));

  }

  public void logout() {
    wd.get(app.getProperty("web.logoutUrl"));
  }

  public void changeemail(String username, String email) {
    type(By.name("email"), email.replace(".localdomain", ""));
    click(By.cssSelector("input[value='Изменить пользователя']"));
  }
}
