package gamepub.db.entity;

import java.util.List;

/**
 * Created by roman on 30.11.15.
 */
public class User {
    int id;
    String login;
    String password;
    String email;
    String avatarUrl;
    String vkInfo;
    String steamInfo;
    String fbInfo;
    UserRole userRole;
    City city;

    List<PrivateMessage> privateMessages;
    List<Friend> subscribers;
    List<Friend> subscribeTo;
    List<Screenshot> screenshots;
    List<Mark> marks;
    List<UserGame> userGames;
    List<Comment> comments;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getVkInfo() {
        return vkInfo;
    }

    public void setVkInfo(String vkInfo) {
        this.vkInfo = vkInfo;
    }

    public String getSteamInfo() {
        return steamInfo;
    }

    public void setSteamInfo(String steamInfo) {
        this.steamInfo = steamInfo;
    }

    public String getFbInfo() {
        return fbInfo;
    }

    public void setFbInfo(String fbInfo) {
        this.fbInfo = fbInfo;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<PrivateMessage> getPrivateMessages() {
        return privateMessages;
    }

    public void setPrivateMessages(List<PrivateMessage> privateMessages) {
        this.privateMessages = privateMessages;
    }

    public List<Friend> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Friend> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Friend> getSubscribeTo() {
        return subscribeTo;
    }

    public void setSubscribeTo(List<Friend> subscribeTo) {
        this.subscribeTo = subscribeTo;
    }

    public List<Screenshot> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<Screenshot> screenshots) {
        this.screenshots = screenshots;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public List<UserGame> getUserGames() {
        return userGames;
    }

    public void setUserGames(List<UserGame> userGames) {
        this.userGames = userGames;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", vkInfo='" + vkInfo + '\'' +
                ", steamInfo='" + steamInfo + '\'' +
                ", fbInfo='" + fbInfo + '\'' +
                ", userRole=" + userRole +
                ", city=" + city +
                '}';
    }
}
