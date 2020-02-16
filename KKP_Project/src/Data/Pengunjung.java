package Data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Data.koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import login.Menu_login;

/**
 *
 * @author hikigaya
 */
public class Pengunjung extends javax.swing.JFrame {
private final Connection conn = koneksi.connect();

public Timer t = null;
    private int count = 0;

    /**
     * Creates new form Pengunjung
     */
    public Pengunjung() {
        initComponents();
        txtnama.setEditable(false);
        txttelpon.setEditable(false);
        txttanggal.setEditable(false);
        txtkd.setEditable(false);
        txtjam.setEditable(false);
        //txtnis.setEditable(false);
        txtnis.setText("");
        tanggal();
        autoNumber();
        refresh();
         getData();
         jDialog1.setLocationRelativeTo(this);
         setTanggal();
          setJam();
          setwaktu();
   
    t = new Timer(100, (ActionEvent e) -> {
            count +=5;
            jProgressBar1.setValue(count);
            if(jProgressBar1.getValue()<100){
                jProgressBar1.setValue(jProgressBar1.getValue()+ 1);
            }
            if(jProgressBar1.getValue()==100 ){
               
               jDialog1.setLocationRelativeTo(this);
                t.stop();
                
            } 
        });
        t.start();
    
    
    
    }
    
  public void setTanggal(){
    java.util.Date skrg = new java.util.Date();
    java.text.SimpleDateFormat kal = new
    java.text.SimpleDateFormat("dd-MM-yyyy");
    jLabel9.setText(kal.format(skrg));
}

public void setJam(){
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            String nol_jam = "", nol_menit = "",nol_detik = "";
            java.util.Date dateTime = new java.util.Date();
            int nilai_jam = dateTime.getHours();
            int nilai_menit = dateTime.getMinutes();
            int nilai_detik = dateTime.getSeconds();
            if(nilai_jam <= 9) nol_jam= "0";
            if(nilai_menit <= 9) nol_menit= "0";
            if(nilai_detik <= 9) nol_detik= "0";
            String waktu = nol_jam + Integer.toString(nilai_jam);
            String menit = nol_menit + Integer.toString(nilai_menit);
            String detik = nol_detik + Integer.toString(nilai_detik);
            txtjam.setText(waktu+":"+menit+":"+detik+"");
        }
    };
    new Timer(1000, taskPerformer).start();
} 
public void setwaktu(){
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            String nol_jam = "", nol_menit = "",nol_detik = "";
            java.util.Date dateTime = new java.util.Date();
            int nilai_jam = dateTime.getHours();
            int nilai_menit = dateTime.getMinutes();
            int nilai_detik = dateTime.getSeconds();
            if(nilai_jam <= 9) nol_jam= "0";
            if(nilai_menit <= 9) nol_menit= "0";
            if(nilai_detik <= 9) nol_detik= "0";
            String waktu = nol_jam + Integer.toString(nilai_jam);
            String menit = nol_menit + Integer.toString(nilai_menit);
            String detik = nol_detik + Integer.toString(nilai_detik);
            jLabel10.setText(waktu+":"+menit+":"+detik+"");
        }
    };
    new Timer(1000, taskPerformer).start();
}  

    
  public void getData() {
        jLabel12.setText(txtkd.getText());
        jLabel13.setText(txtnis.getText());
       jLabel14.setText(txtnama.getText());
       jLabel15.setText(txttelpon.getText());
       jLabel16.setText(txttanggal.getText());
       jLabel11.setText(txtjam.getText());
         
       
  }
    
    public void tanggal(){
Calendar cal = new GregorianCalendar();
int day = cal.get(Calendar.DAY_OF_MONTH);
int month =cal.get(Calendar.MONTH);
int year = cal.get(Calendar.YEAR);




txttanggal.setText(
        day + "-" + 
        (month + 1) 
           + "-" + year);
}
    
