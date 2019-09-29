package ru.stqa.pft.addressbook.model;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")

public class ContactData {
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "nickname")
  private String nickname;

  @Column(name = "company")
  private String company;

  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Column(name = "home")
  @Type(type = "text")
  private String homephone;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilephone;

  @Column(name = "work")
  @Type(type = "text")
  private String workphone;

  @Transient
  private String allPhones;

  @Column(name = "email")
  @Type(type = "text")
  private String firstemail;

  @Column(name = "email2")
  @Type(type = "text")
  private String secondemail;

  @Column(name = "email3")
  @Type(type = "text")
  private String thirdemail;

  @Transient
  private String allemails;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany (fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (homephone != null ? !homephone.equals(that.homephone) : that.homephone != null) return false;
    if (mobilephone != null ? !mobilephone.equals(that.mobilephone) : that.mobilephone != null) return false;
    if (workphone != null ? !workphone.equals(that.workphone) : that.workphone != null) return false;
    if (firstemail != null ? !firstemail.equals(that.firstemail) : that.firstemail != null) return false;
    if (secondemail != null ? !secondemail.equals(that.secondemail) : that.secondemail != null) return false;
    return thirdemail != null ? thirdemail.equals(that.thirdemail) : that.thirdemail == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (homephone != null ? homephone.hashCode() : 0);
    result = 31 * result + (mobilephone != null ? mobilephone.hashCode() : 0);
    result = 31 * result + (workphone != null ? workphone.hashCode() : 0);
    result = 31 * result + (firstemail != null ? firstemail.hashCode() : 0);
    result = 31 * result + (secondemail != null ? secondemail.hashCode() : 0);
    result = 31 * result + (thirdemail != null ? thirdemail.hashCode() : 0);
    return result;
  }

  public File getPhoto() {
    if (photo != null) {

      return new File(photo);

    } else {

      return null;

    }
   }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
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

  public void withGroups(Set<GroupData> groups) {
    this.groups = groups;
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

  public Groups getGroups() {
    return new Groups(groups);
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

  private Object readResolve() {
    groups = new HashSet<GroupData>();
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
