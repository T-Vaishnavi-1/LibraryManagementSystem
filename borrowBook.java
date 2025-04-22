import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class borrowBook extends JFrame {
    private JTextField bookIdField, titleField;
    private JButton borrowButton;
   
    public borrowBook(User user) {
        setTitle("Borrow Book");
        setSize(400, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setBounds(30, 20, 60, 25);
        add(bookIdLabel);

        bookIdField = new JTextField();
        bookIdField.setBounds(100, 20, 200, 25);
        add(bookIdField);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(30, 60, 60, 25);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(100, 60, 200, 25);
        add(titleField);

        borrowButton = new JButton("Borrow");
        borrowButton.setBounds(150, 120, 80, 30);
        add(borrowButton);
       
        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrow_Book(user);
            }
        });
       
        setVisible(true);
    }

    private void borrow_Book(User use1) {
        int bookId = Integer.parseInt(bookIdField.getText());
        String title = titleField.getText();

        DatabaseConn dbc=new DatabaseConn();

        try {
            Connection conn = dbc.getConnection();
            String query = "SELECT * FROM books WHERE bookId = ? AND title = ? AND isAvailable = 1";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, bookId);
            pst.setString(2, title);
           
            ResultSet rs = pst.executeQuery();
           
            if (rs.next()) {

                String use1name=use1.getUsername();
                int userId = use1.getUserId(use1name); // assuming userId is already stored in the User object

      // Update book as borrowed
    String updateBookQuery = "UPDATE books SET isAvailable = 0, userId = ? WHERE bookId = ?";
    PreparedStatement updateBookPst = conn.prepareStatement(updateBookQuery);
    updateBookPst.setInt(1, userId);
    updateBookPst.setInt(2, bookId);
    updateBookPst.executeUpdate();

    // Increment user's borrowedBooks count
    String updateUserQuery = "UPDATE user SET borrowedBooks = borrowedBooks + 1 WHERE userId = ?";
    PreparedStatement updateUserPst = conn.prepareStatement(updateUserQuery);
    updateUserPst.setInt(1, userId);
    updateUserPst.executeUpdate();

    JOptionPane.showMessageDialog(this, "Book borrowed successfully!");

            } else {
                JOptionPane.showMessageDialog(this, "Book not found or already borrowed.");
            }
           
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }}

  