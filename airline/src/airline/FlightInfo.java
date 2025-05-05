package airline;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class FlightInfo extends JFrame  {

    public FlightInfo() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JTable table = new JTable();

        try{

            Connect c = new Connect();

            ResultSet r = c.s.executeQuery("select * from flight");
            table.setModel(DbUtils.resultSetToTableModel(r));

        }catch(Exception e){
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 0, 800, 500);
        add(jsp);


        setSize(800, 800);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlightInfo();
    }
    
}