
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class returnBook extends JFrame {
    private JTextField bookIdField, titleField;
    private JButton returnButton;
    private int userId;

    public returnBook(int userId) {
        this.userId = userId;

        setTitle("Return Book");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel heading = new JLabel("Return Book");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(heading, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Book ID:"), gbc);

        gbc.gridx = 1;
        bookIdField = new JTextField(15);
        add(bookIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Title:"), gbc);

        gbc.gridx = 1;
        titleField = new JTextField(15);
        add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        returnButton = new JButton("Return Book");
        add(returnButton, gbc);

        returnButton.addActionListener(e -> returnBook());
        setVisible(true);
    }

    private void returnBook() {
        int bookId = Integer.parseInt(bookIdField.getText().trim());
        String title = titleField.getText().trim();

        DatabaseConn dbc=new DatabaseConn();


        try (Connection conn = dbc.getConnection()) {
            conn.setAutoCommit(false); // So all steps happen together

            // Verify book exists and was borrowed by this user
            PreparedStatement checkStmt = conn.prepareStatement(
                "SELECT * FROM books WHERE bookId = ? AND title = ? AND userId = ?"
            );
            checkStmt.setInt(1, bookId);
            checkStmt.setString(2, title);
            checkStmt.setInt(3, userId);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "No matching borrowed book found!");
                return;
            }

            // 1. Update books table
            PreparedStatement updateBook = conn.prepareStatement(
                "UPDATE books SET isAvailable = 1, userId = 0 WHERE bookId = ?"
            );
            updateBook.setInt(1, bookId);
            updateBook.executeUpdate();

            // 2. Update user table
            PreparedStatement updateUser = conn.prepareStatement(
                "UPDATE user SET borrowedBooks = borrowedBooks - 1 WHERE userId = ? AND borrowedBooks > 0"
            );
            updateUser.setInt(1, userId);
            updateUser.executeUpdate();

            conn.commit();

            JOptionPane.showMessageDialog(this, "Book returned successfully!");
            bookIdField.setText("");
            titleField.setText("");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while returning book: " + ex.getMessage());
        }
    }

    
}
