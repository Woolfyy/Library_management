/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author wafaaBajjouk
 */
public class Loginpage extends javax.swing.JFrame {

    public Loginpage() {
        initComponents();
        this.setTitle("Library Management");
        this.setLocationRelativeTo(this);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tuser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        blog = new javax.swing.JButton();
        comb = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        tpass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 102, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/icons/library.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 471, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("Username :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 106, 37));
        jPanel1.add(tuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 113, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setText("Password : ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, 106, 37));

        blog.setBackground(new java.awt.Color(0, 102, 255));
        blog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/icons/icons8-enter.png"))); // NOI18N
        blog.setText("Login");
        blog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blogActionPerformed(evt);
            }
        });
        jPanel1.add(blog, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, 110, 30));

        comb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "adherent" }));
        jPanel1.add(comb, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, 110, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/icons/icons8-reading (1).png"))); // NOI18N
        jLabel4.setText("Gestion de bibliothéque");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 260, 40));
        jPanel1.add(tpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, 110, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void blogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blogActionPerformed
        // TODO add your handling code here:
        if(tuser.getText().isEmpty() || tpass.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Username or password should not be empty","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            String user=tuser.getText();
            String pass=tpass.getText();
            String e=comb.getSelectedItem().toString();
            if(e=="adherent"){
                try {
                    //Class.forName("com.mysql.jdbc.Driver");
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3325/library","root","");
                    String sql="SELECT * FROM adherent WHERE username=? AND password=?";
                    PreparedStatement pst=conn.prepareStatement(sql);
                    pst.setString(1,tuser.getText());
                    pst.setString(2,tpass.getText());
                    ResultSet rs=pst.executeQuery();
                    if(rs.next()){
                        //new Main().setVisible(true);
                        Espace_adherent.Ad_space f=new Espace_adherent.Ad_space();
                        f.displayusername(rs.getString("prenom")+" "+rs.getString("nom"));
                        f.id=rs.getInt("id_adherent");
                        f.setVisible(true);
                        setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(this,"Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
                        tuser.setText("");
                        tpass.setText("");
                    }
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Loginpage.class.getName()).log(Level.SEVERE, null, ex);
                }
        }else{
                try {
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3325/library","root","");
                    Statement stm=conn.createStatement();
                    
                    String query="SELECT * FROM useradmin WHERE username='"+tuser.getText()+"' AND password='"+tpass.getText()+"'";
                    ResultSet rst=stm.executeQuery(query);
                    if(rst.next()){
                        Main m=new Main();
                        m.setVisible(true);
                        this.setVisible(false);
                     }
                    else{
                        JOptionPane.showMessageDialog(this,"Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
                        tuser.setText("");
                        tpass.setText("");
                    }
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Loginpage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_blogActionPerformed
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Loginpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loginpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loginpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loginpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loginpage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton blog;
    private javax.swing.JComboBox<String> comb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField tpass;
    private javax.swing.JTextField tuser;
    // End of variables declaration//GEN-END:variables
}