public void autoNumber() {
		try {
			Connection konek = koneksi.connect();
			Statement state = konek.createStatement();
			String query = "SELECT MAX(right(id,2)) AS no FROM pengunjung";

			ResultSet rs = state.executeQuery(query);
			while (rs.next()) {
				if (rs.first() == false) {
					txtkd.setText("P001");
				} else {
					rs.last();
					int id = rs.getInt(1) + 1;
					String no = String.valueOf(id);
					int noLong = no.length();
					for (int a = 0; a < 2 - noLong; a++) {
						no = "0" + no;
					}
					txtkd.setText("P" + no);
				}

			}
			rs.close();
			state.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}    

   private void refresh() {
      //  txtkd.setText("");
        txtnis.setText("");
        txtnama.setText("");
        txttelpon.setText("");
       
        }  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        daftar = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDialog1 = new javax.swing.JDialog();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        txtnis = new javax.swing.JTextField();
        txttelpon = new javax.swing.JTextField();
        txttanggal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtkd = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtjam = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        daftar.setMinimumSize(new java.awt.Dimension(400, 300));

        jLabel2.setText("Untuk Pendaftaran Hubungi Petugas Perpustakaan");

        jLabel3.setText("Selamat Berkunjung");

        javax.swing.GroupLayout daftarLayout = new javax.swing.GroupLayout(daftar.getContentPane());
        daftar.getContentPane().setLayout(daftarLayout);
        daftarLayout.setHorizontalGroup(
            daftarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daftarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(daftarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        daftarLayout.setVerticalGroup(
            daftarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daftarLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addGap(47, 47, 47)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        jDialog1.setMinimumSize(new java.awt.Dimension(355, 350));
        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setText("jLabel12");
        jDialog1.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 150, 30));

        jLabel13.setText("jLabel13");
        jDialog1.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 10, 30));

        jLabel14.setText("jLabel14");
        jDialog1.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 20, 20));

        jLabel15.setText("jLabel15");
        jDialog1.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 20, 20));

        jLabel16.setText("jLabel16");
        jDialog1.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 160, 30));

        jLabel7.setText("Kode Pengunjung");
        jDialog1.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        jLabel8.setText("Tanggal berkunjung");
        jDialog1.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        jButton4.setText("Close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 240, 40));

        jLabel11.setText("jLabel11");
        jDialog1.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, -1));

        jLabel17.setText("Waktu berkunjung");
        jDialog1.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        jProgressBar1.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jProgressBar1InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jDialog1.getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 240, 20));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(685, 754));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtnis.setText("masukan nis di sini !");
        txtnis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnisMouseClicked(evt);
            }
        });
        txtnis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnisKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnisKeyReleased(evt);
            }
        });
        getContentPane().add(txtnis, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 392, 49));
        getContentPane().add(txttelpon, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 392, 50));
        getContentPane().add(txttanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 150, 51));

        jButton1.setText("DAFTAR ANGGOTA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 490, -1, 40));

        jButton2.setText("SUBMIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 490, 142, 38));
        getContentPane().add(txtkd, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, 163, 27));

        jLabel1.setText("NAMA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 92, 49));
        getContentPane().add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 392, 52));

        jButton3.setText("Keluar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 142, 41));

        jLabel4.setText("TELPON");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 92, 49));

        jLabel5.setText("TANGGAL");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 92, 49));

        jLabel6.setText("NIS");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 92, 40));

        jLabel9.setText("jLabel9");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 80, 30));

        jLabel10.setText("jLabel10");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 60, 30));
        getContentPane().add(txtjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 220, 50));

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

    private void txtnisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnisKeyReleased
