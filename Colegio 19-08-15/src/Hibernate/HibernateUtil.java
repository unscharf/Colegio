package Hibernate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Blutharsch
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = null;
      
    public static SessionFactory conexion(String login, String Contraseña, String Puerto, String IP) {
        try {
            try {
                Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:" + Puerto + "/bdcolegio", login, Contraseña);
                conexion.close();
            } catch (SQLException e) {
                return null;
            }
            sessionFactory = (SessionFactory) new Configuration()
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:" + Puerto + "/bdcolegio")
                    .setProperty("hibernate.connection.username", login)
                    .setProperty("hibernate.connection.password", Contraseña)
                    
                    /*ORM*/
                    .addResource("POJO/AnioAcademico.hbm.xml")
                    .addResource("POJO/Asignatura.hbm.xml")
                    .addResource("POJO/CostoMatricula.hbm.xml")
                    .addResource("POJO/CostoMensualidad.hbm.xml")
                    .addResource("POJO/DetalleProgramacion.hbm.xml")
                    .addResource("POJO/DetallesRegistro.hbm.xml")
                    .addResource("POJO/Director.hbm.xml")
                    .addResource("POJO/Estudiante.hbm.xml")
                    .addResource("POJO/Horario.hbm.xml")
                    .addResource("POJO/Matricula.hbm.xml")
                    .addResource("POJO/PagoMensualidad.hbm.xml")
                    .addResource("POJO/Profesor.hbm.xml")
                    .addResource("POJO/ProfesorAsignatura.hbm.xml")
                    .addResource("POJO/Programacion.hbm.xml")
                    .addResource("POJO/Registro.hbm.xml")
                    .addResource("POJO/Seccion.hbm.xml")
                    .addResource("POJO/Titulos.hbm.xml")
                    .buildSessionFactory();

        } catch (HibernateException ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }
    
}
