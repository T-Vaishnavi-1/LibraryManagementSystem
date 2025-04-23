import javax.swing.*;
import org.w3c.dom.events.MouseEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class MainDb extends JFrame{
    private JButton searchButton;
    private JButton addButton;
    private JButton borrowButton;
    private JButton returnButton;
    private JButton dueButton;
    private JButton logout;

    public MainDb(User user)
    {
        
        setTitle("Dashboard");
        setSize(3000,2000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245,245,245));

        JPanel headerPanel=new JPanel();
        headerPanel.setBackground(new Color(100, 149, 237));
        JLabel wLabel=new JLabel("Welcome!");
        headerPanel.add(wLabel);

        wLabel.setFont(new Font("Serif",Font.BOLD,25));
        wLabel.setForeground(Color.WHITE);

        JPanel bPanel=new JPanel();
        bPanel.setLayout(new GridLayout(2,3,50,50));
        bPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
      
        searchButton = createCoolButton(" 🔍 Search Books");
        addButton = createCoolButton(" ➕ Add Books");
        borrowButton = createCoolButton("📥 Borrow Books");
        returnButton = createCoolButton("📤 Return Books");
        dueButton = createCoolButton("📚 Books Borrowed");
        logout = createCoolButton("🚪 Logout");

        bPanel.add(searchButton);
        bPanel.add(addButton);
        bPanel.add(borrowButton);
        bPanel.add(returnButton);
        bPanel.add(dueButton);
        bPanel.add(logout);

        add(headerPanel, BorderLayout.NORTH);
        add(bPanel, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Search sr=new Search(user);
            }
        });

        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                

             new WelcomePage();
              dispose();
    }

            
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                new addBook(user);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                
                new returnBook(user.getUserId(user.getUsername()));
            }
        });

        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                
               new borrowBook(user);
            }
        });

      dueButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
            new BorrowedBooks(user);
        }
      });

       setVisible(true);

    }
private JButton createCoolButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(224, 255, 255));
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2, true));
        button.setForeground(new Color(25, 25, 112));
        
        // Hover effects
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(173, 216, 230)); // lighter blue
            }

           public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(224, 255, 255)); // original color
            }
        });

        return button;
    }
    
}
