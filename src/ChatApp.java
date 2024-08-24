import javax.swing.*;

public class ChatApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                int option = JOptionPane.showOptionDialog(null, "Welcome to ChatApp", "ChatApp",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                        new String[]{"Register", "Login"}, "Register");

                if (option == 0) {
                    new RegisterPage();
                } else {
                    new LoginPage();
                }
            }
        });
    }
}