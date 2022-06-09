import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class JavaCrud {
    private JPanel Main;
    private JTextField textName;
    private JTextField textPrice;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField textpid;
    private JTextField textQty;
    private JButton searchButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCrud");
        frame.setContentPane(new JavaCrud().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    Connection con;
    PreparedStatement pst;
    public JavaCrud() {
        Connect();
        saveButton.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name,price,qty;

                name = textName.getText();
                price = textPrice.getText();
                qty = textQty.getText();

                try {
                    pst = con.prepareStatement("insert into products (pname, price, qty) values(?,?,?)");
                    pst.setString(1,name);
                    pst.setString(2,price);
                    pst.setString(3,qty);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"record added");

                    textName.setText("");
                    textPrice.setText("");
                    textQty.setText("");
                    textName.requestFocus();

                }


                catch (SQLException e1){
                    e1.printStackTrace();

                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String pid = textpid.getText();
                    pst = con.prepareStatement("select pname,price,qty from products where pid = ?");
                    pst.setString(1,pid);
                    ResultSet rs = pst.executeQuery();


                    if (rs.next()==true) {
                        String name = rs.getString(1);
                        String price = rs.getString(2);
                        String qty = rs.getString(3);

                        textName.setText(name);
                        textPrice.setText(price);
                        textQty.setText(qty);

                    }
                    else
                    {
                        textName.setText("");
                        textPrice.setText("");
                        textQty.setText("");
                    }
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }

            }
        } );
            }



        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pid, name,price,qty;

                name = textName.getText();
                price = textPrice.getText();
                qty = textQty.getText();
                pid = textpid.getText();


                try {
                    pst = con.prepareStatement("update products set pname = ?, price = ?, qty = ? where pid = ?");
                    pst.setString(1, name);
                    pst.setString(2, price);
                    pst.setString(3, qty);
                    pst.setString(4, pid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "record update");

                    textName.setText("");
                    textPrice.setText("");
                    textQty.setText("");
                    textName.requestFocus();
                    textpid.setText("");
                }

              catch(SQLException e1)
              {
                  e1.printStackTrace();
              }




            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pid,name,price,qty;

                name = textName.getText();
                price = textPrice.getText();
                qty = textQty.getText();
                pid = textpid.getText();

                try {

                    pst = con.prepareStatement("update products set pname = ?,price = ?,qty = ? where pid = ?");
                    pst.setString(1, name);
                    pst.setString(2, price);
                    pst.setString(3, qty);
                    pst.setString(4, pid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");

                    textName.setText("");
                    textPrice.setText("");
                    textQty.setText("");
                    textName.requestFocus();
                    textpid.setText("");
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }


            }
        });
    }

    public void Connect()
    {
        try {
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:8889/GBProducts", "root", "root");
    System.out.print("Success");
    }
            catch (ClassNotFoundException ex )
            {
                ex.printStackTrace();
             }
            catch (SQLException ex)
            {
                ex.printStackTrace();

            }

        }
    }

