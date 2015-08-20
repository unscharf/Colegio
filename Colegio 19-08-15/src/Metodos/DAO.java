/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Metodos;

import InternalFrame.AgregarAsignatura;
import Login.Usuarios;
import POJO.AnioAcademico;
import POJO.Asignatura;
import POJO.CostoMatricula;
import POJO.CostoMensualidad;
import POJO.Profesor;
import POJO.ProfesorAsignatura;
import POJO.Programacion;
import POJO.Seccion;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Blutharsch
 */
public class DAO {
    private static SessionFactory sf;
    private static Session s;
    private static boolean valido;

  public DAO(SessionFactory sf){ this.sf=sf;}
  
   public static List<Profesor> Cargar_profesores() {
        s = sf.openSession();
        /*Criteria List*/
        Criteria c=s.createCriteria(Profesor.class);
        List<Profesor> prof=c.list();
        s.close();
        return prof;
    }
   
   /*Session Querys*/
   public static ArrayList<Object[]> CargarProfesores_asignatura(int ID){
   s=sf.openSession();
   s.beginTransaction();
   String q="Select p.Id_profesor, Nombre, Apellido, No_cedula, Sexo, Telefono, Direccion "
           + "from profesor_asignatura as pa inner join profesor as p on p.Id_profesor=pa.Fk_profesor "
           +"inner join asignatura as a on a.Id_asignatura=pa.Fk_asignatura where a.Id_asignatura="+ID+";";
   
   SQLQuery query= s.createSQLQuery(q);
   ArrayList<Object[]> res = new ArrayList<>(query.list());
   
   System.out.print(query.list().size());
   s.flush();
   s.getTransaction().commit();
   s.close();
   return res;
   }
  
   public static ArrayList<Object[]> CargarAsignaturas_profesor(int Id){
   s=sf.openSession();
   s.beginTransaction();
   String q= "Select a.Id_asignatura, a.Nombre_asignatura From asignatura as a "
             +"inner join profesor_asignatura as pa on a.Id_asignatura= pa.Fk_asignatura "
             +"inner join Profesor as p on p.Id_profesor= pa.Fk_profesor where p.Id_profesor="+Id+";";
   
   SQLQuery query= s.createSQLQuery(q);
   ArrayList<Object[]> res= new ArrayList<>(query.list());
   System.out.print(query.list().size());
   s.flush();
   s.getTransaction().commit();
   s.close();
   
   return res;
   }//
   
   public static ArrayList<Object[]> CargarProgramaciones(){
   s= sf.openSession();
   s.beginTransaction();
   String q="Select p.Id_programacion, p.Fecha_ini , s.No_seccion, aa.Anio, p.Semestre "
             +"From Programacion as p " 
             +"INNER JOIN Seccion as s on s.Id_seccion= p.Fk_seccion " 
             +"INNER JOIN anio_academico as aa on aa.Id_anio_academico= p.Fk_anio_academico;";
   
   SQLQuery query = s.createSQLQuery(q);
   ArrayList<Object[]> res= new ArrayList<>(query.list());
   System.out.print(query.list().size());
   s.flush();
   s.getTransaction().commit();
   s.close();
   
   return res;
   }//

   /*Detalles de programación*/
   public static ArrayList<Object[]> CargarProfesoresAsignaturas(){
   s=sf.openSession();
   s.beginTransaction();
   String q="Select pa.Id_profesor_asignatura, a.Nombre_asignatura, pro.Nombre, pro.Apellido, pro.No_cedula, pro.Sexo, "
           +"pro.Direccion, pro.Telefono From profesor as pro "
           +"INNER JOIN profesor_asignatura as pa on pro.Id_profesor=pa.Fk_profesor " 
           +"INNER JOIN asignatura as a on a.Id_asignatura= pa.Fk_asignatura ORDER BY Nombre_asignatura;";
          
    
   SQLQuery query = s.createSQLQuery(q);
   ArrayList<Object[]> res= new ArrayList<>(query.list());
   System.out.print(query.list().size());
   
   s.flush();
   s.getTransaction().commit();
   s.close();
   
   return res;
   }//
   
