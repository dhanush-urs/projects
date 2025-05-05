package airline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{

    JButton close, submit, reset;
    JTextField unametxt;
    JPasswordField passtxt;

    public Login(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);


        JLabel uname = new JLabel("username");
        uname.setBounds(20, 20, 100, 20);
        add(uname);

        unametxt = new JTextField();
        unametxt.setBounds(130, 20, 100, 20);
        add(unametxt);

        JLabel pass = new JLabel("password");
        pass.setBounds(20, 60, 100, 20);
        add(pass);

        passtxt = new JPasswordField();
        passtxt.setBounds(130, 60, 100, 20);
        add(passtxt);

        reset = new JButton("reset");
        reset.setBounds(40, 120, 120, 20);
        reset.addActionListener(this);
        add(reset);

        submit = new JButton("submit");
        submit.setBounds(190, 120, 120, 20);
        submit.addActionListener(this);
        add(submit);

        close = new JButton("close");
        close.setBounds(120, 160, 120, 20);
        close.addActionListener(this);
        add(close);

        

        setSize(400, 250);
        setLocation(600, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == submit){
            String username = unametxt.getText();
            String password = passtxt.getText();

            try{
                Connect c = new Connect();

                String query = "select * from login where username ='" + username + "' and password = '" + password + "'";
                ResultSet res = c.s.executeQuery(query);

                if(res.next()){
                    new Home();
                    setVisible(false);
                } else{
                JOptionPane.showMessageDialog(null, "username and pass dosnt match");
                setVisible(false);
                }

            }catch(Exception e){
                e.printStackTrace();
            }

        } else if(ae.getSource() == close){
            setVisible(false);

        }else if(ae.getSource() == reset){
            unametxt.setText("");
            passtxt.setText("");
        }

    }
    public static void main(String[] args) {
        new Login();
    }
}