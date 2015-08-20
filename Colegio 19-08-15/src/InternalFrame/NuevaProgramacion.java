/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalFrame;

import Image.Padding;
import Metodos.DAO;
import POJO.Seccion;
import java.awt.HeadlessException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Blutharsch
 */
public final class NuevaProgramacion extends javax.swing.JInternalFrame {
DefaultTableModel dtm= new DefaultTableModel(){
 @Override
 public boolean isCellEditable(int row, int column) { return false;}
};

ButtonGroup bg;
SessionFactory sf;
Session s;
int Id_seccion;  
int Id_anio;
/**
     * Creates new form Programación
     * @param sf
     */
    public NuevaProgramacion(SessionFactory sf) {
        initComponents();
        this.sf=sf;
        this.setTitle("Nueva programación");
        dtm.setColumnIdentifiers(new Object[]{"ID", "Fecha de inicio","Seccion","Año Académico", "No. Semestre"});
        bg= new ButtonGroup();
        bg.add(jRadioButton1);
        bg.add(jRadioButton2);
        jTable1.setModel(dtm);
        jRadioButton1.setSelected(true);
        CargarCBX();
        CargarProgramaciones();
    }
    
    
    public void CargarProgramaciones(){
    LimpiarTabla();
    DAO d= new DAO(sf);
    ArrayList<Object[]> lista= DAO.CargarProgramaciones();
    if(lista.isEmpty()){ JOptionPane.showMessageDialog(this,"Lista vacia");}else{
    for (Object[] o: lista) {
     Object[] row= o;
     dtm.addRow(row);
    }
    }
    }//
      
    public void LimpiarTabla(){
    for(int i=dtm.getRowCount()-1;i>=0;i--){
     dtm.removeRow(i);
    }
    }

      public void cbxSecciones() {
        DAO d = new DAO(sf);
        List<Seccion> lista = DAO.CargarSecciones();
        for (Seccion seccion : lista) {
            s = sf.openSession();
            cbxSeccion.addItem(seccion.getNoSeccion());
            s.close();
        }
    }//
    
      public void cbxAniosAcademicos() {
        DAO d = new DAO(sf);
        List<POJO.AnioAcademico> lista = DAO.CargarAnioAcademico();
        for (POJO.AnioAcademico a : lista) {
            s = sf.openSession();
            cbxAnios.addItem(a.getAnio());
            s.close();
        }
    }//
      
      public void CargarCBX(){
      this.cbxAniosAcademicos();
      this.cbxSecciones();
      }//
      
