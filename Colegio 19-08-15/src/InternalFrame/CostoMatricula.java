/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalFrame;

import Image.Padding;
import Metodos.DAO;
import POJO.Asignatura;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import org.hibernate.SessionFactory;

/**
 *
 * @author Blutharsch
 */
public final class CostoMatricula extends javax.swing.JInternalFrame {
DefaultTableModel dtm= new DefaultTableModel(){
 @Override
 public boolean isCellEditable(int row, int column) { return false;}
};
Padding pad;
SessionFactory sf;
@Override
protected void paintComponent(Graphics g){
g.setColor(new Color(255,255,255,64));
g.fillRect(0, 0, getWidth(), getHeight());
}   
    /**
     * Creates new form CostoMatricula
     * @param sf
     */
    public CostoMatricula(SessionFactory sf) {
        initComponents();
        this.sf=sf;
        dtm.setColumnIdentifiers(new Object[]{"ID","Costo", "Nivel"});
        jTable1.setModel(dtm);
        TableRowSorter r= new TableRowSorter(dtm);
        jTable1.setRowSorter(r);    
        RenderTable();
        this.setTitle("Costo de matrículas");
    }
    
     public void RenderTable(){
    TableColumn c= jTable1.getColumn("ID");
        c.setMaxWidth(120);
        c.setMinWidth(70);
        CargarTablaCostos();

    }
    
    public CostoMatricula(){initComponents();}

     public void CargarTablaCostos() {
    LimpiarTabla();
    DAO d= new DAO(sf);
    List<POJO.CostoMatricula> pcm= DAO.CargarCostoMatricula();
    if(pcm.isEmpty()){ JOptionPane.showMessageDialog(this, "Lista vacia", "", 
                     JOptionPane.INFORMATION_MESSAGE);}
    else{
    for(POJO.CostoMatricula cm: pcm){
    dtm.addRow(new Object[]{cm.getIdCostoMatricula(), cm.getCosto(), cm.getNivel()});
    }//for
    }//if
    }//
   
   public void LimpiarTabla(){
    for(int i=dtm.getRowCount()-1;i>=0;i--){
     dtm.removeRow(i);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBackground(java.awt.SystemColor.activeCaption);

        jPanel2.setBackground(java.awt.SystemColor.activeCaption);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Actualizar costos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 12))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel1.setText("Nuevo Costo");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel2.setText("Nivel");

        jComboBox1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Prescolar", "Primaria", "Secundaria" }));

        jButton1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Repositorio/1436251807_Synchronize.png"))); // NOI18N
        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jComboBox1, 0, 170, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
     int Id;
     DAO d= new DAO(sf);
     if(jTextField1.getText().isEmpty()){ JOptionPane.showMessageDialog(this, "El costo de la matrícula para "
       +jComboBox1.getSelectedItem().toString()+" no puede ser nulo","Imposible actualizar", JOptionPane.ERROR_MESSAGE);}else{
     if(jComboBox1.getSelectedIndex()==0){Id=1; 
      if(JOptionPane.showConfirmDialog(this, "Actualizar el nuevo precio de matricula para "+jComboBox1.getSelectedItem().toString()+" a "+jTextField1.getText()+"", 
              "Confirmar", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
       DAO.ActualizarCostoMatricula(Id, jTextField1, this);}else{ return;}
     }//
     if(jComboBox1.getSelectedIndex()==1){Id=2; 
     if(JOptionPane.showConfirmDialog(this, "Actualizar el nuevo precio de matricula para "+jComboBox1.getSelectedItem().toString()+" a "+jTextField1.getText()+"", 
             "Confirmar", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        DAO.ActualizarCostoMatricula(Id, jTextField1, this);}else{ return;}
   }//
     if(jComboBox1.getSelectedIndex()==2){Id=3; 
     if(JOptionPane.showConfirmDialog(this, "Actualizar el nuevo precio de matricula para "+jComboBox1.getSelectedItem().toString()+" a "+jTextField1.getText()+"", 
             "Confirmar", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        DAO.ActualizarCostoMatricula(Id, jTextField1, this);}else{ return;}
     }//
     this.CargarTablaCostos();
    }//
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if(jTable1.getSelectedRow()==0){ jComboBox1.setSelectedIndex(0);
            jTextField1.setText(String.valueOf(dtm.getValueAt(jTable1.getSelectedRow(), 1)));        }
        if(jTable1.getSelectedRow()==1){ jComboBox1.setSelectedIndex(1);
        jTextField1.setText(String.valueOf(dtm.getValueAt(jTable1.getSelectedRow(), 1)));}
        if(jTable1.getSelectedRow()==2){ jComboBox1.setSelectedIndex(2);
        jTextField1.setText(String.valueOf(dtm.getValueAt(jTable1.getSelectedRow(), 1)));}
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}