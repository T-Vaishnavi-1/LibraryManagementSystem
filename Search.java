import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Search extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    private Connection conn;
    private JButton backButton;
    private JButton clear;

    public Search(User use)
    {

        DatabaseConn dbc=new DatabaseConn();


        setTitle("Books Search");
        setSize(3000,2000);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
     
//Adding colour
        Font font=new Font("Segoe UI",Font.PLAIN,16);
        Color bgColor=new Color(245,245,245);
        Color btnColor=new Color(100,149,237);
        Color textColor=new Color(33,33,33);

        searchField=new JTextField(50);
        searchButton =new JButton("🔍");

         backButton=new JButton("<- back");
         clear=new JButton("clear");
   
        resultArea=new JTextArea(10,30);
        resultArea.setEditable(false);
        resultArea.setFont(font);
       
        setLayout(new BorderLayout(10,10));

   
        JLabel searchLabel=new JLabel ("Enter book title or author:");
        JPanel topPanel=new JPanel();
        searchLabel.setFont(font);
        searchField.setFont(font);
        topPanel.setBackground(bgColor);
        topPanel.add(searchLabel);
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(backButton);
        topPanel.add(clear);
        topPanel.setBackground(btnColor);
        add(topPanel,BorderLayout.NORTH);
        add(new JScrollPane(resultArea),BorderLayout.CENTER);
        this.conn=dbc.getConnection();

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });


        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a)
            {
                searchField.setText("");
                resultArea.setText("");
            }
        });

        searchButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e)
            {
                String qr=searchField.getText().trim();
                resultArea.setText("Searching ..."+qr);
                if(!qr.isEmpty())
                {  
                    String resultString=use.searchBook(qr);
                    resultArea.setText(resultString);
                }
                else
                {
                    resultArea.setText("Please enter a proper search term");
                }

            }
        });

        setVisible(true);
      
    }

         
    
    
}
