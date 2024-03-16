package lavenstore.users;

public class GoogleUserDTO {

    private String sub;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String email;
    private boolean email_verified;
    private String locate;

    public GoogleUserDTO(String sub, String name, String given_name, String family_name, String picture, String email, boolean email_verified, String locate) {
        this.sub = sub;
        this.name = name;
        this.given_name = given_name;
        this.family_name = family_name;
        this.picture = picture;
        this.email = email;
        this.email_verified = email_verified;
        this.locate = locate;
    }

    public GoogleUserDTO() {
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    @Override
    public String toString() {
        return "GoogleUserDTO{" + "sub=" + sub + ", name=" + name + ", given_name=" + given_name + ", family_name=" + family_name + ", picture=" + picture + ", email=" + email + ", email_verified=" + email_verified + ", locate=" + locate + '}';
    }

}
