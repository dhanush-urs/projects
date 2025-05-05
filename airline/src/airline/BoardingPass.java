package airline;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import javax.swing.*;

public class BoardingPass extends JFrame implements ActionListener {

    JTextField pnrtxt;
    JLabel cnametxt, cnationalitytxt, srctxt, desttxt, fname, fcode, datetxt;
    JButton bookFlight, flight, fetch;

    public BoardingPass() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Air Lines");
        heading.setBounds(380, 10, 450, 35);
        heading.setFont(new Font("tahoma", Font.PLAIN, 32));
        add(heading);

        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setBounds(370, 50, 300, 35);
        subheading.setFont(new Font("tahoma", Font.PLAIN, 24));
        subheading.setForeground(Color.blue);
        add(subheading);

        JLabel aadhar = new JLabel("PNR Detials");
        aadhar.setBounds(60, 100, 150, 25);
        aadhar.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(aadhar);

        pnrtxt = new JTextField();
        pnrtxt.setBounds(220, 100, 150, 25);
        add(pnrtxt);

        fetch = new JButton("Enter");
        fetch.setBackground(Color.BLACK);
        fetch.setForeground(Color.WHITE);
        fetch.setBounds(380, 100, 125, 25);
        fetch.addActionListener(this);
        add(fetch);

        JLabel cname = new JLabel("NAME");
        cname.setBounds(60, 140, 150, 25);
        cname.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(cname);

        cnametxt = new JLabel();
        cnametxt.setBounds(220, 140, 150, 25);
        add(cnametxt);

        JLabel cnationality = new JLabel("NATIONALITY");
        cnationality.setBounds(60, 180, 150, 25);
        cnationality.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(cnationality);

        cnationalitytxt = new JLabel();
        cnationalitytxt.setBounds(220, 180, 150, 25);
        add(cnationalitytxt);

        JLabel address = new JLabel("SRC");
        address.setBounds(60, 220, 150, 25);
        address.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(address);

        srctxt = new JLabel();
        srctxt.setBounds(220, 220, 150, 25);
        add(srctxt);

        JLabel gender = new JLabel("DEST");
        gender.setBounds(380, 220, 150, 25);
        gender.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(gender);

        desttxt = new JLabel();
        desttxt.setBounds(520, 220, 150, 25);
        add(desttxt);

        JLabel flabname = new JLabel("Flight Name");
        flabname.setBounds(60, 260, 150, 25);
        flabname.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(flabname);

        fname = new JLabel();
        fname.setBounds(220, 260, 150, 25);
        add(fname);

        JLabel flabcode = new JLabel("Flight Code");
        flabcode.setBounds(380, 260, 150, 25);
        flabcode.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(flabcode);

        fcode = new JLabel();
        fcode.setBounds(520, 260, 150, 25);
        add(fcode);

        JLabel date = new JLabel("Date");
        date.setBounds(60, 300, 150, 25);
        date.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(date);

        datetxt = new JLabel();
        datetxt.setBounds(220, 300, 150, 25);
        add(datetxt);

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("airline/icons/airindia.png"));
        Image i = image.getImage().getScaledInstance(300, 230, Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(i);
        JLabel jl = new JLabel(img);
        jl.setBounds(600, 0, 300, 300);
        add(jl);

        setSize(1000, 450);
        setLocation(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetch) {
            String pnr = pnrtxt.getText();

            try {
                Connect c = new Connect();

                String query = "select * from reservation where PNR =  '" + pnr + "'";
                ResultSet r = c.s.executeQuery(query);

                if (r.next()) {
                    cnametxt.setText(r.getString("name"));
                    cnationalitytxt.setText(r.getString("nationality"));
                    srctxt.setText(r.getString("source"));
                    desttxt.setText(r.getString("destination"));
                    fname.setText(r.getString("flightname"));
                    fcode.setText(r.getString("flightcode"));
                    datetxt.setText(r.getString("dateofdep"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR no");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BoardingPass();
    }
}