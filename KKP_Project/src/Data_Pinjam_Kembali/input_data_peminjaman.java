/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Pinjam_Kembali;


import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author hikigaya
 */
public class input_data_peminjaman extends javax.swing.JFrame {
String pinjam;
String balik;
 DefaultTableModel tabel1 = new DefaultTableModel();
 DefaultTableModel tabel2 = new DefaultTableModel();
    /**
     * Creates new form datapinjam
     */
    public input_data_peminjaman() {
        initComponents();
        txtnm.setEditable(false);
    txtjdl.setEditable(false);
    refresh();
    //tambah_data_pinjam();
    }
private void loadKategori() {
        tabel1.addColumn("NIS");
        tabel1.addColumn("NAMA");

        jTable1.setModel(tabel1);

        TableColumn lebar_kolom;

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);

        lebar_kolom = jTable1.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable1.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);

        tabel1.getDataVector().removeAllElements();
        tabel1.fireTableDataChanged();
        try {
            java.sql.Connection kon = (Connection) koneksi.koneksi();
            java.sql.Statement stm = kon.createStatement();
            String query_bukaTabel = "select nis,nama from siswa ";
          ResultSet line_result = stm.executeQuery(query_bukaTabel);
            while (line_result.next()) {
                Object[] getO = new Object[2];
                getO[0] = line_result.getString("nis");
                getO[1] = line_result.getString("nama");

                tabel1.addRow(getO);
                
            }
            line_result.close();
            stm.close();
        } catch (Exception e) {
        }

    }
