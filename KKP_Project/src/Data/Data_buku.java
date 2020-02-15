/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author hikigaya
 */
public class Data_buku extends javax.swing.JFrame {
    private final Connection conn = koneksi.connect();
     private DefaultTableModel tabmode;

    /**
     * Creates new form Data_buku
     */
    public Data_buku() {
        initComponents();
        lblid.setEditable(false);
        tabelbuku();
        tampilbox();
        kosong();
    }

    private void kosong() {
       lblid.setText("");
       txtjudul.setText("");
       txtpengarang.setText("");
       txtpenerbit.setText("");
       cmbkat.setSelectedItem("");
       txtdesc.setText("");
       txtstok.setText("");
       
    }
    protected void tabelbuku(){
        Object[] Baris ={"ID","JUDUL"," PENGARANG","PENERBIT","KATEGORI","ISBN","JUMLAH"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel1.setModel(tabmode);
        String sql = "select * from buku order by id_buku asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String data1 = hasil.getString("id_buku");
                String data2 = hasil.getString("judul");
                String data3 = hasil.getString("pengarang");
                String data4 = hasil.getString("penerbit");
                String data5 = hasil.getString("kategori");
                String data6 = hasil.getString("isbn");
                String data7 = hasil.getString("stok");
                
                String[] data={data1,data2,data3,data4,data5,data6,data7,};
                tabmode.addRow(data);
            }
        }      
        
        catch (Exception ex) {
        }
    }
private void tampilbox(){
try{
    String sql="select * from kategori_buku";
    java.sql.Connection kon = (Connection) koneksi.connect();
            PreparedStatement stat = kon.prepareStatement(sql);
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
    String name = hasil.getString("nama_kategori");
    cmbkat.addItem(name);
}
}    catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, ex);
    }
    }
    
  private void save(){
  String kat = (String) cmbkat.getSelectedItem();
String jdl = (String) txtjudul.getText();
String penga = (String) txtpengarang.getText();
String pener = (String) txtpenerbit.getText();
String desc = (String) txtdesc.getText();
String stk = (String) txtstok.getText();
     
    if(kat.equals("Kategori")){
      JOptionPane.showMessageDialog(null, "Pilih Kategori!");
}   
else if(jdl.equals("")||penga.equals("")||pener.equals("")||desc.equals("")||stk.equals("")){
JOptionPane.showMessageDialog(null, "Tidak Boleh Ada Yang Kosong!");     
}else{

    try{
    String sql="select*from buku order by id_buku desc";
    Connection conn=(Connection) koneksi.connect();
    Statement stm = conn.createStatement();
    ResultSet res = stm.executeQuery(sql);
    String aidi;
    if(res.next()){
       
      String no=res.getString("id_buku").substring(1);
      String id = ""+(Integer.parseInt(no)+1);
      String nol=null;
      if(id.length()==1){
          nol="000";
      } 
      else if(id.length()==2){
          nol="00";
      }
       else if(id.length()==3){
          nol="0";
      }
       else if(id.length()==4){
          nol="";
      }
      aidi = "B"+nol+id; 
    }
    else{
       aidi = "B0001"; 
    }
    try {
            String query = "INSERT INTO buku VALUES "
                    + "('" + aidi+"','"+txtjudul.getText() + "','" + txtpengarang.getText() 
                    + "','" + txtpenerbit.getText() + "','" + cmbkat.getSelectedItem()  
                    + "','" + txtdesc.getText()+ "','" + txtstok.getText() + "')";
            java.sql.Connection kon = (Connection) koneksi.connect();
            java.sql.PreparedStatement mts = kon.prepareStatement(query);
            mts.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            //kosong();
            tabelbuku();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
   }  
catch(Exception b){
    JOptionPane.showMessageDialog(null,b.getMessage());
}
// kosong();
            tabelbuku();
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
        tabel1 = new javax.swing.JTable();
        lblid = new javax.swing.JTextField();
        txtjudul = new javax.swing.JTextField();
        txtpengarang = new javax.swing.JTextField();
        txtpenerbit = new javax.swing.JTextField();
        cmbkat = new javax.swing.JComboBox<>();
        txtdesc = new javax.swing.JTextField();
        txtstok = new javax.swing.JTextField();
        btnsave = new javax.swing.JButton();
        btnnew = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();
        tcari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 700));
        setPreferredSize(new java.awt.Dimension(1280, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabel1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabel1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1260, 250));
        getContentPane().add(lblid, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 130, 30));
        getContentPane().add(txtjudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 350, 40));
        getContentPane().add(txtpengarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 350, 40));
        getContentPane().add(txtpenerbit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, 350, 40));

        cmbkat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kategori" }));
        getContentPane().add(cmbkat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 520, 130, 30));
        getContentPane().add(txtdesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 560, 350, 40));

        txtstok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtstokKeyPressed(evt);
            }
        });
        getContentPane().add(txtstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 610, 350, 40));

        btnsave.setText("Simpan");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnsave, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 170, 40));

        btnnew.setText("Refresh");
        btnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewActionPerformed(evt);
            }
        });
        getContentPane().add(btnnew, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 420, 170, 40));

        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        getContentPane().add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, 170, 40));

        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 520, 170, 40));

        btncetak.setText("Print");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });
        getContentPane().add(btncetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 570, 170, 40));

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });
        getContentPane().add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 340, 370, 40));

        jLabel1.setText("JUDUL BUKU");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        jLabel2.setText("PENGARANG");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        jLabel3.setText("PENERBIT");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, -1));

        jLabel4.setText("KATEGORI");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, -1));

        jLabel5.setText("ISBN");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, -1, -1));

        jLabel6.setText("STOK");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, -1, -1));

        jButton1.setText("Cari");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 340, -1, 40));

        jButton2.setText("Kembali");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 10, 190, 50));

        jButton3.setText("Tambah Kategori");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 520, 200, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel1MousePressed