   public static ArrayList<Object[]> CargarDetallesProgramacion(int Id){
   s=sf.openSession();
   s.beginTransaction();
   
   String q="Select dp.Id_detalle_programacion, a.Nombre_asignatura, pro.Nombre, pro.Apellido, pro.No_cedula, pro.Sexo, "
             +"pro.Direccion, pro.Telefono From profesor as pro "
             +"INNER JOIN profesor_asignatura as pa on pro.Id_profesor=pa.Fk_profesor "
             +"INNER JOIN asignatura as a on a.Id_asignatura= pa.Fk_asignatura "
             +"INNER JOIN detalle_programacion as dp on pa.Id_profesor_asignatura=dp.Fk_profesor_asignatura "
             +"WHERE dp.Fk_programacion="+Id 
             +" ORDER BY Nombre_asignatura;";
     
   SQLQuery query = s.createSQLQuery(q);
   ArrayList<Object[]> res= new ArrayList<>(query.list());
   System.out.print(query.list().size());
   
   s.flush();
   s.getTransaction().commit();
   s.close();
   
   return res;
   }
   
      public static ArrayList<Object[]> CargarMatriculas_programacion(int Id){
   s=sf.openSession();
   s.beginTransaction();
   
   String q="Select m.Id_matricula, m.Fecha_matricula, e.No_carnet, e.Nombre, e.Apellido, e.Feha_nac, e.Sexo, e.Direccion, e.Telefono "
            +"From matricula as m inner join estudiante as e on m.Fk_estudiante=e.Id_estudiante "
            +"INNER JOIN programacion as p on m.Fk_programacion=p.Id_programacion "
            +"INNER JOIN anio_academico as aa on p.Fk_anio_academico=aa.Id_anio_academico "
            +"WHERE m.Fk_programacion="+Id;
     
   SQLQuery query = s.createSQLQuery(q);
   ArrayList<Object[]> res= new ArrayList<>(query.list());
   System.out.print(query.list().size());
   
   s.flush();
   s.getTransaction().commit();
   s.close();
   
   return res;
   }
 
   public static ArrayList<Object[]> CargarTitulos(int Id){
   s=sf.openSession();
   s.beginTransaction();
   
   String q="Select t.Id_titulo, t.cad_titulo from titulos t inner join profesor p "
            +"on t.Fk_profesor=p.Id_profesor where p.Id_profesor="+Id+"";
     
   SQLQuery query = s.createSQLQuery(q);
   ArrayList<Object[]> res= new ArrayList<>(query.list());
   System.out.print(query.list().size());
   s.flush();
   s.getTransaction().commit();
   s.close();
   
   return res;
   }
           
   public static ArrayList<Object[]> CargarHorarioDetalles(int Id_detalle){
   s=sf.openSession();
   s.beginTransaction();
   String q="Select h.Id_horario, Dia, Hora_entrada, Hora_salida "
            +"from Horario as h inner join Detalle_programacion as dp "
            +"on h.Fk_detalle_programacion= dp.Id_detalle_programacion "
            +"where dp.Id_detalle_programacion="+Id_detalle+";";
     
      
   SQLQuery query = s.createSQLQuery(q);
   ArrayList<Object[]> res= new ArrayList<>(query.list());
   System.out.print(query.list().size());
   
   s.flush();
   s.getTransaction().commit();
   s.close();
   return res;
   }
   
   /*Filtros criteria*/
   public static List<Asignatura> CargarAsignaturas(){
       s = sf.openSession();
        /*Criteria List*/
       Criteria criteria = s.createCriteria(POJO.Asignatura.class);
       List<Asignatura> asignatura=criteria.list();
       s.close();
       return asignatura;
    }
   
     public static List<POJO.Estudiante> CargarEstudiantes(){
       s = sf.openSession();
        /*Criteria List*/
       Criteria criteria = s.createCriteria(POJO.Estudiante.class);
       List<POJO.Estudiante> e=criteria.list();
       s.close();
       return e;
    }
   
   
     public static List<CostoMatricula> CargarCostoMatricula(){
       s = sf.openSession();
        /*Criteria List*/
       Criteria criteria = s.createCriteria(POJO.CostoMatricula.class);
       List<CostoMatricula> costomatricula=criteria.list();
       s.close();
       return costomatricula;
    }
     
