package model;

public class User {

    private final String userId;
    private final String name;
    private final String email;
    private final String password;
    private final long mobileNumber;
    private final String role;

    // CONSTRUCTOR 

    public User(
            String userId,
            String name,
            String email,
            String password,
            long mobileNumber,
            String role) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.role = role.toUpperCase();
    }

    // GETTERS

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public String getRole() {
        return role;
    }

    // DISPLAY USER DETAILS

    public void displayUserDetails() {

        System.out.println(
                "\n========== USER PROFILE ==========");

        System.out.println(
                "User ID        : " + userId);

        System.out.println(
                "Name           : " + name);

        System.out.println(
                "Email          : " + email);

        System.out.println(
                "Mobile Number  : " + mobileNumber);

        System.out.println(
                "Role           : " + role);

        System.out.println(
                "==================================");
    }
}