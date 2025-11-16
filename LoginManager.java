package inventory;

import javax.swing.*;
import java.io.*;

public class LoginManager {

    private final String LOGIN_FILE = "login.txt";

    public LoginManager() {
        try {
            File f = new File(LOGIN_FILE);
            if (!f.exists()) {
                PrintWriter pw = new PrintWriter(new FileWriter(LOGIN_FILE));
                pw.println("admin,1234");
                pw.close();
            }
        } catch (Exception e) {
            System.out.println("Error creating login file.");
        }
    }

    public boolean showLoginGUI() {
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        Object[] fields = {
                "Username:", userField,
                "Password:", passField
        };

        int option = JOptionPane.showConfirmDialog(
                null,
                fields,
                "Login",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            return login(userField.getText(), new String(passField.getPassword()));
        }
        return false;
    }

    private boolean login(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(LOGIN_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] x = line.split(",");
                if (x[0].equals(username) && x[1].equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading login file.");
        }
        return false;
    }
}
