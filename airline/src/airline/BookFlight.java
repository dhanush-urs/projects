package airline;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;

public class BookFlight extends JFrame implements ActionListener {

    JTextField aadhartxt;
    JLabel cnametxt, cnationalitytxt, addresstxt, genderlabel, fname, fcode;
    JButton bookFlight, flight, fetch;
    Choice source, destination;
    JDateChooser datechoose;

    public BookFlight() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);
        heading.setFont(new Font("tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.blue);
        add(heading);

        JLabel aadhar = new JLabel("Aadhar");
        aadhar.setBounds(60, 80, 150, 25);
        aadhar.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(aadhar);

        aadhartxt = new JTextField();
        aadhartxt.setBounds(220, 80, 150, 25);
        add(aadhartxt);

        fetch = new JButton("Fetch User");
        fetch.setBackground(Color.BLACK);
        fetch.setForeground(Color.WHITE);
        fetch.setBounds(380, 80, 125, 25);
        fetch.addActionListener(this);
        add(fetch);

        JLabel cname = new JLabel("Name");
        cname.setBounds(60, 130, 150, 25);
        cname.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(cname);

        cnametxt = new JLabel();
        cnametxt.setBounds(220, 130, 150, 25);
        add(cnametxt);

        JLabel cnationality = new JLabel("Nationality");
        cnationality.setBounds(60, 180, 150, 25);
        cnationality.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(cnationality);

        cnationalitytxt = new JLabel();
        cnationalitytxt.setBounds(220, 180, 150, 25);
        add(cnationalitytxt);

        JLabel address = new JLabel("Address");
        address.setBounds(60, 230, 150, 25);
        address.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(address);

        addresstxt = new JLabel();
        addresstxt.setBounds(220, 230, 150, 25);
        add(addresstxt);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(60, 280, 150, 25);
        gender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(gender);

        genderlabel = new JLabel("Gender");
        genderlabel.setBounds(220, 280, 150, 25);
        add(genderlabel);

        JLabel srclab = new JLabel("Source");
        srclab.setBounds(60, 330, 150, 25);
        srclab.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(srclab);

        source = new Choice();
        source.setBounds(220, 330, 120, 25);
        add(source);

        JLabel destlab = new JLabel("Destination");
        destlab.setBounds(60, 380, 150, 25);
        destlab.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(destlab);

        destination = new Choice();
        destination.setBounds(220, 380, 120, 25);
        add(destination);

        try {
            Connect c = new Connect();
            String query = "select * from flight";
            ResultSet r = c.s.executeQuery(query);

            while (r.next()) {
                source.add(r.getString("src"));
                destination.add(r.getString("dest"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        flight = new JButton("Fetch Flights");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.white);
        flight.setBounds(380, 380, 120, 25);
        flight.addActionListener(this);
        add(flight);

        JLabel flabname = new JLabel("Flight Name");
        flabname.setBounds(60, 430, 150, 25);
        flabname.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(flabname);

        fname = new JLabel();
        fname.setBounds(220, 430, 150, 25);
        add(fname);

        JLabel flabcode = new JLabel("Flight Code");
        flabcode.setBounds(60, 480, 150, 25);
        flabcode.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(flabcode);

        fcode = new JLabel();
        fcode.setBounds(220, 480, 150, 25);
        add(fcode);

        JLabel date = new JLabel("Date of Travel");
        date.setBounds(60, 530, 150, 25);
        date.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(date);

        datechoose = new JDateChooser();
        datechoose.setBounds(220, 530, 150, 25);
        add(datechoose);

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("airline/icons/details.jpg"));
        Image i = image.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(i);
        JLabel jl = new JLabel(img);
        jl.setBounds(550, 80, 500, 410);
        add(jl);

        bookFlight = new JButton("Book Flight");
        bookFlight.setBackground(Color.BLACK);
        bookFlight.setForeground(Color.WHITE);
        bookFlight.setBounds(220, 580, 150, 25);
        bookFlight.addActionListener(this);
        add(bookFlight);

        setSize(1100, 700);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetch) {
            String saadhar = aadhartxt.getText();

            try {
                Connect c = new Connect();

                String query = "select * from passenger where aadhar =  '" + saadhar + "'";
                ResultSet r = c.s.executeQuery(query);

                if (r.next()) {
                    cnametxt.setText(r.getString("name"));
                    cnationalitytxt.setText(r.getString("nationality"));
                    addresstxt.setText(r.getString("address"));
                    genderlabel.setText(r.getString("gender"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct Aadhar no");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();

            try {
                Connect c = new Connect();

                String query = "select * from flight where src =  '" + src + "' and dest = '" + dest + "'";
                ResultSet r = c.s.executeQuery(query);

                if (r.next()) {
                    fname.setText(r.getString("f_name"));
                    fcode.setText(r.getString("f_code"));
                } else {
                    JOptionPane.showMessageDialog(null, "No flights found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Random rand = new Random();

            String aadhar =  aadhartxt.getText();
            String name = cnametxt.getText();
            String nationality = cnationalitytxt.getText();
            String flightname =  fname.getText(); 
            String flightcode = fcode.getText();
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();
            String date = ((JTextField) datechoose.getDateEditor().getUiComponent()).getText();

            try {
                Connect c = new Connect();
                
                int pnrno =  rand.nextInt(1000000);
                
                String query = "insert into reservation values('PNR-" + pnrno + "', 'TIC-" + rand.nextInt(10000) + "', '" + aadhar + "', '" + name + "', '" + nationality + "', '" + flightname + "', '" + flightcode + "', '" + src + "', '" + dest + "', '" + date + "')";
                c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Ticket booked successfully! with PNR no " + pnrno);
                    setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}