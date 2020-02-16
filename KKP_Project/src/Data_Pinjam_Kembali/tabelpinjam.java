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
   
   
private void cetaksatudata(){
 int bar = tabel.getSelectedRow();
     //  String a= (String) tabel.getValueAt(bar, 0);
      String b=(String) tabel.getValueAt(bar, 1);
        try{  
            String namaFile = "src/laporan/lap_jual.jasper";
            Connection conn = (Connection) koneksi.koneksi();
             
            HashMap parameter = new HashMap();
          //  parameter .put("id_pinjam", txtid.getText());
           parameter.put("npm",b);
            File report_file = new File(namaFile);
            JasperReport  jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter,conn);
            JasperViewer.viewReport(jasperPrint,false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        }catch(Exception e){
       JOptionPane.showMessageDialog(null,("Data bentrok ! pilih salah satu dan nama yang sama pilih cetak semua"));
            
        // bersihdata();  
        
         datatable();
            
        }    
      
          int baris = tabel.getSelectedRow();
        String id= tabel.getValueAt(baris, 0).toString();
        try {
            String sql ="delete from pinjaman where id_pinjam = '"+id+"'"; 
            java.sql.Connection conn=(Connection)koneksi.koneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
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

private void printkabeh(){
try{  
            String namaFile = "src/laporan/laporan_data_pinjam.jasper";
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
        JOptionPane.showMessageDialog(null,"gagal");
            
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
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        tcari = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1280, 700));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("x");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, 40, 30));

        jButton1.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/new.png"))); // NOI18N
        jButton1.setText("   +Input Pinjaman  ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 0, 190, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/next.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1260, 50));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1200, 210));

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });
        jPanel2.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 310, 40));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/delete.png"))); // NOI18N
        jButton4.setText("bersihdata");
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 150, 40));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/print.png"))); // NOI18N
        jButton6.setText("Print 1 data");
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, -1, 40));

        btncetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/print.png"))); // NOI18N
        btncetak.setText("Print all");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });
        jPanel2.add(btncetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 130, 40));

        jLabel4.setText("jLabel4");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search-icon.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1260, 620));

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

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
 printkabeh();    
 datatable();
    }//GEN-LAST:event_btncetakActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
bersihdata();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 cetaksatudata();
 datatable();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
Menu.Menu a=new Menu.Menu();
a.setVisible(true);
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

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
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField tcari;
    // End of variables declaration//GEN-END:variables
}
