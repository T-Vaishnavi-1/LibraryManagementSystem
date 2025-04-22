import javax.swing.*;
import java.sql.*;
import java.awt.Color;
import java.awt.event.*;

public class SignUp extends JFrame {
   
    private JTextField userField;
    private JPasswordField pField;
    private JButton signButton;
   
    public SignUp()
    {

    
        getContentPane().setBackground(new Color(240,200,255));

        setTitle("Signup");
        setSize(3000,2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel=new JLabel("Username:");
        userLabel.setBounds(500,240,120,35);
         add(userLabel);

        JLabel pLabel=new JLabel("Set Password:");
        pLabel.setBounds(500,300,120,35);
        add(pLabel);


         userField=new  JTextField(30);
        userField.setBounds(680,240,250,25);
         add(userField);
        pField=new JPasswordField(30);
      
         pField.setBounds(680, 300, 250, 25);
         add(pField);

         signButton=new JButton("SignUp");
        signButton.setBackground(new Color(70,140,200));
        signButton.setForeground(Color.BLACK);
        signButton.setFocusPainted(false);
        signButton.setBounds(700,360,100,50);
        add(signButton);


        signButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                signUp();
                
            }
        });

        setVisible(true);
        
      
    }
   private void signUp(){
    {
        String newUser=userField.getText();
        String newPassword=new String(pField.getPassword());
        User use=new User(newUser);

        if(newUser.isEmpty()||newPassword.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Please fill all necessary fields");
            return;
        }

        if(use.UserExists(newUser)){
            JOptionPane.showMessageDialog(this, "User already exists");
            return;
        }
        DatabaseConn conn=new DatabaseConn(); 
        try{
            String sql="INSERT INTO user (username,password) values(?,?)";
            Connection connec =conn.getConnection();
            PreparedStatement st=connec.prepareStatement(sql);
            st.setString(1,newUser);
            st.setString(2, newPassword);

            System.out.println("hello");
            int rows =st.executeUpdate();
            if(rows>0)
            {
                JOptionPane.showMessageDialog(this,"Signup successful!");
                dispose();
            }else
            {
                JOptionPane.showMessageDialog(this,"Signup failed.Check if you are already signed up");
            }
            connec.close();

            }catch(SQLException a)
            {
                a.printStackTrace();
                JOptionPane.showMessageDialog(this, "Signup failed.Check if you are already signed up");
            }
         
    

    }

    
   }}