     public static List<POJO.Director> CargarDirectores(){
       s = sf.openSession();
        /*Criteria List*/
       Criteria criteria = s.createCriteria(POJO.Director.class);
       List<POJO.Director> dir=criteria.list();
       s.close();
       return dir;
    }
     
     public static List<CostoMensualidad> CargarCostoMensualidad(){
     s= sf.openSession();
     Criteria criteria= s.createCriteria(CostoMensualidad.class);
     List<CostoMensualidad> costomensualidad= criteria.list();
     s.close();
     return costomensualidad;
     }
    
     public static List<AnioAcademico> CargarAnioAcademico(){
     s=sf.openSession();
     Criteria criteria = s.createCriteria(AnioAcademico.class);
     List<AnioAcademico> anio= criteria.list();
     s.close();
     return anio;
     }
     
     public static List<Seccion> CargarSecciones(){
     s=sf.openSession();
     Criteria criteria = s.createCriteria(Seccion.class);
     List<Seccion> seccion= criteria.list();
     s.close();
     return seccion;
     }  
                        
    public static Asignatura ObtenerIdAsignatura(int Id){
        s = sf.openSession();
        Criteria criteria = s.createCriteria(POJO.Asignatura.class);
        List<Asignatura> lista = criteria.list();
        for (Asignatura asignatura : lista) {
            if (Id == asignatura.getIdAsignatura()) {
                s.close();
                return asignatura;
            }
        }
        s.close();
        return null;
    }

   public static Seccion ObtenerIdSeccion(int Id){
   s=sf.openSession();
   Criteria criteria= s.createCriteria(Seccion.class);
   List<Seccion> lista= criteria.list();
   for(Seccion seccion: lista){
     if (Id == seccion.getIdSeccion()) {
            s.close();
            return seccion;
            }
  }
  s.close();
  return null;
}//

     public static POJO.AnioAcademico ObtenerIdAnio(int Id){
     s=sf.openSession();
     Criteria criteria= s.createCriteria(POJO.AnioAcademico.class);
     List<POJO.AnioAcademico> anio= criteria.list();
     for(POJO.AnioAcademico a: anio){
     if (Id == a.getIdAnioAcademico()) {
            s.close();
            return a;
            }
    }
    s.close();
    return null;
}
//*******************************************************************//
    
    public static void GuardarProfesor(String Nombre, String Apellido, String cedula, String sexo, 
    String usuario, String pass, String direccion, String tel, byte[] foto, JInternalFrame AgregarProfesor) {
        valido = Usuarios.CrearUsuario(usuario, pass);
        if (!valido) {
            System.out.println("NO REGISTRADO");
        }
        JOptionPane.showMessageDialog(AgregarProfesor, "Usuario registrado");     
        try {
          s = sf.openSession();
          Transaction t = s.beginTransaction();
          //FileInputStream fis = null;
          //File file = new File(ruta);
          Profesor p= new Profesor();
          //byte[] foto = new byte[(int)file.length()];
      //try{ //fis= new FileInputStream(file);
           //fis.read(foto);
           //fis.close(); }catch(IOException e){}            
          p.setNombre(Nombre);
          p.setApellido(Apellido);
          p.setNoCedula(cedula);
          p.setSexo(sexo);
          p.setImage(foto);
          p.setDireccion(direccion);
          p.setTelefono(tel);
          p.setUsuario(usuario);
          p.setPwd(pass);
          p.setEstado(1);     
          s.save(p);
          t.commit();
          s.close();
          JOptionPane.showMessageDialog(AgregarProfesor,"Datos almacenados correctamente");    
        } catch (HibernateException ex) {
            System.out.println("Error: " + ex.getMessage() + "  CAUSA: " + ex.getCause());
        }     
    }//
    
