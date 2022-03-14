package login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import java.awt.Label;

public class UserLogin extends JFrame{

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UserLogin() {
    	setBackground(Color.WHITE);
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Cleiton Salvagni\\eclipse-workspace\\amazon-2.png"));
    	setTitle("AMAZON SERVICOS DE VAREJO DO BRASIL LTDA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(Color.ORANGE);
        contentPane.setBorder(new LineBorder(new Color(255, 140, 0), 4));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setBounds(369, 126, 281, 68);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setBounds(369, 295, 281, 68);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Usuario");
        lblUsername.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 32));
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setBounds(369, 49, 281, 52);
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Senha");
        lblPassword.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 32));
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setBounds(369, 231, 281, 52);
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Acessar");
        btnNewButton.setForeground(new Color(0, 128, 0));
        btnNewButton.setBounds(430, 401, 162, 73);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                String password = passwordField.getText();
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/antibioticos",
                        "root", "cl3it0n");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select name, password from usuario where name=? and password=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        UserHome ah = new UserHome(userName);
                        ah.setTitle("AMAZON SERVICOS DE VAREJO DO BRASIL LTDA");
                        ah.setVisible(true);
                        JOptionPane.showMessageDialog(btnNewButton, "Logado com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Usuario ou senha incorreto!", "Informação", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPane.add(btnNewButton);
        
        JLabel lblAmazoncombr = new JLabel("AMAZON.COM.BR");
        lblAmazoncombr.setHorizontalAlignment(SwingConstants.CENTER);
        lblAmazoncombr.setForeground(Color.BLACK);
        lblAmazoncombr.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
        lblAmazoncombr.setBackground(Color.CYAN);
        lblAmazoncombr.setBounds(454, 506, 113, 41);
        contentPane.add(lblAmazoncombr);
    }
}