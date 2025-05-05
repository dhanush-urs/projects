package airline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Home extends JFrame implements ActionListener{
    public Home(){
        setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("airline/icons/front.jpg"));
        JLabel jl = new JLabel(img);
        jl.setBounds(0, 0, 1600, 800);
        add(jl);
        
        JLabel head = new JLabel("WELCOME TO AIRLINES");
        head.setBounds(500, 40, 1000, 40);
        head.setForeground(Color.BLUE);
        head.setFont(new Font("Tahoma", Font.PLAIN, 36));
        jl.add(head);

        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);

        JMenu details = new JMenu("Details");
        bar.add(details);

        JMenuItem flightDet = new JMenuItem("Flight Details");
        flightDet.addActionListener(this);
        details.add(flightDet);

        JMenuItem custDet = new JMenuItem("Add Customer Details");
        custDet.addActionListener(this);
        details.add(custDet);

        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        details.add(bookFlight);

        JMenuItem journeyDet = new JMenuItem("Journey Details");
        journeyDet.addActionListener(this);
        details.add(journeyDet);

        JMenuItem cancelTick = new JMenuItem("Cancel Ticket");
        cancelTick.addActionListener(this);
        details.add(cancelTick);

        JMenu ticket = new JMenu("Ticket");
        bar.add(ticket);

        JMenuItem boardingpass = new JMenuItem("Boarding pass");
        ticket.add(boardingpass);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        
        String text = ae.getActionCommand();
       
        if(text.equals("Add Customer Details"))
        	new AddCustomer();
        else if(text.equals("Flight Details"))
        	new FlightInfo();
        else if(text.equals("Book Flight"))
        	new BookFlight();
        else if(text.equals("Journey Details"))
        	new JourneyDetails();
        else if(text.equals("Cancel Ticket"))
        	new Cancel();
    }
    public static void main(String[] args) {
        new Home();
    }
}