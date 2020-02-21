/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author hikigaya
 */
public class Data_siswa extends javax.swing.JFrame {
private final Connection conn = koneksi.connect();
     private DefaultTableModel tabmode;
    /**
     * Creates new form Data_siswa
     */
    public Data_siswa() {
        initComponents();
        tabelsiswa();
        refresh();
        lebarkolong();
        
        
         try{
         BufferedImage beam = ImageIO.read(getClass().getResource("SMK PERINTIS DEPOK.png"));
        setIconImage(beam);
        } catch (IOException ex) {
            Logger.getLogger(Data_siswa.class.getName()).log(Level.SEVERE, null, ex);
        }
            this.setTitle("DATA SISWA");
    }
    
private void lebarkolong(){
  TableColumn lebarkolong;
        
        tabel.setAutoResizeMode(tabel.AUTO_RESIZE_OFF);
        
        lebarkolong = tabel.getColumnModel().getColumn(0);
        lebarkolong.setPreferredWidth(150);
        lebarkolong = tabel.getColumnModel().getColumn(1);
        lebarkolong.setPreferredWidth(200);
        lebarkolong = tabel.getColumnModel().getColumn(2);
        lebarkolong.setPreferredWidth(365);
        lebarkolong = tabel.getColumnModel().getColumn(3);
        lebarkolong.setPreferredWidth(200);
        lebarkolong = tabel.getColumnModel().getColumn(4);
        lebarkolong.setPreferredWidth(110);
         lebarkolong = tabel.getColumnModel().getColumn(5);
        lebarkolong.setPreferredWidth(155);

}    
   private void refresh() {
        txtnis.setText("");
        txtnama.setText("");
        txtalamat.setText("");
        txtnohp.setText("");
        jekel.setSelectedItem(null);
        
       
        kelas.setSelectedItem(null);
        }   
    
  protected void tabelsiswa(){
        Object[] Baris ={"NIS","NAMA"," ALAMAT"," NO HP","JENIS KELAMIN","KELAS"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel.setModel(tabmode);
        String sql = "select * from siswa order by nis asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String data1 = hasil.getString("nis");
                String data2 = hasil.getString("nama");
                String data3 = hasil.getString("alamat");
                String data4 = hasil.getString("no_hp");
                String data5 = hasil.getString("jenis_kelamin");
                String data6 = hasil.getString("kelas");
                
                String[] data={data1,data2,data3,data4,data5,data6,};
                tabmode.addRow(data);
            }
        }      
        
        catch (Exception e) {
        }
    }
  private void save(){
  {
          
            String sql = "insert into siswa values(?,?,?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtnis.getText());
                stat.setString(2, txtnama.getText());
                stat.setString(3, txtalamat.getText());
                stat.setString(4, txtnohp.getText());
                stat.setString(5, (String) jekel.getSelectedItem());
                stat.setString(6, (String) kelas.getSelectedItem());
               
                
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
               
                txtnis.requestFocus();
              
            }   
            
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+ex);
            } 
            tabelsiswa();
            refresh();
            lebarkolong();
        } 
  
  }
  
 
  
  private void edit(){
   int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin mengubah data?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if(ok==0){
           
            String sql = "update siswa set nis=?,"
                    + "nama=?,"
                    + "alamat=?,"
                    + "no_hp=?,"
                    + "jenis_kelamin=?,"
                   
                    + "kelas=? "
                    + "where nis='"+txtnis.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtnis.getText());
                stat.setString(2, txtnama.getText());
                stat.setString(3, txtalamat.getText());
                stat.setString(4, txtnohp.getText());
                stat.setString(5, (String) jekel.getSelectedItem());
                stat.setString(6, (String) kelas.getSelectedItem());
                
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                refresh();
                txtnis.requestFocus();
                tabelsiswa();
                lebarkolong();
            }  
            
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+e);
            } 
            tabelsiswa();
        } 
  }
  
  private void hapus(){
   {
            String sql = "delete from siswa where nis='"+txtnis.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                refresh();
                txtnis.requestFocus();
                tabelsiswa();
                lebarkolong();
            }   
            
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e);
            }
        }
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
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtnis = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txtalamat = new javax.swing.JTextField();
        jekel = new javax.swing.JComboBox<>();
        kelas = new javax.swing.JComboBox<>();
        txtnohp = new javax.swing.JTextField();
        btnsave = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnbaru = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();
        tcari = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("NO HANDPHONE");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("KELAS");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("JENIS KELAMIN");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("ALAMAT");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("NAMA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("NIS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));
        jPanel1.add(txtnis, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 270, 40));
        jPanel1.add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 270, 40));
        jPanel1.add(txtalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 270, 40));

        jekel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));
        jPanel1.add(jekel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 140, 40));

        kelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kelas", "X", "XI", "XII" }));
        jPanel1.add(kelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 140, 40));

        txtnohp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnohpKeyPressed(evt);
            }
        });
        jPanel1.add(txtnohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, 270, 40));

        btnsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/save.png"))); // NOI18N
        btnsave.setText("Simpan");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnsave, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, 130, 50));

        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/edit.png"))); // NOI18N
        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        jPanel1.add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 120, 40));

        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/delete.png"))); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 370, 120, 40));

        btnbaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/new.png"))); // NOI18N
        btnbaru.setText("Baru");
        btnbaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbaruActionPerformed(evt);
            }
        });
        jPanel1.add(btnbaru, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, 120, 40));

        btncetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/print.png"))); // NOI18N
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });
        jPanel1.add(btncetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, 120, 40));

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });
        jPanel1.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 240, 410, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search-icon.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 230, -1, 50));

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        jScrollPane2.setViewportView(jScrollPane1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1210, 200));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1260, 720));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("x");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 0, 50, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/next.png"))); // NOI18N
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, 40));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("DATA SISWA");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 140, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1260, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMousePressed
 int bar = tabel.getSelectedRow();
        
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        
       
        txtnis.setText(a);
        txtnama.setText(b);
         txtalamat.setText(c);
        
        txtnohp.setText(d);
        
        jekel.setSelectedItem(e);
        kelas.setSelectedItem(f);           // TODO add your handling code here:
    }//GEN-LAST:event_tabelMousePressed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
