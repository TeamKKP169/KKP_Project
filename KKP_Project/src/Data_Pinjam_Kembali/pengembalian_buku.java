/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Pinjam_Kembali;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hikigaya
 */
public class pengembalian_buku extends javax.swing.JFrame {
private DefaultTableModel tabmode;
String kembali;
String pulang;
    /**
     * Creates new form tesdatabalikbuku
     */
    public pengembalian_buku() {
        initComponents();
        //datatable();
        kosong();
          tampil();
        txtstatus.setEditable(false);
    }
protected void datatable(){
        Object[] Baris ={"Kode","NIS ","NAMA","ID BUKU","JUDUL BUKU","JUMLAH","TANGGAL PINJAM","TANGGAL KEMBALI","STATUS"};
        tabmode = new DefaultTableModel(null, Baris);
        jTable1.setModel(tabmode);
        String sql = "select * from pengembalian order by id_pinjam asc";
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
            String status = "Belum Kembali";
            String query = "select*from pengembalian where status = '"+status+"'";
            java.sql.Connection kon = (Connection) koneksi.koneksi();
            java.sql.Statement stm = kon.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);
            
            while(data.next()){
                model.addRow(new Object [] {
                data.getString(1),
                data.getString(2),
                data.getString(3),
                data.getString(4),
                data.getString(5),
                data.getString(6),
                data.getString(7),
                data.getString(8),
            
                data.getString(9)});
            }
            jTable1.setModel(model);
        }
        catch(Exception b){
            
        }
    }
