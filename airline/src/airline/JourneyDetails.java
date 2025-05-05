package airline;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener{

    JTable table;
    JTextField pnr;
    JButton show;

    public JourneyDetails() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel jlab = new JLabel("PNR details");
        jlab.setFont(new Font("Tahoma", Font.PLAIN, 16));
        jlab.setBounds(50, 50, 100, 25);
        add(jlab);

        pnr = new JTextField();
        pnr.setBounds(160, 50, 120, 25);
        add(pnr);

        show = new JButton("Show details");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setBounds(290, 50, 120, 25);
        show.addActionListener(this);
        add(show);

        table = new JTable();

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 800, 150);
        jsp.setBackground(Color.WHITE);
        add(jsp);

        setSize(800, 800);
        setLocation(400, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        try{

            Connect c = new Connect();

            ResultSet r = c.s.executeQuery("select * from reservation where PNR = '"+ pnr.getText()+"'");

            if(!r.isBeforeFirst()){
                JOptionPane.showMessageDialog(null, "No info found");
                return;
            }

            table.setModel(DbUtils.resultSetToTableModel(r));
        }catch(Exception e){
            e.printStackTrace();
        }
   }
    public static void main(String[] args) {
        new JourneyDetails();
    }
}