private void loadKategori1() {
        tabel2.addColumn("ID");
        tabel2.addColumn("JUDUL BUKU");

        tabelbuku.setModel(tabel2);

        TableColumn lebar_kolom;

        tabelbuku.setAutoResizeMode(tabelbuku.AUTO_RESIZE_OFF);

        lebar_kolom = tabelbuku.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = tabelbuku.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);

        tabel2.getDataVector().removeAllElements();
        tabel2.fireTableDataChanged();
        try {
            java.sql.Connection kon = (Connection) koneksi.koneksi();
            java.sql.Statement stm = kon.createStatement();
            String query_bukaTabel = "select id_buku, judul from buku";
          ResultSet line_result = stm.executeQuery(query_bukaTabel);
            while (line_result.next()) {
                Object[] getO = new Object[2];
                getO[0] = line_result.getString("id_buku");
                getO[1] = line_result.getString("judul");

                tabel2.addRow(getO);
                
            }
            line_result.close();
            stm.close();
        } catch (Exception e) {
        }

    }
 private void refresh() {
        txtnpm.setText("");
        txtnm.setText("");
        txtidbk.setText("");
        txtjdl.setText("");
        txtjmlh.setText("");
        
       
        
    }
 
 private void tambah_data_pengembalian(){
  if(tgl.getDate().equals("")&&tglbalik.getDate().equals("")){
            JOptionPane.showMessageDialog(null,"Tidak Boleh Ada yang kosong");
        }
        else{
            try{
                String aidi1;
                aidi1=txtidbk.getText();
                String query = "select*from buku where id_buku = '"+aidi1+"'";
                java.sql.Connection kon = (Connection) koneksi.koneksi();
                java.sql.Statement stm = kon.createStatement();
                java.sql.ResultSet data = stm.executeQuery(query);

                while(data.next()){
                    String jmlh = txtjmlh.getText();
                    String stk = data.getString(7);
                    int stok = Integer.parseInt(stk);
                    int jumlah = Integer.parseInt(jmlh);
                    if(stok == 0){
                        JOptionPane.showMessageDialog(null,"Stok Buku Telah Kosong!");
                    }
                    else if(jumlah>stok){
                        JOptionPane.showMessageDialog(null,"Jumlah Buku Tidak Cukup!");
                    }
                    else{

                        try{
                            String sql1="select*from pengembalian order by id_pinjam desc";
                            java.sql.Connection conn=(Connection) koneksi.koneksi();
                            java.sql.Statement stm1 = conn.createStatement();
                            java.sql.ResultSet res1 = stm1.executeQuery(sql1);
                            String aidi;
                            if(res1.next()){

                                String no=res1.getString("id_pinjam").substring(1);
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
                                aidi = "P"+nol+id;
                            }
                            else{
                                aidi = "P0001";
                            }
                            try {

                          
                                String query1 = "INSERT INTO pengembalian VALUES "
                                + "('" + aidi+"','"+txtnpm.getText()
                                + "','" + txtnm.getText()
                                + "','" + txtidbk.getText()
                                + "','" + txtjdl.getText()
                                + "','" + txtjmlh.getText()
                                + "','" + pinjam
                                + "','" + balik
                               
                                + "','" + "Belum Kembali"+ "')";
                                java.sql.Connection kon1 = (Connection) koneksi.koneksi();
                                java.sql.PreparedStatement mts = kon1.prepareStatement(query1);
                                mts.execute();
                                //JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");

                                try{
                                    String query2 = "select*from buku where id_buku = '"+txtidbk.getText()+"'";
                                    java.sql.Connection kon2 = (Connection) koneksi.koneksi();
                                    java.sql.Statement st = kon2.createStatement();
                                    java.sql.ResultSet data2 = st.executeQuery(query2);

                                    while(data2.next()){
                                        int jumlah1= Integer.parseInt(data2.getString(7));
                                        int jumlahpinjam = Integer.parseInt(txtjmlh.getText());
                                        int total = jumlah1-jumlahpinjam;
                                        try {
                                            String sql3 ="UPDATE buku SET stok = '"+total
                                            +"' WHERE id_buku = '"+txtidbk.getText()+"'";
                                            java.sql.Connection conn1=(Connection)koneksi.koneksi();
                                            java.sql.PreparedStatement pst=conn1.prepareStatement(sql3);
                                            pst.execute();
                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
                                        }

                                    }
                                }
                                catch(Exception b){
                                    JOptionPane.showMessageDialog(null, b.getMessage());
                                }
        // TODO add your handling code here:
         
                                new input_data_peminjaman().setVisible(true);
                                dispose();

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e.getMessage());
                            }

                        }
                        catch(Exception b){
                            JOptionPane.showMessageDialog(null,b.getMessage());
                        }

                    }

                }

            }
            catch(Exception b){
                JOptionPane.showMessageDialog(null,b.getMessage());
            }

      //  refresh();
        
  }   
 
 }
 
 
 private void tambah_data_pinjam(){
  if(tgl.getDate().equals("")&&tglbalik.getDate().equals("")){
            JOptionPane.showMessageDialog(null,"Tidak Boleh Ada yang kosong");
        }
        else{
            try{
                String aidi1;
                aidi1=txtidbk.getText();
                String query = "select*from buku where id_buku = '"+aidi1+"'";
                java.sql.Connection kon = (Connection) koneksi.koneksi();
                java.sql.Statement stm = kon.createStatement();
                java.sql.ResultSet data = stm.executeQuery(query);

                while(data.next()){
                    String jmlh = txtjmlh.getText();
                    String stk = data.getString(7);
                    int stok = Integer.parseInt(stk);
                    int jumlah = Integer.parseInt(jmlh);
                    if(stok == 0){
                        JOptionPane.showMessageDialog(null,"Stok Buku Telah Kosong!");
                    }
                    else if(jumlah>stok){
                        JOptionPane.showMessageDialog(null,"Jumlah Buku Tidak Cukup!");
                    }
                    else{

                        try{
                            String sql1="select*from pinjaman order by id_pinjam desc";
                            java.sql.Connection conn=(Connection) koneksi.koneksi();
                            java.sql.Statement stm1 = conn.createStatement();
                            java.sql.ResultSet res1 = stm1.executeQuery(sql1);
                            String aidi;
                            if(res1.next()){

                                String no=res1.getString("id_pinjam").substring(1);
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
                                aidi = "P"+nol+id;
                            }
                            else{
                                aidi = "P0001";
                            }
                            try {

                          
                                String query1 = "INSERT INTO pinjaman VALUES "
                                + "('" + aidi+"','"+txtnpm.getText()
                                + "','" + txtnm.getText()
                                + "','" + txtidbk.getText()
                                + "','" + txtjdl.getText()
                                + "','" + txtjmlh.getText()
                                + "','" + pinjam
                                + "','" + balik
                               
                                + "','" + "Belum Kembali"+ "')";
                                java.sql.Connection kon1 = (Connection) koneksi.koneksi();
                                java.sql.PreparedStatement mts = kon1.prepareStatement(query1);
                                mts.execute();
                                JOptionPane.showMessageDialog(null, "Peminjaman  berhasil");

                                try{
                                    String query2 = "select*from buku where id_buku = '"+txtidbk.getText()+"'";
                                    java.sql.Connection kon2 = (Connection) koneksi.koneksi();
                                    java.sql.Statement st = kon2.createStatement();
                                    java.sql.ResultSet data2 = st.executeQuery(query2);

                                
                                }
                                catch(Exception b){
                                    JOptionPane.showMessageDialog(null, b.getMessage());
                                }
        // TODO add your handling code here:
         
                                new input_data_peminjaman().setVisible(true);
                                dispose();

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e.getMessage());
                            }

                        }
                        catch(Exception b){
                            JOptionPane.showMessageDialog(null,b.getMessage());
                        }

                    }

                }

            }
            catch(Exception b){
                JOptionPane.showMessageDialog(null,b.getMessage());
            }

       // refresh();
        
        
        
        
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

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        tcari = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jDialog2 = new javax.swing.JDialog();
        tcari1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelbuku = new javax.swing.JTable();
        txtnpm = new javax.swing.JTextField();
        txtnm = new javax.swing.JTextField();
        txtidbk = new javax.swing.JTextField();
        txtjdl = new javax.swing.JTextField();
        txtjmlh = new javax.swing.JTextField();
        btntambah = new javax.swing.JButton();
        tgl = new com.toedter.calendar.JDateChooser();
        tglbalik = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        jDialog1.setMinimumSize(new java.awt.Dimension(400, 350));

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton5.setText("KEMBALI");

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });

        jLabel8.setText("CARI");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel8)))
                        .addGap(0, 79, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tcari, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap())
        );

        jDialog2.setMinimumSize(new java.awt.Dimension(400, 350));

        tcari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcari1KeyReleased(evt);
            }
        });

        jLabel9.setText("CARI");

        jButton7.setText("KEMBALI");

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane3MousePressed(evt);
            }
        });

        tabelbuku.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelbuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelbukuMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelbukuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelbuku);

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7)
                            .addGroup(jDialog2Layout.createSequentialGroup()
                                .addComponent(tcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel9)))
                        .addGap(0, 79, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tcari1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(737, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtnpm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnpmMouseClicked(evt);
            }
        });
        txtnpm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnpmKeyReleased(evt);
            }
        });
        getContentPane().add(txtnpm, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 310, 30));

        txtnm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnmKeyReleased(evt);
            }
        });
        getContentPane().add(txtnm, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 310, 30));

        txtidbk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtidbkMouseClicked(evt);
            }
        });
        txtidbk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidbkActionPerformed(evt);
            }
        });
        txtidbk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtidbkKeyReleased(evt);
            }
        });
        getContentPane().add(txtidbk, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 310, 30));
        getContentPane().add(txtjdl, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 310, 30));

        txtjmlh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtjmlhMouseClicked(evt);
            }
        });
        txtjmlh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjmlhActionPerformed(evt);
            }
        });
        txtjmlh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtjmlhKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtjmlhKeyPressed(evt);
            }
        });
        getContentPane().add(txtjmlh, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 310, 30));

        btntambah.setText("TAMBAH");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });
        getContentPane().add(btntambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 130, 40));

        tgl.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglPropertyChange(evt);
            }
        });
        getContentPane().add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 170, 31));

        tglbalik.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglbalikPropertyChange(evt);
            }
        });
        getContentPane().add(tglbalik, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 170, 31));

        jButton2.setText("KEMBALI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 600, 200, 40));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 40, 30));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 40, 30));

        jLabel1.setText("NIS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        jLabel2.setText("NAMA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        jLabel3.setText("ID BUKU");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        jLabel4.setText("JUDUL BUKU");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        jLabel5.setText("JUMLAH");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        jLabel6.setText("TANGGAL PINJAM");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        jLabel7.setText("TANGGAL KEMBALI");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jButton6.setText("REFRESH");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 480, -1, 40));

        jButton8.setText("Cek Data");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 480, 150, 40));

        jButton9.setText("jButton9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, 130, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtnpmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnpmMouseClicked
        txtnpm.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnpmMouseClicked

    private void txtnpmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnpmKeyReleased
        try{
            String query = "select nama from siswa where nis = '"+txtnpm.getText()+"'";
            java.sql.Connection kon = (Connection) koneksi.koneksi();
            java.sql.Statement stm = kon.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);

            if(data.next()){
                txtnm.setText(data.getString("nama"));
            }
            else{
                txtnm.setText("Jika npm benar makan Nama akan tercantum");
            }
        }
        catch(Exception b){
            JOptionPane.showMessageDialog(null, b.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnpmKeyReleased

    private void txtnmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnmKeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmKeyReleased

    private void txtidbkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtidbkMouseClicked
        txtidbk.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidbkMouseClicked

    private void txtidbkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidbkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidbkActionPerformed

    private void txtidbkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidbkKeyReleased
        try{
            String query = "select judul from buku where id_buku = '"+txtidbk.getText()+"'";
            java.sql.Connection kon = (Connection) koneksi.koneksi();
            java.sql.Statement stm = kon.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);

            if(data.next()){
                txtjdl.setText(data.getString("judul"));
            }
            else{
                txtjdl.setText("Judul");
            }
        }
        catch(Exception b){
            JOptionPane.showMessageDialog(null, b.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidbkKeyReleased

    private void txtjmlhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtjmlhMouseClicked
        txtjmlh.setText(null);
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjmlhMouseClicked

    private void txtjmlhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjmlhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjmlhActionPerformed

    private void txtjmlhKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjmlhKeyTyped
        char c = evt.getKeyChar();
        if (! ((Character.isDigit(c) ||
            (c == KeyEvent.VK_BACK_SPACE) ||
            (c == KeyEvent.VK_DELETE))
        )
        )
        {
            evt.consume();
        }            // TODO add your handling code here:
    }//GEN-LAST:event_txtjmlhKeyTyped

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
tambah_data_pinjam();

tambah_data_pengembalian();
refresh();

        // TODO add your handling code here:
    }//GEN-LAST:event_btntambahActionPerformed

    private void tglPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglPropertyChange
if (tgl.getDate() != null) {
     SimpleDateFormat FormatTanggal = new SimpleDateFormat("dd MMMM yyyy");
     pinjam = FormatTanggal.format(tgl.getDate());
}        // TODO add your handling code here:
    }//GEN-LAST:event_tglPropertyChange

    private void tglbalikPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglbalikPropertyChange
    if (tglbalik.getDate() != null) {
     SimpleDateFormat FormatTanggal = new SimpleDateFormat("dd MMMM yyyy");
     balik = FormatTanggal.format(tglbalik.getDate());
}        // TODO add your handling code here:
    }//GEN-LAST:event_tglbalikPropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
