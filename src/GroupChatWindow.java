import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupChatWindow extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private int groupId;
    private int userId;

    public GroupChatWindow(int groupId, int userId) {
        this.groupId = groupId;
        this.userId = userId;

        setTitle("Group Chat");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        messageField = new JTextField(40);
        sendButton = new JButton("Send");

        JPanel panel = new JPanel();
        panel.add(messageField);
        panel.add(sendButton);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendGroupMessage();
            }
        });

        loadGroupMessages();
        setVisible(true);
    }

    private void sendGroupMessage() {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO messages (sender_id, receiver_id, message) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, userId);
                statement.setInt(2, groupId);
                statement.setString(3, message);
                statement.executeUpdate();

                chatArea.append("You: " + message + "\n");
                messageField.setText("");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void loadGroupMessages() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM messages WHERE receiver_id=? ORDER BY timestamp";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int senderId = resultSet.getInt("sender_id");
                String message = resultSet.getString("message");
                chatArea.append("User " + senderId + ": " + message + "\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}