    public void ObtenerIdCbx() {
        int n = cbxSeccion.getSelectedIndex()+1;
        DAO d = new DAO(sf);
        Id_seccion = DAO.ObtenerIdSeccion(n).getIdSeccion();
        System.out.println("Seccion: " + Id_seccion);
        
        int n1= cbxAnios.getSelectedIndex()+1;
        Id_anio = DAO.ObtenerIdAnio(n1).getIdAnioAcademico();
        System.out.println("Año: " + Id_anio);
        int semestre=1;
        if(jRadioButton1.isSelected()){ semestre=1;}
        if(jRadioButton2.isSelected()){ semestre=2; }
        
        if(JOptionPane.showConfirmDialog(this, "Guardar programación para: "+cbxAnios.getSelectedItem().toString()
           +" en la sección "+cbxSeccion.getSelectedItem().toString()
           +"\nSemestre: "+String.valueOf(semestre)+", Inicio: "+TransformDate(),"Confirmar", 
           JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
           GuardarProgramacion(Id_seccion, Id_anio, semestre, TransformDate());
           CargarProgramaciones();
        }else{return;}
       
    }//
    public NuevaProgramacion(){initComponents();}
    
   public void GuardarProgramacion(int IdSeccion, int IdAnio, int Semestre, Date Fecha){
   DAO d= new DAO(sf); 
   DAO.GuardarProgramacion(Id_seccion, Id_anio, Semestre, Fecha);
   }
   
   public String DateChooser(){
   String fechaformato;
   String day= dateChooser.getText().substring(0, 2);
   String month= dateChooser.getText().substring(3,5);
   String year;
   
   if(day.contains("/")){ day=dateChooser.getText().substring(0, 1);}
   if(month.contains("/")){ month= dateChooser.getText().substring(2,4); }
   if(dateChooser.getText().length()==8){ year= dateChooser.getText().substring(6,8); }
   else{ year= dateChooser.getText().substring(5,7);;}
   
   System.out.println(day);
   System.out.println(month);
   System.out.println("20"+year);
   
   fechaformato=day+"/"+month+"/20"+year;
   System.out.println(fechaformato);
  
   return fechaformato;
   }
   public Date TransformDate(){
  SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    Date fechaDate = null;
    try {
        fechaDate = formato.parse(DateChooser());     
    } 
    catch (ParseException ex) 
    {
        System.out.println(ex);
    }
    return fechaDate;
   
   }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxSeccion = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbxAnios = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        dateChooser = new datechooser.beans.DateChooserCombo();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Matriculados = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(java.awt.SystemColor.activeCaption);

        jPanel2.setBackground(java.awt.SystemColor.activeCaption);
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel1.setText("Secciones");

        cbxSeccion.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel2.setText("Año académico");

        cbxAnios.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel3.setText("Semestre");

        jRadioButton1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jRadioButton1.setText("I");

        jRadioButton2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jRadioButton2.setText("II");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel4.setText("Fecha de Inicio");

        dateChooser.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dateChooser.setCalendarPreferredSize(new java.awt.Dimension(360, 250));
    dateChooser.setWeekStyle(datechooser.view.WeekDaysStyle.FULL);
    try {
        dateChooser.setDefaultPeriods(new datechooser.model.multiple.PeriodSet(new datechooser.model.multiple.Period(new java.util.GregorianCalendar(2015, 6, 28),
            new java.util.GregorianCalendar(2015, 6, 28))));
} catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
    e1.printStackTrace();
    }
    dateChooser.setFieldFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
    dateChooser.setLocale(new java.util.Locale("es", "", ""));

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel3)
                    .addGap(48, 48, 48)
                    .addComponent(jRadioButton1)
                    .addGap(18, 18, 18)
                    .addComponent(jRadioButton2))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addGap(27, 27, 27)
                    .addComponent(cbxSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2)
                .addComponent(jLabel4))
            .addGap(28, 28, 28)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(cbxAnios, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(71, 71, 71))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cbxSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2)
                .addComponent(cbxAnios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1))
            .addGap(25, 25, 25)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel4))
                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(12, Short.MAX_VALUE))
    );

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
    jScrollPane1.setViewportView(jTable1);

    jButton1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Repositorio/save (1).png"))); // NOI18N
    jButton1.setText("Guardar");
    jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    jButton2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
    jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Repositorio/cross.png"))); // NOI18N
    jButton2.setText("Cancelar");
    jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    jButton3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
    jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Repositorio/1436251807_Synchronize.png"))); // NOI18N
    jButton3.setText("Actualizar");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });

    jButton4.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
    jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Repositorio/add_25.png"))); // NOI18N
    jButton4.setText("Ver/Agregar detalles");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
        }
    });

    Matriculados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Repositorio/user.png"))); // NOI18N
    Matriculados.setText("Matriculados");
    Matriculados.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            MatriculadosActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Matriculados, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(77, 77, 77)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton4)
                            .addGap(18, 18, 18)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10, 10, 10)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Matriculados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(46, Short.MAX_VALUE))
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
          ObtenerIdCbx();   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DateChooser();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
        int selectedRow = jTable1.getSelectedRow();
        int Id = Integer.parseInt(String.valueOf(dtm.getValueAt(selectedRow, 0)));
        int Semestre = Integer.parseInt(String.valueOf(dtm.getValueAt(selectedRow, 4)));
        String Seccion = String.valueOf(dtm.getValueAt(selectedRow, 2));
        String Anio= String.valueOf(dtm.getValueAt(selectedRow, 3));
        
        ActualizarProgramacion a = new ActualizarProgramacion(sf, Id, Anio, Seccion, Semestre);
        Padding pad= new Padding();
        if(pad.activo(a, getDesktopPane())){ 
        getDesktopPane().add(a);
        a.setVisible(true);
       
       }else{return;}
      }catch(Exception ee){JOptionPane.showMessageDialog(this,"Primero seleccione a alguien de la tabla");}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
        int selectedRow = jTable1.getSelectedRow();
        /*********************************************************************************************/
        int Id = Integer.parseInt(String.valueOf(dtm.getValueAt(selectedRow, 0)));
        String Fecha = String.valueOf(dtm.getValueAt(selectedRow, 1));
        String Seccion = String.valueOf(dtm.getValueAt(selectedRow, 2));
        String Anio= String.valueOf(dtm.getValueAt(selectedRow, 3));
        
        NuevoDetalleProgramacion n = new NuevoDetalleProgramacion(sf, Id, Seccion, Anio, Fecha);
        Padding pad= new Padding();
        if(pad.activo(n, getDesktopPane())){ 
        getDesktopPane().add(n);
        n.setVisible(true);
        this.dispose();
      }else{JOptionPane.showMessageDialog(this, "Ya hay una ventana de diálogo abierta");}
      }catch(Exception ee){
       JOptionPane.showMessageDialog(this,"Primero seleccione a alguien de la tabla");
    }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void MatriculadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MatriculadosActionPerformed
        // TODO add your handling code here:
       try{
       int selectedRow = jTable1.getSelectedRow();
       int Id = Integer.parseInt(String.valueOf(dtm.getValueAt(selectedRow, 0)));
       RegistroMatriculados rm= new RegistroMatriculados(Id, sf);
       Padding pad= new Padding();
        if(pad.activo(rm, getDesktopPane())){ 
        getDesktopPane().add(rm);
        rm.setVisible(true);
        this.dispose();
      }else{JOptionPane.showMessageDialog(this, "Ya hay una ventana de diálogo abierta");}
      }catch(Exception ee){}
    }//GEN-LAST:event_MatriculadosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Matriculados;
    private javax.swing.JComboBox cbxAnios;
    private javax.swing.JComboBox cbxSeccion;
    private datechooser.beans.DateChooserCombo dateChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