int bar = tabel1.getSelectedRow();
        String h = tabmode.getValueAt(bar, 0).toString();
        String a = tabmode.getValueAt(bar, 1).toString();
        String b = tabmode.getValueAt(bar, 2).toString();
        String c = tabmode.getValueAt(bar, 3).toString();
        String d = tabmode.getValueAt(bar, 4).toString();
        String e = tabmode.getValueAt(bar, 5).toString();
        String f = tabmode.getValueAt(bar, 6).toString();

        lblid.setText(h);
        txtjudul.setText(a);
        txtpengarang.setText(b);
        txtpenerbit.setText(c);
        cmbkat.setSelectedItem(d);
        txtdesc.setText(e);
        txtstok.setText(f);        // TODO add your handling code here:
    }//GEN-LAST:event_tabel1MousePressed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
save();        // TODO add your handling code here:
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewActionPerformed
kosong();        // TODO add your handling code here:
    }//GEN-LAST:event_btnnewActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
  {
            String sql = "update buku set"
                    + " id_buku=?,"
                    + "judul=?,"
                    + "pengarang=?,"
                    + "penerbit=?,"
                    + "kategori=?,"
                    + "isbn=?,"
                    + "stok=?"
                    + "where id_buku='"+lblid.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, lblid.getText());
                stat.setString(2, txtjudul.getText());
                stat.setString(3, txtpengarang.getText());
                stat.setString(4, txtpenerbit.getText());
                
                stat.setString(5, (String) cmbkat.getSelectedItem());
                 stat.setString(6, txtdesc.getText());
                  stat.setString(7, txtstok.getText());
               
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            
                lblid.requestFocus();
             tabelbuku();
            kosong();
            }   
            
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+ex);
            } 
            
            
        }         // TODO add your handling code here:
    }//GEN-LAST:event_btneditActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
 {
            String sql = "delete from buku where id_buku='"+lblid.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
                lblid.requestFocus();
                tabelbuku();
                kosong();
            }   
            
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnhapusActionPerformed

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
tabmode.getDataVector().removeAllElements();
        tabmode.fireTableDataChanged();

        try {
            Connection connection = koneksi.connect();
            Statement statement = connection.createStatement();

            String sql = "select * from buku where id_buku like '%" 
                    + tcari.getText() + "%' or id_buku like'%" 
                    + tcari.getText() + "%' or judul like'" 
                    + tcari.getText() + "%' or pengarang like'%" 
                    + tcari.getText() + "%' or penerbit like'%" 
                    + tcari.getText() + "%' or kategori like'%" 
                    + tcari.getText() + "%' or isbn like'%"
                    + tcari.getText() + "%' " + "or stok like'%" 
                    + tcari.getText() + "%'";
            ResultSet resulSet = statement.executeQuery(sql);

            while (resulSet.next()) {
                Object[] o = new Object[7];
                o[0] = resulSet.getString("id_buku");
                o[1] = resulSet.getString("judul");
                 o[2] = resulSet.getString("pengarang");
                o[3] = resulSet.getString("penerbit");
                o[4] = resulSet.getString("kategori");
                o[5] = resulSet.getString("isbn");
                o[6] = resulSet.getString("stok");
              
                
                tabmode.addRow(o);
            }
            resulSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }         // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyReleased

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
try{
             
         String namaFile = "src/laporan/report1.jasper";
         Connection kon3 = (Connection) koneksi.connect();
             
            HashMap parameter = new HashMap();
         
            File report_file = new File(namaFile);
            JasperReport  jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter,conn);
            JasperViewer.viewReport(jasperPrint,false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btncetakActionPerformed

    private void txtstokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstokKeyPressed
if (evt.getKeyCode()== KeyEvent.VK_ENTER){
String kat = (String) cmbkat.getSelectedItem();
String jdl = (String) txtjudul.getText();
String penga = (String) txtpengarang.getText();
String pener = (String) txtpenerbit.getText();
String desc = (String) txtdesc.getText();
String stk = (String) txtstok.getText();


     if(kat.equals("Kategori")){
      JOptionPane.showMessageDialog(null, "Pilih Kategori!");
     }   
     else if(jdl.equals("")||penga.equals("")||pener.equals("")||desc.equals("")||stk.equals("")){
      JOptionPane.showMessageDialog(null, "Tidak Boleh Ada Yang Kosong!");     
     }
     else{
     try{
    String sql="select*from buku order by id_buku desc";
    Connection conn=(Connection) koneksi.connect();
   Statement stm = conn.createStatement();
    ResultSet res = stm.executeQuery(sql);
     String aidi;
    if(res.next()){
       
      String no=res.getString("id_buku").substring(1);
      String id = ""+(Integer.parseInt(no)+1);
      String nol=null;
      if(id.length()==1){
          nol="000";
      } 
      else if(id.length()==2){
          nol="00";
      }
       else if(id.length()==3){
          nol="0";
      }
       else if(id.length()==4){
          nol="";
      }
      aidi = "B"+nol+id; 
    }
    else{
       aidi = "B0001"; 
    }
    try {
            String query = "INSERT INTO buku VALUES "
                    + "('" + aidi+"','"+txtjudul.getText() + "','" + txtpengarang.getText() 
                    + "','" + txtpenerbit.getText() + "','" + cmbkat.getSelectedItem()  
                    + "','" + txtdesc.getText()+ "','" + txtstok.getText() + "')";
            java.sql.Connection kon = (Connection) koneksi.connect();
            java.sql.PreparedStatement mts = kon.prepareStatement(query);
            mts.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            kosong();
            tabelbuku();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
   }  
catch(Exception b){
    JOptionPane.showMessageDialog(null,b.getMessage());
}
// kosong();
            tabelbuku();
     }     
}// TODO add your handling code here:
    }//GEN-LAST:event_txtstokKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
Menu.Menu a=new Menu.Menu();
a.setVisible(true);
this.dispose();

// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
kategori a=new kategori();
a.setVisible(true);
this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Data_buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnnew;
    private javax.swing.JButton btnsave;
    private javax.swing.JComboBox<String> cmbkat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lblid;
    private javax.swing.JTable tabel1;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField txtdesc;
    private javax.swing.JTextField txtjudul;
    private javax.swing.JTextField txtpenerbit;
    private javax.swing.JTextField txtpengarang;
    private javax.swing.JTextField txtstok;
    // End of variables declaration//GEN-END:variables
}
