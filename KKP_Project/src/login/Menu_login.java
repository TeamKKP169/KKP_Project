/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;


import Data.koneksi;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hikigaya
 */
public class Menu_login extends javax.swing.JFrame {

    /**
     * Creates new form Menu_login
     */
    public Menu_login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_log = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txt_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_pass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(551, 421));
        setPreferredSize(new java.awt.Dimension(551, 421));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_log.setIcon(new javax.swing.ImageIcon("/home/hikigaya/NetBeansProjects/Sistem_Dristribusi_Gudang_Elektronik/src/Gambar/Login-toolbar-32.png")); // NOI18N
        btn_log.setText("Masuk");
        btn_log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logActionPerformed(evt);
            }
        });
        getContentPane().add(btn_log, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 130, 40));

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 214, 38));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("ID =");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 50, 30));

        txt_pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passKeyPressed(evt);
            }
        });
        jPanel1.add(txt_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 210, 40));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Password =");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 420));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_logActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logActionPerformed
    
        try {
Connection konek = koneksi.connect();
String sql = "Select * from login where id = '"+ txt_id.getText() +"' and password='" + txt_pass.getText() +"';";
PreparedStatement ps = konek.prepareStatement (sql);
ResultSet rs = ps.executeQuery();
if(rs.next()) {
String hak = rs.getString("hak"); 
if(hak.equals("admin")) {
new splash2().setVisible(true);
this.dispose(); 
}else if (hak.equals("user")) {
new splash().setVisible(true); 
this.dispose();
}
 }
}       catch (SQLException ex) {
            Logger.getLogger(Menu_login.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_btn_logActionPerformed

    private void txt_passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passKeyPressed
if (evt.getKeyCode()== KeyEvent.VK_ENTER)
        try {
Connection konek = koneksi.connect();
String sql = "Select * from login where id = '"+ txt_id.getText() +"' and password='" + txt_pass.getText() +"';";
PreparedStatement ps = konek.prepareStatement (sql);
ResultSet rs = ps.executeQuery();
if(rs.next()) {
String hak = rs.getString("hak"); 
if(hak.equals("admin")) {
new splash2().setVisible(true);
this.dispose(); 
}else if (hak.equals("user")) {
new splash().setVisible(true); 
this.dispose();
}
 }
}       catch (SQLException ex) {
            Logger.getLogger(Menu_login.class.getName()).log(Level.SEVERE, null, ex);
        }           // TODO add your handling code here:
    }//GEN-LAST:event_txt_passKeyPressed

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
            java.util.logging.Logger.getLogger(Menu_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_log;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_id;
    private javax.swing.JPasswordField txt_pass;
    // End of variables declaration//GEN-END:variables
}
