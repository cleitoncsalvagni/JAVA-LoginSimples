package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ChangePassword extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JLabel lblEnterNewPassword;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public ChangePassword(String name) {
    	setTitle("Altera\u00E7\u00E3o de Senha");
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Cleiton Salvagni\\eclipse-workspace\\amazon-2.png"));
        setBounds(450, 360, 1024, 234);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(Color.ORANGE);
        contentPane.setBorder(new LineBorder(new Color(255, 140, 0), 4));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(333, 35, 609, 67);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 34));
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnSearch = new JButton("Alterar");
        btnSearch.setForeground(Color.RED);
        btnSearch.setBounds(429, 125, 170, 59);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String pstr = textField.getText();
                try {
                    System.out.println("update password name " + name);
                    System.out.println("update password");

                    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/antibioticos",
                        "root", "cl3it0n");

                    PreparedStatement st = (PreparedStatement) con
                        .prepareStatement("Update usuario set password=? where name=?");

                    st.setString(1, pstr);
                    st.setString(2, name);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(btnSearch, "Senha alterada com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }

            }
        });
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 29));
        btnSearch.setBackground(new Color(240, 240, 240));
        contentPane.add(btnSearch);

        lblEnterNewPassword = new JLabel("Digite a nova senha :");
        lblEnterNewPassword.setBounds(25, 37, 326, 67);
        lblEnterNewPassword.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 32));
        contentPane.add(lblEnterNewPassword);
    }
}
