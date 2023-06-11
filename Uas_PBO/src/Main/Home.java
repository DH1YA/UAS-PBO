/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import Database.DBconnection;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.text.MessageFormat;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ACER
 */
public class Home extends javax.swing.JFrame {

    int xx, xy;
    
    Connection conn;
    Statement stmt;
    private PreparedStatement pst = null;
    ResultSet rs;
    
    DBconnection koneksi;
    
    DefaultTableModel dtm;
    ResultSetMetaData rsmd;
    
    
    String tampilan = "yyyy-MM-dd";
    SimpleDateFormat fm = new SimpleDateFormat(tampilan);
    String expired,nama,kuantitas,harga,kategori;
    
    public Home() throws SQLException {
        initComponents();

        
        koneksi = new DBconnection();
        conn = koneksi.getConnection();
//        tampilTabel();
        table();
        String imagePath = "gambar/pesut.png";
        
        Image image = null;
        try {
            image = ImageIO.read(FormLogin.class.getClassLoader().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(resize(logo.getWidth(), logo.getHeight(), image));
        logo.setIcon((icon));
        
    }
    
    private void table(){
        DefaultTableModel tbl  = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowHeight(40);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
//        tbl.setRowCount(0);        
        try{
            // membuat statement dan mengirimkan query ke database
            String sql = "SELECT * FROM produk";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            // mengisi data dari ResultSet ke dalam model
            if(!rs.next()){
                JOptionPane.showMessageDialog(rootPane, "data tidak ada");
            }
            while(rs.next()) {
                String no = Integer.toString(rs.getInt("id_produk"));
                nama = rs.getString("nama_produk");
                this.harga = Integer.toString(rs.getInt("harga_produk"));
                kuantitas = Integer.toString(rs.getInt("kuantitas"));
                kategori = rs.getString("kategori_produk");
                expired = String.valueOf(fm.format(rs.getDate("expired")));
                tbl.addRow(new Object []{no, nama, this.harga, kuantitas, kategori, expired});
                
            }
//            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }jTable1.setModel(tbl);
    }
    
    private DefaultTableModel buatTabelModel(ResultSet rs) throws SQLException{
        dtm = new DefaultTableModel();
        rsmd = (ResultSetMetaData) rs.getMetaData();
        int jmlKolom = rsmd.getColumnCount();
        
        String[] namaKolom = new String[jmlKolom];
        for (int i=0;i< jmlKolom; i++){
            namaKolom[i] = rsmd.getColumnName(i+1);
        }
        dtm.setColumnIdentifiers(namaKolom);
        
        while (rs.next()){
            String[] dataProduk = new String[jmlKolom];
            for (int i = 0; i< jmlKolom; i++){
                dataProduk[i] = rs.getString(i+1);
            }
        }return dtm;
    } 
    
    public boolean tampilTabel() throws SQLException{
        String sql = "SELECT * FROM produk";
        try {
           stmt = conn.createStatement();
           rs = stmt.executeQuery(sql);
           
//           if(rs.last()){
//               if(rs.getRow()==0){
//                   JOptionPane.showMessageDialog(rootPane, "data tidak ditemukan");
//               }else{
//                   rs.beforeFirst(); // Kembali ke baris pertama
//
//                while (rs.next()) {
//                    rs.beforeFirst();
//                    dtm = buatTabelModel(rs);
//                    jTable1.setModel(dtm);
//                    jTable1.setAutoCreateRowSorter(true);
//                }
//               }
//           }
//           rs.last();
//           if(rs.getRow() == 0){
//               JOptionPane.showMessageDialog(rootPane, "data tidak ditemukan");
//           } else{
//               rs.beforeFirst();
//               dtm = buatTabelModel(rs);
//               jTable1.setModel(dtm);
//               jTable1.setAutoCreateRowSorter(true);
//           }
            return true;
        } catch (SQLException e) {
            stmt.close();
            JOptionPane.showMessageDialog(null, "error 2 : " + e.getMessage());
        }
        stmt.close();
        return false;
    }
    
    public void clear(){
        txtNama.setText("");
        txtHarga.setText("");
        txtKuantitas.setText("");
        jComboBox1.setSelectedItem(0);
        jDateChooser1.setDateFormatString("");
    }
    
    public boolean insertData() throws SQLException{
        try {
            stmt = conn.createStatement();
            int insert = stmt.executeUpdate("INSERT INTO produk(id_produk,nama_produk,harga_produk,kuantitas,kategori_produk,expired)"
                    + "VALUES('0 ','"+nama+"','"+harga+"','"+kuantitas+"','"+kategori+"','"+expired+"')");
            if(insert == 1){
                JOptionPane.showMessageDialog(rootPane, "Tambah Data Berhasil");
            }
        } catch (SQLException e) {
            stmt.close();
            JOptionPane.showMessageDialog(null, "error2 : " + e.getMessage());
        }
        stmt.close();
        return false;
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
        logo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Data_Barang = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtKuantitas = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnSimpan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 0, 204));

        jLabel1.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Amplang Mahakam");

        logo.setText("jLabel2");

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("LOGOUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addContainerGap())
            .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Cari");
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 204)));
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 230, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/icons8_search_48px.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 40, 40));

        Data_Barang.setBackground(new java.awt.Color(255, 255, 255));
        Data_Barang.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)));
        Data_Barang.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Data Barang");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("Nama");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 255));
        jLabel5.setText("Harga");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("Kategori");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 255));
        jLabel7.setText("Kuantitas");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 255));
        jLabel8.setText("Expired");

        txtNama.setForeground(new java.awt.Color(0, 0, 204));
        txtNama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 204)));
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        txtHarga.setForeground(new java.awt.Color(0, 0, 204));
        txtHarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 204)));

        txtKuantitas.setForeground(new java.awt.Color(0, 0, 204));
        txtKuantitas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 204)));

        jComboBox1.setForeground(new java.awt.Color(51, 0, 204));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Amplang", "Gabin", "Snack", "Minuman" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 204)));

        btnSimpan.setBackground(new java.awt.Color(51, 0, 204));
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("SIMPAN");

        btnTambah.setBackground(new java.awt.Color(102, 255, 102));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(255, 51, 51));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("HAPUS");
        btnHapus.setEnabled(false);

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jCheckBox1.setText("Hapus Data");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jDateChooser1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 204)));

        javax.swing.GroupLayout Data_BarangLayout = new javax.swing.GroupLayout(Data_Barang);
        Data_Barang.setLayout(Data_BarangLayout);
        Data_BarangLayout.setHorizontalGroup(
            Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Data_BarangLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(Data_BarangLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(txtKuantitas, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(txtNama)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(122, 122, 122)
                        .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jCheckBox1))))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        Data_BarangLayout.setVerticalGroup(
            Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Data_BarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Data_BarangLayout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTambah)
                        .addGap(32, 32, 32)
                        .addComponent(btnHapus)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(Data_BarangLayout.createSequentialGroup()
                        .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(Data_BarangLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addGroup(Data_BarangLayout.createSequentialGroup()
                                .addComponent(txtHarga)
                                .addGap(3, 3, 3)))
                        .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addGroup(Data_BarangLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(Data_BarangLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtKuantitas, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addGroup(Data_BarangLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jComboBox1))
                        .addGap(10, 10, 10)
                        .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.add(Data_Barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 760, 230));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "kuku macan", "20000", "50", "Amplang", "2023-08-10"},
                {"2", "Amplang tenggiri", "30000", "60", "Amplang", "2023-09-09"},
                {"3", "Amplang Haruan", "40000", "45", "Amplang", "2023-10-10"}
            },
            new String [] {
                "id_produk", "Nama", "Harga", "Kuantitas", "Kategori", "Expired"
            }
        ));
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 740, 200));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 760, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx, y-xy);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            FormLogin loginMenu = new FormLogin();
            loginMenu.setVisible(true);
            dispose();
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()){
            btnHapus.setEnabled(true);
        }else{
            btnHapus.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        nama = txtNama.getText();
        harga = txtHarga.getText();
        kuantitas = txtKuantitas.getText();
        kategori = jComboBox1.getSelectedItem().toString();
        expired = String.valueOf(fm.format(jDateChooser1.getDate()));
        
        try {
            insertData();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        clear();
    }//GEN-LAST:event_btnTambahActionPerformed

    private Image resize(int w, int h, Image img){
        BufferedImage bufimg = new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB );
        Graphics2D dimensi = bufimg.createGraphics();
        dimensi.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        dimensi.drawImage(img, 0, 0, w, h, null);
        dimensi.dispose();
        return bufimg;
    }
    
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Home().setVisible(true);
                    new JTable().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Data_Barang;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKuantitas;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
