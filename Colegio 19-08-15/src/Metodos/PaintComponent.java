/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Metodos;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JInternalFrame;

/**
 *
 * @author Blutharsch
 */
public class PaintComponent {
    
    
 public PaintComponent(){}
 
 public void paintComponent(Graphics g, JInternalFrame j){
 g.setColor(new Color(255,255,255,64));
 g.fillRect(0, 0, j.getWidth(), j.getHeight());
 }
    
}
