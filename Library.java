import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

class Book {

    private int id;
    private String title;
    private String author;
    private boolean available;
    private String shelfId;
    private String Category;


    private Connection conn;
    DatabaseConn db = new DatabaseConn();

    // Constructor (object only)
    public Book(int id, String title, String author, boolean available,String cat,String sId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
        this.conn = db.getConnection();
        this.Category=cat;
        this.shelfId=sId;
    }

    // Constructor (fetch from DB)
    public Book(int id) {
        this.conn = db.getConnection();
        try {
            String sql = "SELECT books.*,shelf.shelfname From books Join shelf on books.shelfId=shelf.shelfId where bookId=?";
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("bookId");
                this.title = rs.getString("title");
                this.author = rs.getString("author");
                this.available = rs.getBoolean("isAvailable");
                this.Category=rs.getString("category");
                this.shelfId=rs.getString("shelfId");
            } else {
                System.out.println("Book not found in database.");
            }
            prst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public String getCategory(){return Category;}
    public String getShelfId(){return shelfId;}

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setAvailable(boolean available) { this.available = available; }
    public void setCategory(String cat){this.Category=cat;}
    public void setShelfId(String id) { this.shelfId = id; }

    // Get a book by ID
   /*  public Book getBookById(int id) {
        try {
            String sql = "SELECT books.*,shelf.shelfname From books Join shelf on books.shelfId=shelf.shelfId where bookId=?";
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                return new Book(
                    rs.getInt("bookId"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getBoolean("isAvailable"),
                    rs.getString("category")
                    ,rs.getString("shelfId")
                );
            }
            prst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /* 
        // Count total books
    public int countTotalBooks() {
        try {
            String sql = "SELECT COUNT(*) FROM books";
            PreparedStatement prst = conn.prepareStatement(sql);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            prst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
     */
    /* 
     // Check if a book is available by ID
    public boolean isBookAvailable(int id) {
        try {
            String sql = "SELECT isAvailable FROM books WHERE bookId = ?";
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("isAvailable");
            }
            prst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
        */

    // Display full info
    public void displayInfo() {
        System.out.println(" Book Info:");
        System.out.println("ID        : " + id);
        System.out.println("Title     : " + title);
        System.out.println("Author    : " + author);
        System.out.println("Available : " + (available ? "Yes" : "No"));
    }
}

class Shelf
{
    private int shelfId;
    private String shelfName;
    private String location;
    private int capacity;
    private int booksCount;
    private Connection conn;
    //private ArrayList <Book> books;
    private boolean isEmpty;
    DatabaseConn dbc= new DatabaseConn();

    public Shelf(int shelfId)
    {
        this.conn=dbc.getConnection();
        this.shelfId=shelfId;
        try{
            String sql="SELECT * FROM shelf WHERE shelf_id=?";
            PreparedStatement prst=conn.prepareStatement(sql);
            prst.setInt(1, shelfId);
            ResultSet rs=prst.executeQuery();
            if(rs.next())
            {
                
            }
        }catch(SQLException e)
        {
            System.out.println("Error:"+e.getMessage());
        }
    }

