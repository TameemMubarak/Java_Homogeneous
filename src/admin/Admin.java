package admin;

import model.User;
import java.util.*;
public class Admin {
    Scanner scan;

    public Admin(User user) {
        if (user.getRole().trim().equalsIgnoreCase("admin")) {
            System.out.println("good day admin want to modify/update bus data");
        } else {
            System.out.println("Sry only admin can control the data ....");
        }
    }

}