    public static void GuardarProfesorAsignatura(int idProfesor, int idAsignatura){    
    s=sf.openSession();
   try{ 
    s.beginTransaction();
    
    POJO.ProfesorAsignatura pa= new POJO.ProfesorAsignatura();
    pa.setAsignatura((Asignatura)s.get(Asignatura.class, idAsignatura));
    pa.setProfesor((Profesor)s.get(Profesor.class, idProfesor));
    s.save(pa);
    
    JOptionPane.showMessageDialog(null,"Profesor y asignatura asociados");
    s.getTransaction().commit();
    s.close();
   
    }catch(HibernateException ee){}
    }
    
    public static void GuardarProgramacion(int Id_seccion, int Id_anio, int Semestre, Date fecha_ini){
    s=sf.openSession();
    
    try{
    s.beginTransaction();
    POJO.Programacion pro= new POJO.Programacion();
    pro.setSeccion((Seccion) s.get(Seccion.class, Id_seccion));
    pro.setAnioAcademico((AnioAcademico) s.get(AnioAcademico.class, Id_anio));
    pro.setSemestre(Semestre);
    pro.setFechaIni(fecha_ini);
    pro.setEstado(1);
    s.save(pro);
    s.getTransaction().commit();
    JOptionPane.showMessageDialog(null,"Programación almacenada con éxito");
    s.close();
    }catch(HibernateException ee){}
    }//
    
    public static void GuardarTitulo(int Id_profesor, String titulo){
    s=sf.openSession();
    try{
    s.beginTransaction();
    POJO.Titulos t= new POJO.Titulos();
    t.setProfesor((POJO.Profesor) s.get(POJO.Profesor.class, Id_profesor));
    t.setCadTitulo(titulo);
    s.save(t);
    JOptionPane.showMessageDialog(null,"Titulo agregado");
    s.getTransaction().commit();
    s.close();
    }catch(HibernateException ee){}
    }//
    
    public static void GuardarPagoMensualidad(int Id_estudiante, Date Fecha){
    s=sf.openSession();
    s.beginTransaction();
    try{
    POJO.PagoMensualidad p= new POJO.PagoMensualidad();
    p.setCostoMensualidad((CostoMensualidad) s.get(CostoMensualidad.class, 1));
    p.setEstudiante((POJO.Estudiante) s.get(POJO.Estudiante.class, Id_estudiante));
    p.setFechaPago(Fecha);
    s.save(p);
    JOptionPane.showMessageDialog(null, "Mensualidad almacenada");
    s.getTransaction().commit();
    }catch(HibernateException ee){}
    }
    
    public static void GuardarDetalle_programacion(int Id_programacion, int Id_profesor_asignatura){
    s=sf.openSession();
    s.beginTransaction();
    try{
    POJO.DetalleProgramacion d= new POJO.DetalleProgramacion();
    d.setProgramacion((Programacion)s.get(Programacion.class, Id_programacion));
    d.setProfesorAsignatura((ProfesorAsignatura)s.get(ProfesorAsignatura.class, Id_profesor_asignatura));
    s.save(d);
    s.getTransaction().commit();
    JOptionPane.showMessageDialog(null,"Agregado con éxito");
    s.close();
    }catch(HibernateException ee){}
    }//
    
    public static void GuardarMatricula_prescolar(int Id_estudiante, int Id_programacion, Date Fecha){
    s=sf.openSession();
    s.beginTransaction();
    try{
    POJO.Matricula m= new POJO.Matricula();
    m.setCostoMatricula((CostoMatricula)s.get(CostoMatricula.class, 1));
    m.setEstudiante((POJO.Estudiante)s.get(POJO.Estudiante.class,Id_estudiante));
    m.setProgramacion((POJO.Programacion)s.get(POJO.Programacion.class, Id_programacion));
    m.setFechaMatricula(Fecha);
    s.save(m);
    s.getTransaction().commit();
    JOptionPane.showMessageDialog(null,"Estudiante matriculado a la programación");
    s.close();
    }catch(HibernateException ee){}
    }
    
    public static void GuardarMatricula_primaria(int Id_estudiante, int Id_programacion, Date Fecha){
    s=sf.openSession();
    s.beginTransaction();
    try{
    POJO.Matricula m= new POJO.Matricula();
    m.setCostoMatricula((CostoMatricula)s.get(CostoMatricula.class, 2));
    m.setEstudiante((POJO.Estudiante)s.get(POJO.Estudiante.class,Id_estudiante));
    m.setProgramacion((POJO.Programacion)s.get(POJO.Programacion.class, Id_programacion));
    m.setFechaMatricula(Fecha);
    s.save(m);
    s.getTransaction().commit();
    JOptionPane.showMessageDialog(null,"Estudiante matriculado a la programación");
    s.close();
    }catch(HibernateException ee){}
    }
    
