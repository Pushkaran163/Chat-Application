import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendRequestHandler {

    public void sendFriendRequest(int senderId, int receiverId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO friend_requests (sender_id, receiver_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, senderId);
            statement.setInt(2, receiverId);
            statement.executeUpdate();
            System.out.println("Friend request sent.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void acceptFriendRequest(int requestId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE friend_requests SET status='ACCEPTED' WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, requestId);
            statement.executeUpdate();
            System.out.println("Friend request accepted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void rejectFriendRequest(int requestId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE friend_requests SET status='REJECTED' WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, requestId);
            statement.executeUpdate();
            System.out.println("Friend request rejected.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void listPendingRequests(int userId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM friend_requests WHERE receiver_id=? AND status='PENDING'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int requestId = resultSet.getInt("id");
                int senderId = resultSet.getInt("sender_id");
                System.out.println("Pending request from user ID: " + senderId + ", Request ID: " + requestId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}