jDialog1.setLocationRelativeTo(null);
        loadKategori();
        jDialog1.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
tabel1.getDataVector().removeAllElements();
        tabel1.fireTableDataChanged();

        try {
            java.sql.Connection kon = (Connection) koneksi.koneksi();
                java.sql.Statement stm = kon.createStatement();

            String sql = "select * from siswa where nis like '%" 
                    + tcari.getText() + "%' or nis like'%" 
                    + tcari.getText() + "%' or nama like'" 
                    
                    + tcari.getText() + "%'";
            ResultSet resulSet = stm.executeQuery(sql);

            while (resulSet.next()) {
                Object[] o = new Object[2];
                o[0] = resulSet.getString("Nis");
                o[1] = resulSet.getString("Nama");
                
                
                tabel1.addRow(o);
            }
            resulSet.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyReleased

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
String a = 
jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
txtnpm.setText(a);
String b = 
 jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();   
 txtnm.setText(b);
 jDialog1.setVisible(false);  
        
// TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tcari1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcari1KeyReleased
tabel2.getDataVector().removeAllElements();
        tabel2.fireTableDataChanged();

        try {
            java.sql.Connection kon = (Connection) koneksi.koneksi();
                java.sql.Statement stm = kon.createStatement();

           
            String sql = "select * from buku where id_buku like '%" 
                    + tcari1.getText() + "%' or id_buku like'%" 
                    + tcari1.getText() + "%' or judul like'" 
                   
                    + tcari1.getText() + "%'";
            ResultSet resulSet = stm.executeQuery(sql);

            while (resulSet.next()) {
                Object[] o = new Object[2];
                o[0] = resulSet.getString("id_buku");
                o[1] = resulSet.getString("judul");
                
                
                tabel2.addRow(o);
            }
            resulSet.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }          // TODO add your handling code here:
    }//GEN-LAST:event_tcari1KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jDialog2.setLocationRelativeTo(null);
        loadKategori1();
        jDialog2.setVisible(true);           // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabelbukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelbukuMouseClicked
    // TODO add your handling code here:
    }//GEN-LAST:event_tabelbukuMouseClicked

    private void tabelbukuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelbukuMousePressed
        String c = 