    public static void GuardarMatricula_secundaria(int Id_estudiante, int Id_programacion, Date Fecha){
    s=sf.openSession();
    s.beginTransaction();
    try{
    POJO.Matricula m= new POJO.Matricula();
    m.setCostoMatricula((CostoMatricula)s.get(CostoMatricula.class, 3));
    m.setEstudiante((POJO.Estudiante)s.get(POJO.Estudiante.class,Id_estudiante));
    m.setProgramacion((POJO.Programacion)s.get(POJO.Programacion.class, Id_programacion));
    m.setFechaMatricula(Fecha);
    s.save(m);
    s.getTransaction().commit();
    JOptionPane.showMessageDialog(null,"Estudiante matriculado a la programación");
    s.close();
    }catch(HibernateException ee){}
    }
     
     
    public static void GuardarHorario(int Id_detalle_programacion, String Dia, String Hora_entrada, String Hora_salida){
    s=sf.openSession();
    s.beginTransaction();
    try{
    POJO.Horario h= new POJO.Horario();
    h.setDetalleProgramacion((POJO.DetalleProgramacion) s.get(POJO.DetalleProgramacion.class, Id_detalle_programacion));
    h.setDia(Dia);
    h.setHoraEntrada(Hora_entrada);
    h.setHoraSalida(Hora_salida);
    s.save(h);
    JOptionPane.showMessageDialog(null,"Detalle de horario agregado");
    s.getTransaction().commit();
    s.close();
    }catch(HibernateException he){}
    }
    
    public static void EditarProgramacion(int Id_programacion, int Id_seccion, int Id_anio, int Semestre, Date Fecha_ini){
     s=sf.openSession();
    
    try{
    s.beginTransaction();
    POJO.Programacion pro= (POJO.Programacion)s.load(POJO.Programacion.class, Id_programacion);
    pro.setSeccion((Seccion) s.get(Seccion.class, Id_seccion));
    pro.setAnioAcademico((AnioAcademico) s.get(AnioAcademico.class, Id_anio));
    pro.setSemestre(Semestre);
    pro.setFechaIni(Fecha_ini);
    pro.setEstado(1);
    s.update(pro);
    
    s.getTransaction().commit();
    JOptionPane.showMessageDialog(null,"Programación actualizada con éxito");
    s.close();
    }catch(HibernateException ee){}
    }//
    
    public static void DetallesProfesor(int Id, JTextField Nombre, JTextField Apellido,JTextField Ced, JTextField tel,
                JTextArea area,JRadioButton b1, JRadioButton b2, JLabel mostrarImg, int Height, int Width) throws IOException{
       
        s= sf.openSession();
        Profesor p= (Profesor)s.get(Profesor.class, Id);
        FileInputStream is = null;
        /*Get de la base de datos*/
        byte [] foto= p.getImage();
        Nombre.setText(p.getNombre());
        Apellido.setText(p.getApellido());
        Ced.setText(p.getNoCedula()); 
        tel.setText(p.getTelefono());
        area.setText(p.getDireccion());
        BufferedImage imagen=null;
        
        try {
         if(foto != null)
         { imagen= ImageIO.read(new ByteArrayInputStream(foto));
           ImageIcon icon= new ImageIcon(imagen.getScaledInstance(Height, Width ,Image.SCALE_DEFAULT)); 
           mostrarImg.setIcon(icon);  
         }else{JOptionPane.showMessageDialog(null,"No hay una imagen valida");}
        
         String sexo=p.getSexo();
        if(sexo.equals("M")) { b1.setSelected(true);}
        if(sexo.equals("F")){b2.setSelected(true);}      
        } catch (NullPointerException e) { JOptionPane.showMessageDialog(null, ""+e);}   
        s.close();
}//
  
