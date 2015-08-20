/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Blutharsch
 */
public class Usuarios {
     private static SessionFactory sf;
    private static Session s;

    public Usuarios(SessionFactory sf) {
        Usuarios.sf = sf;
    }
    
    public static boolean CrearUsuario(String nombreUsuario, String password) {
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();
            String query = "Create User '" + nombreUsuario + "'@'localhost' identified by '" + password + "';";
            System.out.println("" + query);
            Query q = s.createSQLQuery(query);
            q.executeUpdate();
           
            t.commit();

        } catch (HibernateException e) {
            System.out.println("ERROR: " + e.getMessage() + " CAUSA: " + e.getCause());
            return false;
        } finally {
            s.close();
        }

        return true;
    }
    
       public boolean ExisteUsuario(String nombreUsuario) {
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Profesor where Usuario =:parametro");
            q.setString("parametro", nombreUsuario);
            if (q.list().size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage() + " CAUSA: " + e.getCause());
            return true;
        } finally {
            s.close();
        }
    }//
    
    public static boolean AsignarPermisosDirector(String usuario){
    try{
            s = sf.openSession();
            Transaction t = s.beginTransaction(); 
            
            t.commit();

        } catch (Exception e) {
            
            return false;
        } finally {
            s.close();
        }
    
    return true;
    }
    
    /*Permisos DBA*/
     public static boolean AsignarPermisosAdministrador(String usuario) {

        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();
            String query = "GRANT ALL ON bdcolegio.* TO '" + usuario + "'@'localhost';";
            Query q = s.createSQLQuery(query);
            q.executeUpdate();
            t.commit();
        } catch (HibernateException e) {
            return false;
        } finally {
            s.close();
        }
        return true;
    }
}
