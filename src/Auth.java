public class Auth {
    private static Auth auth;
    private String email;

    private Auth() {}

    public static synchronized Auth getInstance() {
        if(auth == null) auth = new Auth();
        return auth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}