try{
            String query = "select nama,no_hp from siswa where nis = '"+txtnis.getText()+"'";
            java.sql.Connection kon = (Connection) koneksi.connect();
            java.sql.Statement stm = kon.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);

            if(data.next()){
                txtnama.setText(data.getString("nama"));
                txttelpon.setText(data.getString("no_hp"));
            }
            else{
                txtnama.setText("");
                txttelpon.setText("");
            }
        }
        catch(Exception b){
            JOptionPane.showMessageDialog(null, b.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtnisKeyReleased

    private void txtnisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnisKeyPressed
         if (evt.getKeyCode()== KeyEvent.VK_ENTER)
         {
             
        String id = txtkd.getText();
        String Nis = txtnis.getText();
        String Nama = txtnama.getText();
        String Telpon = txttelpon.getText();
        String Tanggal = txttanggal.getText();
        String Kam= txtjam.getText();
        if(id.equals("")||
           Nis.equals("")||
           Nama.equals("")||
           Telpon.equals("")||
           Tanggal.equals("")||
           Kam.equals("")
                ){
            JOptionPane.showMessageDialog(null,"Data Tidak Tersedia ! Daftar Anggota Terlebih Dahulu ... ");
refresh();
        }
        
        else{
            try {
                String sql1 = "INSERT INTO pengunjung"
                + " VALUES ('"
                 +txtkd.getText()
                +"','" + txtnis.getText()
                + "','" + txtnama.getText()
                + "','" + txttelpon.getText()
                 + "','" +txttanggal.getText()
                        + "','" +txtjam.getText()
                + "')";
                java.sql.Connection kon = (Connection) koneksi.connect();
                java.sql.PreparedStatement pdt = kon.prepareStatement(sql1);
                pdt.execute();
                JOptionPane.showMessageDialog(null, "Selamat Datang Di Perpustakaan");
               // new datamahasiswa().setVisible(true);
              //  dispose();//
            
             autoNumber();
             refresh();
             jDialog1.setVisible(true);
            getData();
           
           
            } catch (Exception b) {
                JOptionPane.showMessageDialog(this, b.getMessage());
            }
        
        
        }     
         }// TODO add your handling code here:
    }//GEN-LAST:event_txtnisKeyPressed

    private void txtnisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnisMouseClicked
txtnis.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_txtnisMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
daftar.setLocationRelativeTo(null);
       
        daftar.setVisible(true);
//datasiswa a=new datasiswa();
//a.setVisible(true);
//dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
Menu_login a=new Menu_login();
a.setVisible(true);
dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 {
             
        String id = txtkd.getText();
        String Nis = txtnis.getText();
        String Nama = txtnama.getText();
        String Telpon = txttelpon.getText();
        String Tanggal = txttanggal.getText();
        String Jam = txtjam.getText();
        
        if(id.equals("")||
           Nis.equals("")||
           Nama.equals("")||
           Telpon.equals("")||
           Tanggal.equals("")||
                
           Jam.equals("")
                ){
            JOptionPane.showMessageDialog(null,"Data Tidak Tersedia ! Daftar Anggota Terlebih Dahulu ... ");
refresh();
        }
        
        else{
            try {
                String sql1 = "INSERT INTO pengunjung"
                + " VALUES ('"
                 +txtkd.getText()
                +"','" + txtnis.getText()
                + "','" + txtnama.getText()
                + "','" + txttelpon.getText()
                  + "','" +txttanggal.getText()      
                 + "','" +txtjam.getText()
                + "')";
                java.sql.Connection kon = (Connection) koneksi.connect();
                java.sql.PreparedStatement pdt = kon.prepareStatement(sql1);
                pdt.execute();
                JOptionPane.showMessageDialog(null, "Selamat Datang Di Perpustakaan");
               // new datamahasiswa().setVisible(true);
              //  dispose();//
             
             autoNumber();
             refresh();
             jDialog1.setVisible(true);
             getData();
            } catch (Exception b) {
                JOptionPane.showMessageDialog(this, b.getMessage());
            }
          
        
        }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 jDialog1.setVisible(false);


               // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jProgressBar1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jProgressBar1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jProgressBar1InputMethodTextChanged

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
            java.util.logging.Logger.getLogger(Pengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pengunjung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog daftar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField txtjam;
    private javax.swing.JTextField txtkd;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnis;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txttelpon;
    // End of variables declaration//GEN-END:variables

   
}
