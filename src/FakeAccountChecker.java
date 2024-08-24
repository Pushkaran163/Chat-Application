import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FakeAccountChecker {

    public static boolean isFakeAccount(String username) {
        // Simple logic to check for fake accounts
        // You can enhance this by checking more complex criteria
        String[] commonFakeUsernames = {"test", "admin", "user", "1234"};

        for (String fakeUsername : commonFakeUsernames) {
            if (username.equalsIgnoreCase(fakeUsername)) {
                return true;
            }
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 1; // More than one user with the same username is suspicious
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}