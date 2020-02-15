/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Pinjam_Kembali;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author hikigaya
 */
public class tabelpinjam extends javax.swing.JFrame {
    private DefaultTableModel tabmode;

    /**
     * Creates new form tabelpinjam
     */
    public tabelpinjam() {
        initComponents();
//        bersihdata();
       // tampil();
        datatable();
    }
    
    
    public void bersihdata()
    {
    int bar = tabmode.getRowCount();
    for(int a=0;a<bar;a++)
    {
    tabmode.removeRow(0);
    
    }
    
    
    
    }
    
    
    
   protected void datatable(){
        Object[] Baris ={"Kode","NIS ","NAMA","ID BUKU","JUDUL BUKU","JUMLAH","TANGGAL PINJAM","TANGGAL KEMBALI","STATUS"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel.setModel(tabmode);
        String sql = "select * from pinjaman order by id_pinjam asc";
        try {
            java.sql.Connection kon = (Connection) koneksi.koneksi();
            java.sql.Statement stm = kon.createStatement();
            ResultSet hasil = stm.executeQuery(sql);
            while(hasil.next()){
                String data1 = hasil.getString("id_pinjam");
                String data2 = hasil.getString("npm");
                String data3 = hasil.getString("nama");
                String data4 = hasil.getString("id_buku");
                String data5 = hasil.getString("judul");
                String data6 = hasil.getString("jumlah");
                String data7 = hasil.getString("tgl_pinjam");
                String data8 = hasil.getString("tgl_balik");
                String data9 = hasil.getString("status");
                
                String[] data={
                    data1,data2,data3,data4,data5,data6,data7,data8,data9,};
                tabmode.addRow(data);
            }
        }      
        
        catch (Exception e) {
        }
    }
private void tampil(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Npm");
        model.addColumn("Nama");
        model.addColumn("Id_buku");
        model.addColumn("judul");
        model.addColumn("Jumlah");
        model.addColumn("Tgl Pinjam");
        model.addColumn("Tgl Kembali");
        
        model.addColumn("Status");
        try{
            String query = "select*from pinjaman";
            java.sql.Connection kon = (Connection) koneksi.koneksi();
            java.sql.Statement stm = kon.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);
            
            while(data.next()){
                model.addRow(new Object [] {data.getString(1),
                data.getString(2),
                data.getString(3),
                data.getString(4),
                data.getString(5),
                data.getString(6),
                data.getString(7),
                data.getString(8),
             
                data.getString(9)});
            }
            tabel.setModel(model);
        }
        catch(Exception b){
            
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        tcari = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();
        txtid = new javax.swing.JTextField();
        txtnpm = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txtidbuku = new javax.swing.JTextField();
        txtjudul = new javax.swing.JTextField();
        txtjumlah = new javax.swing.JTextField();
        tglpinjam = new javax.swing.JTextField();
        tglbalik = new javax.swing.JTextField();
        status = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 1220, 220));

        jButton1.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        jButton1.setText("   +Input Pinjaman  ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, 30));

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });
        getContentPane().add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 260, 40));

        jButton2.setText("Kembali");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 153, 41));

        jButton3.setText("Cari");
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 100, 40));

        btncetak.setText("Print");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });
        getContentPane().add(btncetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 280, 160, 40));
        getContentPane().add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 280, 200, 30));
        getContentPane().add(txtnpm, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 310, 200, 30));
        getContentPane().add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 340, 200, 30));
        getContentPane().add(txtidbuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 370, 200, 30));
        getContentPane().add(txtjudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 400, 200, 30));
        getContentPane().add(txtjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 430, 200, 30));
        getContentPane().add(tglpinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 460, 200, 30));
        getContentPane().add(tglbalik, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 490, 200, 30));
        getContentPane().add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 520, 200, 30));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 300, -1, -1));

        jButton5.setText("kembali");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1084, 0, 160, 40));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("About");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
     int bar = tabel.getSelectedRow();
        
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();
        
       
        txtid.setText(a);
        txtnpm.setText(b);
         txtnama.setText(c);
        txtidbuku.setText(d);
        txtjudul.setText(e);
        txtjumlah.setText(f);
        tglpinjam.setText(g);
        tglbalik.setText(h);
        status.setText(i);

    }//GEN-LAST:event_tabelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new input_data_peminjaman().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
     tabmode.getDataVector().removeAllElements();
        tabmode.fireTableDataChanged();

        try {
            Connection connection = koneksi.koneksi();
            Statement statement = connection.createStatement();

            String sql = "select * from pinjaman where id_pinjam like '%" 
                    + tcari.getText() + "%' or id_pinjam like'%" 
                    + tcari.getText() + "%' or npm like'" 
                    + tcari.getText() + "%' or nama like'%" 
                    + tcari.getText() + "%' or id_buku like'%" 
                    + tcari.getText() + "%' or judul like'%" 
                    + tcari.getText() + "%' or jumlah like'%" 
                    + tcari.getText() + "%' or tgl_pinjam like'%" 
                     + tcari.getText() + "%' or tgl_balik like'%" 
                    + tcari.getText() + "%' " + "or status like'%" 
                    + tcari.getText() + "%'";
            ResultSet resulSet = statement.executeQuery(sql);

            while (resulSet.next()) {
                Object[] o = new Object[9];
                o[0] = resulSet.getString("id_pinjam");
                o[1] = resulSet.getString("npm");
                o[2] = resulSet.getString("nama");
                o[3] = resulSet.getString("id_buku");
                o[4] = resulSet.getString("judul");
                o[5] = resulSet.getString("jumlah");
                o[6] = resulSet.getString("tgl_pinjam");
                o[7] = resulSet.getString("tgl_balik");
                o[8] = resulSet.getString("status");
              
                tabmode.addRow(o);
            }
            resulSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }         // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//Menu a=new Menu();
//a.setVisible(true);//
//dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
 
        
        
     //   int bar = tabel.getSelectedRow();
     //  String a= (String) tabel.getValueAt(bar, 0);
    ///  String b=(String) tabel.getValueAt(bar, 1);
        try{  
            String namaFile = "src/laporan/lap_pinjam.jasper";
            Connection conn = (Connection) koneksi.koneksi();
             
            HashMap parameter = new HashMap();
          //  parameter .put("id_pinjam", txtid.getText());
         //   parameter.put("npm",txtnpm.getText());
            File report_file = new File(namaFile);
            JasperReport  jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter,conn);
            JasperViewer.viewReport(jasperPrint,false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e.getMessage());
            
        // bersihdata();  
        
         datatable();
            
        }    
      
        String sql = "delete from pinjaman ";
            try {
              Connection connection = koneksi.koneksi();
            Statement stat = connection.createStatement();
               stat.executeUpdate(sql);
               // JOptionPane.showMessageDialog(null, "");
                
                datatable();
            }   
            
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e);
            }
         
        
        
    }//GEN-LAST:event_btncetakActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
bersihdata();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
Menu.Menu a=new Menu.Menu();
a.setVisible(true);
this.dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(tabelpinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tabelpinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tabelpinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tabelpinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tabelpinjam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncetak;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField status;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tglbalik;
    private javax.swing.JTextField tglpinjam;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtidbuku;
    private javax.swing.JTextField txtjudul;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnpm;
    // End of variables declaration//GEN-END:variables
}