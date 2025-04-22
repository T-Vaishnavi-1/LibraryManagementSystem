import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class BorrowedBooks extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public BorrowedBooks(User use2) {
        setTitle("Borrowed Books");
        setSize(3000, 2000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table Model Setup
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Book ID", "Title", "Author"});
        table = new JTable(model);
        add(new JScrollPane(table));

        // Load Data from DB
        loadBorrowedBooks(use2);
        setVisible(true);
    }

    private void loadBorrowedBooks(User use_copy) {
      
        String useName=use_copy.getUsername();
        DatabaseConn dbc=new DatabaseConn();

        String query = """
            SELECT bookId, title, author
            FROM books 
            WHERE userId=? and isAvailable=0
        """;

        try {
             Connection conn = dbc.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1,use_copy.getUserId(useName));
             try(ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int bookId = rs.getInt("bookId");
                String title = rs.getString("title");
                String author = rs.getString("author");
                 model.addRow(new Object[]{bookId, title, author});
            }
            }catch(SQLException a)
            {
                JOptionPane.showMessageDialog(this, "Failed to load data: " + a.getMessage());
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load data: " + e.getMessage());
        }
    
    } }

    