    public Shelf(int id,String name,String lc,int cap)
    {
        this.shelfId=id;
        this.shelfName=name;
        this.location=lc;
        this.capacity=cap;
        this.booksCount=0;
        this.conn=dbc.getConnection();
        try{
           
            String sql="SELECT shelfId,shelfName,location,capacity,isEmpty FROM Shelf WHERE shelfId=?";
            PreparedStatement prst=conn.prepareStatement(sql);
            prst.setInt(1, shelfId);
            ResultSet rs=prst.executeQuery();
            if(rs.next())
            {
                this.shelfName=rs.getString("shelfName");
                this.location=rs.getString("location");
                this.capacity=rs.getInt("capacity");
                this.booksCount=rs.getInt("booksCount");
                this.isEmpty=rs.getBoolean("isEmpty");
            }
            prst.close();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    

    public void getInfo()
    {
       try
       {
         String sql="SELECT shelfId,shelfName,location,capacity,booksCount,isEmpty from shelf where shelfId=?";
         PreparedStatement prst=conn.prepareStatement(sql);
         prst.setInt(1, shelfId);
         ResultSet rs=prst.executeQuery();
         if(rs.next())
         {
            System.out.println("Details");
            System.out.println("ID:"+rs.getString("shelfId"));
            System.out.println("Category:"+rs.getString("shelfName"));
            System.out.println("Location"+rs.getString("location"));
            System.out.println("capacity:"+rs.getInt("capacity"));
            System.out.println("currentBooks:"+rs.getInt("booksCount"));
            if(rs.getBoolean("isEmpty")==true)
              System.out.println("shelf is empty");
            else
               System.out.println("Not empty");

       }
     prst.close();  
    }catch(SQLException e)
    {
        e.printStackTrace();
    }
}

    public boolean spaceCheck()
    {
       return booksCount<capacity;
    }

    public void updateShelf(int id,String name,String lc,int cap)
    {
        try{
        String sql="UPDATE shelf set shelfName=?,location=?,capacity=?,booksCount=?,isEmpty=? where shelfId=?";
        PreparedStatement prst =conn.prepareStatement(sql);
        prst.setString(1, name);
        prst.setString(2, lc);
        prst.setInt(3, cap);
        prst.setInt(4, booksCount);
        prst.setBoolean(5, booksCount==0);
        prst.setInt(6, id);
        int rows=prst.executeUpdate();
        if(rows>0)
        {
        this.shelfId=id;
        this.shelfName=name;
        this.location=lc;
        this.capacity=cap;
        this.isEmpty=(booksCount==0);
        }
        prst.close();

    }catch(SQLException e){
          e.printStackTrace();
    }
}}

class User {
    public static final boolean UserExists = false;
    private String username;
    private String password;
    private int userId;
    private int borrowedBooks;

    private Connection conn;
    DatabaseConn dbc = new DatabaseConn();

    public User()
    {
         conn=dbc.getConnection();
    }
    public User(String username) {
       this.conn = dbc.getConnection();
        getUser(username,password); // Load user from DB
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    //if user want to change
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean UserExists(String userName)
    {
        try{
            String sql="Select * from user where username=?";
            PreparedStatement st=conn.prepareStatement(sql);
            st.setString(1, userName);

            ResultSet rs=st.executeQuery();
            return rs.next();
        }catch(SQLException exception)
        {
             exception.printStackTrace();
        }
        return false;
    }

    public int getUserId(String userName)
    {
        int userId=-1;
        try{
        String sql="Select userId from user where username=?";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,userName);
        ResultSet rs=st.executeQuery();
      if(rs.next())
      {
        userId=rs.getInt("userId");
        System.out.println("Found userId: " + userId);
      }
      rs.close();
      st.close();
  
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return userId;
    }

    // Fetch user data from database
    public User getUser(String username,String password) {
        try {
            String sql = "SELECT * FROM user WHERE username = ? and password=?";
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setString(1, username);
            prst.setString(2, password);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                 int ID=rs.getInt("userId");
                return new User(username);
            } else {
                System.out.println("User not found.");
            }
           
            prst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
       
    }


    // Search a book
    public String searchBook(String query) {
        StringBuilder result=new StringBuilder();
        try{
            String qr="Select title,author,shelfId,isAvailable,category,bookId from books where title like ? or author like ?";
            PreparedStatement st=conn.prepareStatement(qr);
            String match="%"+query+"%";
            st.setString(1, match);
            st.setString(2, match);

            ResultSet rs=st.executeQuery();
            boolean isFound=false;
            while(rs.next())
            {
                isFound=true;
                String title=rs.getString("title");
                String author=rs.getString("author");
                int shelfId=rs.getInt("shelfId");
                int available=rs.getInt("isAvailable");
                String category=rs.getString("category");
                int bookId=rs.getInt("bookId");

                result.append(String.format("\nTitle:%s  Author:%s  Category:%s  ShelfId:%d  Available:%s  bookId:%d\n",
                title,author,category,shelfId,(available==1)?"Yes":"No",bookId));
            }
            if(!isFound)
            {
                result.append("No matching results");
            }
            rs.close();
            st.close();
           
            

            }catch(SQLException a)
            {
                result.append("Error:"+a.getMessage());
                 a.printStackTrace();
            }
        
      return result.toString();

    }

    // Add a new book to the database
    public void addBook(String title, String author, String category, String shelfId) {
        try {
            String sql = "INSERT INTO books (title, author, category, shelfId) VALUES (?, ?, ?, ?)";
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setString(1, title);
            prst.setString(2, author);
            prst.setString(3, category);
            prst.setString(4, shelfId);

            int rows = prst.executeUpdate();
            if (rows > 0) {
                System.out.println("Book added: " + title);
            }

            prst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




public class Library {
    public static void main(String[] args) throws Exception {
       
        WelcomePage wp=new WelcomePage();
        wp.setVisible(true);   

    }   
    }