save();   
 tabelsiswa();

// TODO add your handling code here:
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
edit();   
 tabelsiswa();
lebarkolong();
// TODO add your handling code here:
    }//GEN-LAST:event_btneditActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
hapus(); 
 tabelsiswa();
lebarkolong();
// TODO add your handling code here:
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbaruActionPerformed
refresh();
 tabelsiswa();
lebarkolong();
// TODO add your handling code here:
    }//GEN-LAST:event_btnbaruActionPerformed

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
 tabmode.getDataVector().removeAllElements();
        tabmode.fireTableDataChanged();

        try {
            Connection connection = koneksi.connect();
            Statement statement = connection.createStatement();

            String sql = "select * from siswa where nis like '%" 
                    + tcari.getText() + "%' or nis like'%" 
                    + tcari.getText() + "%' or nama like'" 
                    + tcari.getText() + "%' or alamat like'%" 
                    + tcari.getText() + "%' or no_hp like'%" 
                    + tcari.getText() + "%' or jenis_kelamin like'%" 
                    + tcari.getText() + "%' " + "or kelas like'%" 
                    + tcari.getText() + "%'";
            ResultSet resulSet = statement.executeQuery(sql);

            while (resulSet.next()) {
                Object[] o = new Object[6];
                o[0] = resulSet.getString("nis");
                o[1] = resulSet.getString("nama");
                 o[2] = resulSet.getString("alamat");
                o[3] = resulSet.getString("no_hp");
                o[4] = resulSet.getString("jenis_kelamin");
                o[5] = resulSet.getString("kelas");
              
                
                tabmode.addRow(o);
            }
            resulSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }            // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyReleased

    private void txtnohpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnohpKeyPressed
 if (evt.getKeyCode()== KeyEvent.VK_ENTER){
          
            String sql = "insert into siswa values(?,?,?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtnis.getText());
                stat.setString(2, txtnama.getText());
                stat.setString(3, txtalamat.getText());
                stat.setString(4, txtnohp.getText());
                stat.setString(5, (String) jekel.getSelectedItem());
                stat.setString(6, (String) kelas.getSelectedItem());
               
                
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
               
                txtnis.requestFocus();
              
            }   
            
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+ex);
            } 
            tabelsiswa();
            refresh();
             lebarkolong();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_txtnohpKeyPressed

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
//cetak();
cetak a=new cetak();
a.setVisible(true);
dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_btncetakActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
Menu.Menu a=new Menu.Menu();
a.setVisible(true);
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

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
            java.util.logging.Logger.getLogger(Data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Data_siswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbaru;
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jekel;
    private javax.swing.JComboBox<String> kelas;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnis;
    private javax.swing.JTextField txtnohp;
    // End of variables declaration//GEN-END:variables

    private Connection conn() {
         return conn;
    }
}
