import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class WelcomePage extends JFrame{
    public WelcomePage()
    {
        //pagesetup
        setTitle("Library Management welcomes you");
        setSize(3000,2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(null);

        JLabel Welcome=new JLabel("Welcome to the Library System",SwingConstants.CENTER);
        Welcome.setBounds(500, 100, 500, 100);
        Welcome.setFont(new Font("Arial",Font.BOLD,30));
        
        JButton loginButton=new JButton("Login");
        JButton signupButton=new JButton("Signup");

        JPanel bPanel=new JPanel();
        bPanel.add(loginButton);
        bPanel.add(signupButton);

        setLayout(new BorderLayout());//layout
        add(Welcome);
        add(bPanel,BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                new LoginPage();
                dispose();
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                new SignUp();
            }
        });

        
        setVisible(true);


      



    }
}
