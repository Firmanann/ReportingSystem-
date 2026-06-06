package view.frame;

import javax.swing.*;
import java.awt.*;
import controller.AuthController;
import utils.UserSession;

public class LoginFrame extends JFrame {
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnGoToRegister;

    //UI Design
    public LoginFrame() {
        // Setup dasar Frame
        setTitle("Login - Reporting System");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Header
        JLabel lblHeader = new JLabel("Selamat Datang", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        
        // Panel Form
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        formPanel.add(new JLabel("NIM / Username:"));
        txtUsername = new JTextField();
        formPanel.add(txtUsername);

        formPanel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        formPanel.add(txtPassword);

        // Panel Tombol
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        //Button login 
        btnLogin = new JButton("Login");
        btnLogin.setBackground(Color.BLACK);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);

        //Button Back to register page 
        btnGoToRegister = new JButton("Belum punya akun? Daftar");
        btnGoToRegister.setBackground(Color.WHITE);
        btnGoToRegister.setForeground(Color.BLACK);
        btnGoToRegister.setFocusPainted(false);

        btnPanel.add(btnLogin);
        btnPanel.add(btnGoToRegister);

        // Menambahkan komponen ke Frame
        add(lblHeader, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        //Login Button action
        btnLogin.addActionListener(e -> handleLogin());

        //Back to Register button action
        btnGoToRegister.addActionListener(e -> handleGoToRegister());
    }

    //Logic Login
    private void handleLogin() {

        //1. Get data input 
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        
        AuthController authController = new AuthController();

        //2. Validate input
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //4. Process login
        String role = authController.login(username, password);
        UserSession user = new UserSession();
        
        //3. Mapping to next page
        if (role.equals("MAHASISWA")) {

            //Set data user session
            user.setUser(username);

            //Start main mahasiswa frame
            new MainMahasiswaFrame().setVisible(true);

            //Stop this frame
            this.dispose();

        } else if (role.equals("PETUGAS")) {

            //Set data user session
            user.setUser(username);

            //Start Main petugas frame
            new MainPetugasFrame().setVisible(true);

            //Stop this frame
            this.dispose();

        } else {

            //Show notification
            JOptionPane.showMessageDialog(this, "Username atau Password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Mencoba login: " + username);
    }

    //Logic back to register
    private void handleGoToRegister() {
        new RegisterFrame().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}