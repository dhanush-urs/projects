package airline;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import javax.swing.*;

public class Cancel extends JFrame implements ActionListener {

    JTextField pnrno;
    JLabel cnametxt, cancellationno, flightcode, dateoftravel, fname, fcode;
    JButton flight, fetch;

    public Cancel() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        Random rand = new Random();

        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("tahoma", Font.PLAIN, 32));
        add(heading);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("airline/icons/cancel.jpg"));
        Image i = img.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(i);
        JLabel jl = new JLabel(icon);
        jl.setBounds(510, 100, 250, 250);
        add(jl);

        JLabel aadhar = new JLabel("PNR Number");
        aadhar.setBounds(60, 80, 150, 25);
        aadhar.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(aadhar);

        pnrno = new JTextField();
        pnrno.setBounds(220, 80, 150, 25);
        add(pnrno);

        fetch = new JButton("Show details");
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

        JLabel cnationality = new JLabel("Cancellation No");
        cnationality.setBounds(60, 180, 150, 25);
        cnationality.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(cnationality);

        cancellationno = new JLabel(""+rand.nextInt(1000000));
        cancellationno.setBounds(220, 180, 150, 25);
        add(cancellationno);

        JLabel address = new JLabel("Flight code");
        address.setBounds(60, 230, 150, 25);
        address.setFont(new Font("tahoma", Font.PLAIN, 16));
        add(address);

        flightcode = new JLabel();
        flightcode.setBounds(220, 230, 150, 25);
        add(flightcode);

        JLabel gender = new JLabel("Date of travel");
        gender.setBounds(60, 280, 150, 25);
        gender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(gender);

        dateoftravel = new JLabel();
        dateoftravel.setBounds(220, 280, 150, 25);
        add(dateoftravel);

        flight = new JButton("FCancel");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.white);
        flight.setBounds(380, 330, 120, 25);
        flight.addActionListener(this);
        add(flight);

        setSize(800, 400);
        setLocation(350, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetch) {
            String spnr = pnrno.getText();

            try {
                Connect c = new Connect();

                String query = "select * from reservation where PNR =  '" + spnr + "'";
                ResultSet r = c.s.executeQuery(query);

                if (r.next()) {
                    cnametxt.setText(r.getString("name"));
                    flightcode.setText(r.getString("flightcode"));
                    dateoftravel.setText(r.getString("dateofdep"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR no");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String name = cnametxt.getText();
            String pnr = pnrno.getText();
            String cno = cancellationno.getText();
            String fcode = flightcode.getText();
            String date = dateoftravel.getText();

            try {
                Connect c = new Connect();

                String query = "insert into cancellation values('" + pnr + "', '" + name + "', '"+ cno +"', '"+ fcode +"', '"+ date +"')";
                c.s.executeUpdate(query);
                c.s.executeUpdate("delete from reservation where PNR = '"+ pnr +"'");
                JOptionPane.showMessageDialog(null, "Ticket cancelled successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Cancel();
    }
}