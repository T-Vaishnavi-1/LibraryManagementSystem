import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginPage extends JFrame
{
    private JTextField userName;
    private JPasswordField passwordField;
    private JButton login;
    public LoginPage()
    {
        /* 
        setSize(1000, 800);
        setTitle("Library for all");
        JLabel title=new JLabel("Distributed Library System");
        title.setFont(new Font("Arial",Font.BOLD,28));
        title.setBounds(100,200,20,20);
        add(title);*/
        
        setTitle("Library:Login Page");
        setSize(3000, 2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255));

        JLabel title=new JLabel("Distributed Library System");
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setBounds(500,30,500,50);
        add(title);

        setTitle("Login");
         //setSize(1000, 500);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         setLocationRelativeTo(null);
         setLayout(null);

         JLabel userLabel=new JLabel("Username:");
         userLabel.setBounds(500,240,120,35);
         add(userLabel);

         userName=new JTextField();
         userName.setBounds(680,240,250,25);
         add(userName);
        
         JLabel passwordLabel=new JLabel("Password:");
         passwordLabel.setBounds(500,300,120,35);
         add(passwordLabel);
         getContentPane().setBackground(new Color(200, 248,255));

         passwordField=new JPasswordField();
         //passwordField.setVisible(true);
         passwordField.setBounds(680, 300, 250, 25);
         add(passwordField);



         login=new JButton("LOGIN");
         login.setBackground(new Color(70,140,200));
         login.setForeground(Color.BLACK);
         login.setFocusPainted(false);
         login.setBounds(700,360,100,50);
         add(login);

         login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String username=userName.getText();
                String password=new String(passwordField.getPassword());
                User currentuser=new User();
                User fetchedUser=currentuser.getUser(username,password);
                if(fetchedUser!=null){
                MainDb md=new MainDb(fetchedUser);
                md. setVisible(true);
                dispose();
                }else
                {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }                

                
                //JOptionPane.showMessageDialog("Login clicked!");
            }
         });

         setVisible(true);


        


        
    }
}



    
    

