package ru.stqa.pft.addressbook.model;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("contact")
public class ContactData {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String nickname;
  private String company;
  private String address;
  private String homephone;
  private String mobilephone;
  private String workphone;
  private String allPhones;
  private String firstemail;
  private String secondemail;
  private String thirdemail;
  private String allemails;
  private String group;
  private File photo;

  public File getPhoto() {
    return photo;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomephone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public ContactData withMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public ContactData withWorkphone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withFirstemail(String firstemail) {
    this.firstemail = firstemail;
    return this;
  }

  public ContactData withSecondemail(String secondemail) {
    this.secondemail = secondemail;
    return this;
  }

  public ContactData withThirdemail(String thirdemail) {
    this.thirdemail = thirdemail;
    return this;
  }

  public ContactData withAllemails(String allEmails) {
    this.allemails = allEmails;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", company='" + company + '\'' +
            ", address='" + address + '\'' +
            ", homephone='" + homephone + '\'' +
            ", mobilephone='" + mobilephone + '\'' +
            ", workphone='" + workphone + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", firstemail='" + firstemail + '\'' +
            ", secondemail='" + secondemail + '\'' +
            ", thirdemail='" + thirdemail + '\'' +
            ", allemails='" + allemails + '\'' +
            ", group='" + group + '\'' +
            '}';
  }


  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getGroup() {
    return group;
  }

  public String getFirstemail() {
    return firstemail;
  }

  public String getSecondemail() {
    return secondemail;
  }

  public String getThirdemail() {
    return thirdemail;
  }

  public String getAllemails() {
    return allemails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}
