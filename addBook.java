import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addBook extends JFrame{

    private JTextField titleField;
    private JTextField authorField;
    private JTextField category;

    DatabaseConn dbs=new DatabaseConn();
    

    private JButton addButton;
    private JTextArea messageArea;

    public addBook(User use)
    {
        setTitle("Add Book");
        setSize(3000,2000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JPanel formPanel=new JPanel(new GridLayout(3, 2,5,5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        formPanel.add(new JLabel("Bookname:"));
        titleField=new JTextField();
        formPanel.add(titleField);

        formPanel.add(new JLabel("Author:"));
        authorField=new JTextField();
        formPanel.add(authorField);


        formPanel.add(new JLabel("Category"));
        category=new JTextField();
        formPanel.add(category);

        add(formPanel,BorderLayout.NORTH);

        addButton =new JButton("Add Book");
        JPanel bPanel=new JPanel();
        bPanel.add(addButton);
        add(bPanel,BorderLayout.CENTER);



        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {

                
                String cat=category.getText();
                String title=titleField.getText();
                String author=authorField.getText();

                if(cat.isEmpty()||title.isEmpty()||author.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                }else{
                    addBooktoDatabase();
                }

            }
        });

        
        setVisible(true);
    }

    private void addBooktoDatabase()
    {
        String title=titleField.getText();
        String author=authorField.getText();
        String cat=category.getText();
         int shelf_id=1;
        try
        {
            Connection conn=dbs.getConnection();
           String sql="select * from shelf where shelfName=?";
           PreparedStatement st=conn.prepareStatement(sql);
           st.setString(1, cat);
           ResultSet rs=st.executeQuery();
   
           if(rs.next())
           {
            int booksCount=rs.getInt("booksCount");
            int capacity=rs.getInt("capacity");

            if(booksCount<capacity)
            {
                shelf_id=rs.getInt("shelfId");
            }
            else{
                String sql2="insert into shelf(location,capacity,shelfName) values(?,?,?)";
                PreparedStatement ps=conn.prepareStatement(sql2);
                ps.setString(1,"A");
                ps.setInt(2,3);
                ps.setString(3,cat);
                ps.executeUpdate();
  
                
                PreparedStatement getNewShelf=conn.prepareStatement("SELECT shelfId FROM shelf WHERE shelfName=? ORDER BY shelfId DESC LIMIT 1");
          
                getNewShelf.setString(1,cat);
  
                ResultSet newrs=getNewShelf.executeQuery();
                newrs.next();
                shelf_id=newrs.getInt("shelfId");
  
            }
 
         }
          else {
            String sql1="insert into shelf(location,capacity,shelfName) values(?,?,?)";
            PreparedStatement prst=conn.prepareStatement(sql1);
            prst.setString(1, "A");
            prst.setInt(2,30);
            prst.setString(3, cat);
            prst.executeUpdate();

            PreparedStatement getshelf = conn.prepareStatement("SELECT shelfId FROM shelf WHERE shelfName=? ");
            getshelf.setString(1, cat);
            ResultSet shelfRs = getshelf.executeQuery();
            shelfRs.next(); // move cursor to the row
            shelf_id = shelfRs.getInt("shelfId");
             
         }
 
              
              PreparedStatement insert=conn.prepareStatement("Insert into books(title,author,category,isAvailable,shelfId)values(?,?,?,?,?)");
              insert.setString(1, title);
              insert.setString(2, author);
              insert.setString(3, cat);
              insert.setInt(4,1);
              insert.setInt(5,shelf_id);
              int rows=insert.executeUpdate();
             
              if(rows>0)
              {
                PreparedStatement update=conn.prepareStatement("Update shelf set booksCount =booksCount +1 where shelfId=?");
                update.setInt(1,shelf_id);
                update.executeUpdate();
              }

              JOptionPane.showMessageDialog(null, "Book added successfully!");

           }catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error adding book: " + e.getMessage());
           }
        }
    
    
}
