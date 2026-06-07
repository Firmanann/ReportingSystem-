package view.frame;

import javax.swing.*;
import java.awt.*;
import controller.AuthController;

public class RegisterFrame extends JFrame {
    
    private JTextField txtNim;
    private JTextField txtNama;
    private JTextField txtProdi;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnRegister;
    private JButton btnLogin;

    public RegisterFrame() {
        
        
        // Setup dasar Frame 
        setTitle("Register - Reporting System");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Header
        JLabel lblHeader = new JLabel("Pendaftaran Mahasiswa", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        // Panel Form 
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 15));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        formPanel.add(new JLabel("NIM:"));
        txtNim = new JTextField();
        formPanel.add(txtNim);

        formPanel.add(new JLabel("Nama Lengkap:"));
        txtNama = new JTextField();
        formPanel.add(txtNama);

        formPanel.add(new JLabel("Program Studi:"));
        txtProdi = new JTextField();
        formPanel.add(txtProdi);

        formPanel.add(new JLabel("No. HP / Phone:"));
        txtPhone = new JTextField();
        formPanel.add(txtPhone);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        formPanel.add(txtPassword);

        // Panel Tombol
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        btnRegister = new JButton("Register");
        btnRegister.setBackground(Color.BLACK);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);

        btnLogin = new JButton("Kembali");
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setFocusPainted(false);

        btnPanel.add(btnRegister);
        btnPanel.add(btnLogin);

        // komponen form
        add(lblHeader, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        // Action Listeners
        btnRegister.addActionListener(e -> handleRegister());
        btnLogin.addActionListener(e -> handleBackToLogin());
    }

    private void handleRegister() {
        

        //data input 
        String nim = txtNim.getText();
        String nama = txtNama.getText();
        String prodi = txtProdi.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());

        // validasi input 
        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //kirim data ke controller untuk di proses 
        AuthController authController = new AuthController();
        boolean sukses = authController.register(nim, nama, prodi, phone, email, password);

        if (sukses) {
            JOptionPane.showMessageDialog(this, "Registrasi Berhasil! Silakan Login.");
            handleBackToLogin();
        } else {
            JOptionPane.showMessageDialog(this, "Registrasi Gagal!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleBackToLogin() {
        new LoginFrame().setVisible(true);  
        this.dispose();
    }
}