private void kosong(){
    txttelat.setEditable(true);
    
    tglkembali.setDate(null);
    tglkembali.setEnabled(false);  
    
    txtid.setText("ID Peminjaman");
    txtpinjam.setText("Tanggal Peminjaman");
    txtblk.setText("Tanggal Balik");
    txttelat.setText("Keterlambatan");
   
       txtid.setEditable(false);
       txtpinjam.setEditable(false);
       txtblk.setEditable(false);
       txttelat.setEditable(false);
      
    tglkembali.setEnabled(false);
    
  btnbatal.setEnabled(false);
  btnproses.setEnabled(false);
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
        jTable1 = new javax.swing.JTable();
        tcari = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        txtpinjam = new javax.swing.JTextField();
        txtblk = new javax.swing.JTextField();
        tglkembali = new com.toedter.calendar.JDateChooser();
        txttelat = new javax.swing.JTextField();
        txtstatus = new javax.swing.JTextField();
        btnproses = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 1210, 220));

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });
        getContentPane().add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 91, 286, 30));
        getContentPane().add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 219, 33));
        getContentPane().add(txtpinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 219, 33));
        getContentPane().add(txtblk, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 219, 33));

        tglkembali.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglkembaliPropertyChange(evt);
            }
        });
        tglkembali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglkembaliKeyPressed(evt);
            }
        });
        getContentPane().add(tglkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 220, 30));
        getContentPane().add(txttelat, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, 110, 33));

        txtstatus.setText("sudah kembali");
        getContentPane().add(txtstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 479, 10, 0));

        btnproses.setText("Proses");
        btnproses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprosesActionPerformed(evt);
            }
        });
        getContentPane().add(btnproses, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 230, 40));

        btnbatal.setText("batal");
        getContentPane().add(btnbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, 230, 40));

        jLabel1.setText("Pencarian");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, 30));

        jButton1.setText("Kembali");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 570, 230, 40));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("About");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Help");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Exit");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased

     if(tcari.getText().equals(""))
   {
 tampil();   
}
else{
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nis");
        model.addColumn("Nama");
        model.addColumn("Id_buku");
        model.addColumn("judul");
        model.addColumn("Jumlah");
        model.addColumn("Tgl Pinjam");
        model.addColumn("Tgl Kembali");
     
        model.addColumn("Status");
        try{
            String status="Belum Kembali";
            String query = "select * from pengembalian where npm = '"
                    +tcari.getText()
                    +"'"
                    + "&& status = '"+status+"'";
            java.sql.Connection kon = (Connection) koneksi.koneksi();
            java.sql.Statement stm = kon.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);

            while(data.next()){
                model.addRow(new Object [] {
                    data.getString(1),
                    data.getString(2),
                    data.getString(3),
                    data.getString(4),
                    data.getString(5),
                    data.getString(6),
                    data.getString(7),
                    data.getString(8),
                    
                    data.getString(9)});
        }
        jTable1.setModel(model);
        }
        catch(Exception b){

        }    
        }       // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyReleased

    private void tglkembaliPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglkembaliPropertyChange
        if (tglkembali.getDate() != null) {
            SimpleDateFormat FormatTanggal = new SimpleDateFormat("dd MMMM yyyy");
            pulang = FormatTanggal.format(tglkembali.getDate());
        }
        try {
            DateFormat format = new SimpleDateFormat("dd MMMM yyyy");
            Date tanggalpinjam = format.parse(kembali);
            Date tanggalkembali = format.parse(pulang);
            long tanggalpinjam1 = tanggalpinjam.getTime();
            long tanggalkembali1 = tanggalkembali.getTime();
            long diff = tanggalkembali1 - tanggalpinjam1;
            long lama = diff / (24 * 60 * 60 * 1000);
            txttelat.setText(Long.toString(lama) + "");

            int denda;
            int telat = Integer.parseInt(txttelat.getText());
            denda = telat*1000;
            String tlt;
            tlt = String.valueOf(denda);
            if(telat<0){
                txttelat.setText("Keterlambatan");

                btnproses.setEnabled(false);
                btnbatal.setEnabled(true);
            }
            else if(tlt.equals("0")){

                btnproses.setEnabled(true);
                btnbatal.setEnabled(true);
            }
            else{

                btnproses.setEnabled(true);
                btnbatal.setEnabled(true);

            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tglkembaliPropertyChange

    private void tglkembaliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglkembaliKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglkembaliKeyPressed

    private void btnprosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprosesActionPerformed
        try {
            String status = "sudah kembali";
            String sql ="UPDATE pengembalian SET status = '"+txtstatus.getText()

            +"' WHERE id_pinjam = '"+txtid.getText()+"'";
            Connection conn=(Connection)koneksi.koneksi();
           PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        try{

            String id= txtid.getText();

            String query = "select id_buku from pengembalian where id_pinjam = '"+id+"'";
            Connection kon = (Connection) koneksi.koneksi();
           Statement stm = kon.createStatement();
          ResultSet data = stm.executeQuery(query);

            while(data.next()){
                String aidi = data.getString("id_buku");
                try{
                    String query1 = "select*from buku where id_buku = '"+aidi+"'";
                    java.sql.Connection kon1 = (Connection) koneksi.koneksi();
                    java.sql.Statement st = kon1.createStatement();
                    java.sql.ResultSet data1 = st.executeQuery(query1);

                    while(data1.next()){
                        int baris = jTable1.getSelectedRow();
                        String jmlh= jTable1.getValueAt(baris, 5).toString();
                        int jumlah= Integer.parseInt(data1.getString(7));
                        int jumlahpinjam = Integer.parseInt(jmlh);
                        int total = jumlah+jumlahpinjam;
                        try {
                            String sql1 ="UPDATE buku SET stok = '"+total
                            +"' WHERE id_buku = '"+aidi+"'";
                            java.sql.Connection conn1=(Connection)koneksi.koneksi();
                            java.sql.PreparedStatement pst=conn1.prepareStatement(sql1);
                            pst.execute();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
                        }

                    }
                }
                catch(Exception b){
                    JOptionPane.showMessageDialog(null, b.getMessage());
                }
            }

        }
        catch(Exception b){

        }
        tampil();
        //datatable();
        kosong();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnprosesActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
 btnbatal.setEnabled(true);
 tglkembali.setEnabled(true);
       
        try{

            int baris = jTable1.getSelectedRow();
            String id= jTable1.getValueAt(baris, 0).toString();

            String query = "select*from pengembalian where id_pinjam = '"+id+"'";
            java.sql.Connection kon = (Connection) koneksi.koneksi();
            java.sql.Statement stm = kon.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);

            while(data.next()){

                txtid.setText(data.getString(1));
                txtpinjam.setText(data.getString(7));
                txtblk.setText(data.getString(8));
                kembali = data.getString(8);
            }

        }
        catch(Exception b){

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
Menu.Menu a=new Menu.Menu();
a.setVisible(true);
this.dispose();



// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(pengembalian_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pengembalian_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pengembalian_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pengembalian_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pengembalian_buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnproses;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tcari;
    private com.toedter.calendar.JDateChooser tglkembali;
    private javax.swing.JTextField txtblk;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtpinjam;
    private javax.swing.JTextField txtstatus;
    private javax.swing.JTextField txttelat;
    // End of variables declaration//GEN-END:variables
}