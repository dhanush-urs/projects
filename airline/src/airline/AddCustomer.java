package airline;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddCustomer extends JFrame implements ActionListener{

    JTextField cnametxt, cnationalitytxt, aadhartxt, addresstxt, phnotxt;
    JRadioButton male, female;

    public AddCustomer(){
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(220, 20, 500, 35);
        heading.setFont(new Font("tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.blue);
        add(heading);

        JLabel cname = new JLabel("Name");
        cname.setBounds(60, 80, 150, 25);
        cname.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(cname);

        cnametxt = new JTextField();
        cnametxt.setBounds(220, 80, 150, 25);
        add(cnametxt);

        JLabel cnationality = new JLabel("Nationality");
        cnationality.setBounds(60, 130, 150, 25);
        cnationality.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(cnationality);

        cnationalitytxt = new JTextField();
        cnationalitytxt.setBounds(220, 130, 150, 25);
        add(cnationalitytxt);

        JLabel aadhar = new JLabel("Aadhar Number");
        aadhar.setBounds(60, 180, 150, 25);
        aadhar.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(aadhar);

        aadhartxt = new JTextField();
        aadhartxt.setBounds(220, 180, 150, 25);
        add(aadhartxt);

        JLabel address = new JLabel("Address");
        address.setBounds(60, 230, 150, 25);
        address.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(address);

        addresstxt = new JTextField();
        addresstxt.setBounds(220, 230, 150, 25);
        add(addresstxt);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(60, 280, 150, 25);
        gender.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(gender);

        ButtonGroup group = new ButtonGroup();

        male = new JRadioButton("Male");
        male.setBounds(220, 280, 70, 25);
        male.setBackground(Color.white);
        add(male);  
        
        female = new JRadioButton("Female");
        female.setBounds(300, 280, 70, 25);
        female.setBackground(Color.white);
        add(female); 

        group.add(male);
        group.add(female);

        JLabel phno = new JLabel("Phone Number");
        phno.setBounds(60, 330, 150, 25);
        phno.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(phno);

        phnotxt = new JTextField();
        phnotxt.setBounds(220, 330, 150, 25);
        add(phnotxt);

        JButton save = new JButton("SAVE");
        save.setBackground(Color.BLACK);
        save.setForeground(Color.white);
        save.setBounds(220, 380, 150, 30);
        save.addActionListener(this);
        add(save);

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("airline/icons/emp.png"));
        JLabel jl = new JLabel(image);
        jl.setBounds(450, 80, 280, 400);
        add(jl);

        setSize(900, 600);
        setLocation(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        String sname = cnametxt.getText();
        String snationality = cnationalitytxt.getText();
        String saadhar = aadhartxt.getText();
        String saddress = addresstxt.getText();
        String sphno = phnotxt.getText();
        String sgender = null;
        if(male.isSelected())
        sgender = "Male";
        else
        sgender = "Female";
        try{
            Connect c = new Connect();
            
            String query = "insert into passenger values('"+ sname +"', '"+ snationality +"', '"+ sphno +"', '"+ saddress +"', '"+ saadhar +"', '"+ sgender +"')";


            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Customer details added successfully");
            setVisible(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    new  AddCustomer();
    }
}