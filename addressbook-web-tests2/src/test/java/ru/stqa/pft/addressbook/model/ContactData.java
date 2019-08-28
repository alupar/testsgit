package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int id;
  private final String firstname;
  private final String lastname;
  private final String nickname;
  private final String company;
  private final String address;
  private final String mobilephone;
  private String group;

  public ContactData(String firstname, String lastname, String nickname, String company, String address, String mobilephone, String group) {
    this.id = 0;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.company = company;
    this.address = address;
    this.mobilephone = mobilephone;
    this.group = group;
  }

  public ContactData(int id, String name, String firstname, String lastname, String nickname, String company, String address, String mobilephone, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.company = company;
    this.address = address;
    this.mobilephone = mobilephone;
    this.group = group;
  }


  public void setId(int id) {
    this.id = id;
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
            ", mobilephone='" + mobilephone + '\'' +
            ", group='" + group + '\'' +
            '}';
  }

  public ContactData(int id, String firstname, String lastname, String nickname, String company, String address, String mobilephone, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.company = company;
    this.address = address;
    this.mobilephone = mobilephone;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
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

  public String getMobilephone() {
    return mobilephone;
  }

  public String getGroup() {
    return group;
  }

}