 public static void ActualizarProfesor(int Id, JTextField Nombre, JTextField Apellido,JTextField Ced, JTextField Tel, JTextArea area, String Sexo, byte[] foto){
  s= sf.openSession();
  try{
    s.beginTransaction();
    Profesor p= (Profesor)s.load(POJO.Profesor.class, Id);
    p.setNombre(Nombre.getText());
    p.setApellido(Apellido.getText());
    p.setNoCedula(Ced.getText());
    p.setTelefono(Tel.getText());
    p.setDireccion(area.getText());
    p.setImage(foto);
    p.setSexo(Sexo);
  
    s.update(p);
    s.getTransaction().commit();
    JOptionPane.showMessageDialog(null, "Registro actualizado");
  }catch(HibernateException ee){ JOptionPane.showInternalMessageDialog(null,""+ee.getMessage());}
  s.close();   
}
 
 public static void ActualizarCostoMatricula(int Id, JTextField Costo, JInternalFrame CostoMatricula){
     s= sf.openSession();
     try{
     s.beginTransaction();
     CostoMatricula cm= (CostoMatricula)s.load(CostoMatricula.class, Id);
     cm.setCosto(Double.parseDouble(Costo.getText()));
     s.update(cm);
     s.getTransaction().commit();
     JOptionPane.showMessageDialog(CostoMatricula, "Costo Actualizado");
     }catch(HibernateException ee){}
     s.close();
 
 }//
 
  public static void ActualizarCostoMensualidad(int Id, JTextField Costo, JInternalFrame CostoMensualidad){
     s= sf.openSession();
     try{
     s.beginTransaction();
     CostoMensualidad cm= (CostoMensualidad)s.load(CostoMensualidad.class, Id);
     cm.setCosto(Double.parseDouble(Costo.getText()));
     s.update(cm);
     s.getTransaction().commit();
     JOptionPane.showMessageDialog(CostoMensualidad, "Costo Actualizado");
     }catch(HibernateException ee){}
     s.close();
     }//
 
    public static void GuardarAsignatura(JTextField asignatura, AgregarAsignatura frame){
    s= sf.openSession();
    try{
    s.beginTransaction();
    Asignatura a= new Asignatura();
    a.setNombreAsignatura(asignatura.getText());
    s.save(a);
    JOptionPane.showMessageDialog(null, "Asignatura almacenada con éxito");
    s.getTransaction().commit();
    }catch(HibernateException ee){ JOptionPane.showInternalMessageDialog(frame, ""+ee.getMessage());}
    s.close();
    }//
    
    /*05-08-15*/
    public static void GuardarDirector(String Nombre, String Apellido, String Dir, String Tel){
    s= sf.openSession();
    try{
    s.beginTransaction();
    POJO.Director d= new POJO.Director();
    d.setNombre(Nombre);
    d.setApellido(Apellido);
    d.setDireccion(Dir);
    d.setTelefono(Tel);
    d.setEstado(1);
    s.save(d);
     JOptionPane.showMessageDialog(null, "Nuevo director agregado con éxito");
    s.getTransaction().commit();
    }catch(HibernateException ee){JOptionPane.showInternalMessageDialog(null, ""+ee.getMessage());}
    s.close();
    }
    
    public static void GuardarEstudiante(String Nombre, String Apellido, String No_carnet, Date Fecha_nac, String Dir, String sexo, String Tel){
    s=sf.openSession();
    try{
    
    byte[] binaryArray=new byte[1];
    
    s.beginTransaction();
    POJO.Estudiante e= new POJO.Estudiante();
    e.setNombre(Nombre);
    e.setApellido(Apellido);
    e.setNoCarnet(No_carnet);
    e.setFehaNac(Fecha_nac);
    e.setDireccion(Dir);
    e.setSexo(sexo);
    e.setTelefono(Tel);
    e.setImage(binaryArray);
    e.setEstado(1);
    s.save(e);
    JOptionPane.showMessageDialog(null, "Estudiante almacenado con éxito");
    s.getTransaction().commit();
    }catch(HibernateException ee){JOptionPane.showInternalMessageDialog(null, ""+ee.getMessage());}
    s.close();
    }
  
}