/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import Components.cardKamar;
import Database.Config;
import Page.Home;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class user extends javax.swing.JFrame {

    private String username;
    private String userlevel;
    private int iduser;

    /**
     * Creates new form user
     *
     * @param username
     * @param userLevel
     */
    public user(String username, String userLevel, int idUser) {
        initComponents();
        this.username = username;
        this.userlevel = userLevel;
        this.iduser = idUser;
        data();
    }

    private user() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void data() {
        // Set the layout manager to BoxLayout with Y_AXIS orientation
        showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));

        // Clear existing components
        showPanel.removeAll();

        String sql = "SELECT * FROM kamar";

        try {
            Connection conn = Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                String nama = result.getString("nama");
                String kelas = result.getString("kelas");
                String kode = result.getString("kode");
                String jenis = result.getString("tipe");
                String harga = result.getString("harga");

                cardKamar card = new cardKamar(nama, kelas, kode, jenis, harga);
                card.setPreferredSize(new Dimension(830, 140)); // Sesuaikan width dan height sesuai kebutuhan
                showPanel.add(card);
            }
        } catch (SQLException e) {
            System.out.println("Terjadi masalah saat mengambil data: " + e);
        }

        showPanel.revalidate(); // Update the panel's layout
        showPanel.repaint();    // Repaint the panel
    }

    public void handlePembayaran(String nama, String kelas, String kode, String jenis, String harga) {
        // Selanjutnya, isikan tabel pembayaran dengan informasi yang diterima
        try {
            Connection conn = Config.configDB();
            String sql = "INSERT INTO pemesanan (kode, nama, kelas, jenis, harga, id_user) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Eksekusi pernyataan SQL

            pstmt.setString(1, kode);
            pstmt.setString(2, nama);
            pstmt.setString(3, kelas);
            pstmt.setString(4, jenis);
            pstmt.setString(5, harga);
            pstmt.setInt(6, iduser);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Pesanan berhasil ditambahkan", "INFORMASI", JOptionPane.INFORMATION_MESSAGE);

            // Tambahkan logika lain jika diperlukan
        } catch (SQLException e) {
            System.out.println("Terjadi masalah saat memasukkan data pemesanan: " + e);
        }
    }

    private void showData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID_Pesanan");
        model.addColumn("Kode_Kamar");
        model.addColumn("Nama");
        model.addColumn("Kelas");
        model.addColumn("Jenis");
        model.addColumn("Harga");
        try {
            int no = 1;
            String sql = "SELECT id_pemesanan, kode, nama, kelas, jenis, harga FROM pemesanan WHERE id_user = ?";
            java.sql.Connection conn = (Connection) Config.configDB();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, iduser);
            java.sql.ResultSet res = stm.executeQuery();

            while (res.next()) {
                System.out.println("halo");
                model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3),
                    res.getString(4), res.getString(5), res.getString(6)});
            }
            tablePesanan.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void totalHarga() {
        int total = 0;

        for (int i = 0; i < tablePesanan.getRowCount(); i++) {
            try {
                int harga = Integer.parseInt(tablePesanan.getValueAt(i, 5).toString());
                total += harga * Integer.parseInt(txtLama.getText());

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        txtTotal.setText(String.valueOf(total));
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        showPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablePesanan = new javax.swing.JTable();
        txtKodeKamar = new javax.swing.JTextField();
        txtIdPesanan = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtKelas = new javax.swing.JTextField();
        txtJenis = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        jLabel1 = new javax.swing.JLabel();
        txtLama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        showPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout showPanelLayout = new javax.swing.GroupLayout(showPanel);
        showPanel.setLayout(showPanelLayout);
        showPanelLayout.setHorizontalGroup(
            showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1112, Short.MAX_VALUE)
        );
        showPanelLayout.setVerticalGroup(
            showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(showPanel);

        jTabbedPane1.addTab("Pesan Kamar", jScrollPane3);

        tablePesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID_Pesanan", "Kode_Kamar", "Nama", "Kelas", "Jenis", "Harga"
            }
        ));
        tablePesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePesananMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablePesanan);

        txtKodeKamar.setEditable(false);

        txtIdPesanan.setEditable(false);
        txtIdPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdPesananActionPerformed(evt);
            }
        });

        txtNama.setEditable(false);

        txtKelas.setEditable(false);

        txtJenis.setEditable(false);

        txtHarga.setEditable(false);

        rSMaterialButtonRectangle1.setText("DELETE");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Lama Booking");

        txtLama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLamaActionPerformed(evt);
            }
        });

        jLabel2.setText("Total Harga");

        txtTotal.setEditable(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle2.setText("PROSES");
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtIdPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtKodeKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtLama, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKodeKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Pesanan", jPanel1);

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel7.setText("Dashboard User");

        txtUsername.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        txtUsername.setText("Hai, User");

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(35, 35, 35)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtUsername)
                    .addComponent(jButton1))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        txtUsername.setText("Hai, " + username);
        showData();
    }//GEN-LAST:event_formWindowActivated

    private void txtIdPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdPesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdPesananActionPerformed

    private void tablePesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePesananMouseClicked
        int row = tablePesanan.rowAtPoint(evt.getPoint());
        String idPesanan = tablePesanan.getValueAt(row, 0).toString();
        txtIdPesanan.setText(idPesanan);

        String kodeKamar = tablePesanan.getValueAt(row, 1).toString();
        txtKodeKamar.setText(kodeKamar);

        String nama = tablePesanan.getValueAt(row, 2).toString();
        txtNama.setText(nama);

        String kelas = tablePesanan.getValueAt(row, 3).toString();
        txtKelas.setText(kelas);

        String jenis = tablePesanan.getValueAt(row, 4).toString();
        txtJenis.setText(jenis);

        String harga = tablePesanan.getValueAt(row, 5).toString();
        txtHarga.setText(harga);
    }//GEN-LAST:event_tablePesananMouseClicked

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        try {
            String sql = "DELETE FROM pemesanan WHERE id_pemesanan = '" + txtIdPesanan.getText() + "'";
            Connection conn = Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(this, "Data kamar berhasil dihapus", "INFORMASI", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println("Terjadi error saat menghapus data : " + e);
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void txtLamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLamaActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        totalHarga();
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        new Home().setVisible(true);
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new user().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private javax.swing.JPanel showPanel;
    private javax.swing.JTable tablePesanan;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtIdPesanan;
    private javax.swing.JTextField txtJenis;
    private javax.swing.JTextField txtKelas;
    private javax.swing.JTextField txtKodeKamar;
    private javax.swing.JTextField txtLama;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JLabel txtUsername;
    // End of variables declaration//GEN-END:variables
}