tabelbuku.getValueAt(tabelbuku.getSelectedRow(), 0).toString();
txtidbk.setText(c);
String d = 
 tabelbuku.getValueAt(tabelbuku.getSelectedRow(), 1).toString();   
 txtjdl.setText(d);
 jDialog2.setVisible(false);  // TODO add your handling code here:
    }//GEN-LAST:event_tabelbukuMousePressed

    private void jScrollPane3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MousePressed
       // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane3MousePressed

    private void txtjmlhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjmlhKeyPressed
 if (evt.getKeyCode()== KeyEvent.VK_ENTER)
        if(tgl.getDate().equals("")&&tglbalik.getDate().equals("")){
            JOptionPane.showMessageDialog(null,"Tidak Boleh Ada yang kosong");
        }
        else{
            try{
                String aidi1;
                aidi1=txtidbk.getText();
                String query = "select*from buku where id_buku = '"+aidi1+"'";
                java.sql.Connection kon = (Connection) koneksi.koneksi();
                java.sql.Statement stm = kon.createStatement();
                java.sql.ResultSet data = stm.executeQuery(query);

                while(data.next()){
                    String jmlh = txtjmlh.getText();
                    String stk = data.getString(7);
                    int stok = Integer.parseInt(stk);
                    int jumlah = Integer.parseInt(jmlh);
                    if(stok == 0){
                        JOptionPane.showMessageDialog(null,"Stok Buku Telah Kosong!");
                    }
                    else if(jumlah>stok){
                        JOptionPane.showMessageDialog(null,"Jumlah Buku Tidak Cukup!");
                    }
                    else{

                        try{
                            String sql1="select*from pinjaman order by id_pinjam desc";
                            java.sql.Connection conn=(Connection) koneksi.koneksi();
                            java.sql.Statement stm1 = conn.createStatement();
                            java.sql.ResultSet res1 = stm1.executeQuery(sql1);
                            String aidi;
                            if(res1.next()){

                                String no=res1.getString("id_pinjam").substring(1);
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
                                aidi = "P"+nol+id;
                            }
                            else{
                                aidi = "P0001";
                            }
                            try {

                          
                                String query1 = "INSERT INTO pinjaman VALUES "
                                + "('" + aidi+"','"+txtnpm.getText()
                                + "','" + txtnm.getText()
                                + "','" + txtidbk.getText()
                                + "','" + txtjdl.getText()
                                + "','" + txtjmlh.getText()
                                + "','" + pinjam
                                + "','" + balik
                               
                                + "','" + "Belum Kembali"+ "')";
                                java.sql.Connection kon1 = (Connection) koneksi.koneksi();
                                java.sql.PreparedStatement mts = kon1.prepareStatement(query1);
                                mts.execute();
                                JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");

                                try{
                                    String query2 = "select*from buku where id_buku = '"+txtidbk.getText()+"'";
                                    java.sql.Connection kon2 = (Connection) koneksi.koneksi();
                                    java.sql.Statement st = kon2.createStatement();
                                    java.sql.ResultSet data2 = st.executeQuery(query2);

                                    while(data2.next()){
                                        int jumlah1= Integer.parseInt(data2.getString(7));
                                        int jumlahpinjam = Integer.parseInt(txtjmlh.getText());
                                        int total = jumlah1-jumlahpinjam;
                                        try {
                                            String sql3 ="UPDATE buku SET stok = '"+total
                                            +"' WHERE id_buku = '"+txtidbk.getText()+"'";
                                            java.sql.Connection conn1=(Connection)koneksi.koneksi();
                                            java.sql.PreparedStatement pst=conn1.prepareStatement(sql3);
                                            pst.execute();
                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
                                        }

                                    }
                                }
                                catch(Exception b){
                                    JOptionPane.showMessageDialog(null, b.getMessage());
                                }

                                new input_data_peminjaman().setVisible(true);
                                dispose();

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e.getMessage());
                            }

                        }
                        catch(Exception b){
                            JOptionPane.showMessageDialog(null,b.getMessage());
                        }

                    }

                }

            }
            catch(Exception b){
                JOptionPane.showMessageDialog(null,b.getMessage());
            }

        refresh();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjmlhKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
Menu.Menu a=new Menu.Menu();
a.setVisible(true);
dispose();       // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
tabelpinjam a=new tabelpinjam();
a.setVisible(true);
dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
String reportSource = null;
        String reportDest = null;
        
        try {
            Connection koneksi = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/app_book", "root", "@@##bagas");
            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) koneksi;
            reportSource = System.getProperty("user.dir") + "/src/laporan/peminjaman.jrxml";
            reportDest = System.getProperty("user.dir") + "/src/laporan/peminjaman.jasper";
            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);   
        }
        
        catch (Exception e) {
            System.out.println(e);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(input_data_peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(input_data_peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(input_data_peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(input_data_peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new input_data_peminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btntambah;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tabelbuku;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tcari1;
    private com.toedter.calendar.JDateChooser tgl;
    private com.toedter.calendar.JDateChooser tglbalik;
    private javax.swing.JTextField txtidbk;
    private javax.swing.JTextField txtjdl;
    private javax.swing.JTextField txtjmlh;
    private javax.swing.JTextField txtnm;
    private javax.swing.JTextField txtnpm;
    // End of variables declaration//GEN-END:variables
}
