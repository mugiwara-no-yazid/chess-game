/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Yazid Soumanou
 */
public class Tour extends Piece {
    
   public Tour(int posx, int posy, boolean estBlanc,boolean bouger, String nom, LinkedList<Piece> piece) {
        super(posx, posy, estBlanc,bouger, nom, piece);
    }
    @Override
     public void bouger(Piece piece, int startX, int startY,int posx,int posy)
     {
        
         
          if (Debordement(posx,posy) && MouvTour( this,  startX,  startY,  posx, posy))
          {
               super.bouger(this,startX,startY,posx, posy);
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.nom+" bouge");
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          }
     
     }
      private boolean MouvTour(Tour tour, int startX, int startY, int endX, int endY) {
        // Vérifie que le mouvement est bien soit horizontal soit vertical
        if (startX != endX && startY != endY) {
            return false;
        }

        int xDirection = startX == endX ? 0 : (endX > startX ? 1*Math.abs(endX - startX) : -1*Math.abs(endX - startX));
        int yDirection = startY == endY ? 0 : (endY > startY ? 1*Math.abs(endY - startY) : -1*Math.abs(endY - startY));

        int x = startX + (xDirection != 0 ? xDirection/Math.abs(endX - startX): xDirection );
        int y = startY + (yDirection != 0 ? yDirection/Math.abs(endY - startY): yDirection );
         
        // Vérifie que toutes les cases sur le chemin sont vides
        while (x != endX || y != endY) {
            if (Echiquier.getPiece(x*64, y*64) != null) {
                return false;
            }
             x += startX == endX ? 0 : (endX > startX ? 1 : -1);
             y += startY == endY ? 0 : (endY > startY ? 1 : -1);
        }

        // Vérifie la case de destination
        Piece destinationPion = Echiquier.getPiece(endX*64, endY*64);
        if (destinationPion == null || destinationPion.estBlanc != tour.estBlanc) {
            return true;
        }

        return false;
    }
 public JPanel cheminTour(Tour tour, int startX, int startY) {
    JPanel pn = new JPanel();
    int startx=startX/64;
    int starty=startY/64;
    //System.out.println(this.posx);
    int direction = tour.estBlanc == true ? -1 : 1;
         pn = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.DARK_GRAY);
                g2d.setStroke(new BasicStroke(2));
               int i=0;
               if(tour.estBlanc==false)
                        {
                            int y=starty;
                            i=1;
                            while(i<=y && (Echiquier.getPiece((startx)*64, ((starty-i) )*64)==null || Echiquier.getPiece((startx)*64, ((starty-i))*64).estBlanc != tour.estBlanc))
                            { 
                                g2d.drawRect((startx*64), ((starty-i)*64+direction*64), 64, 64);     
                                 if ((Echiquier.getPiece((startx)*64, ((starty-i))*64)!=null && Echiquier.getPiece((startx)*64, ((starty-i))*64).estBlanc != tour.estBlanc))
                                 {
                                      g2d.setColor(Color.yellow);
                                      g2d.drawRect((startx*64), ((starty-i)*64), 64, 64); 
                                      break;
                                 }

                                 i++;
                            }
                         }
               if(tour.posy>=0)
               {
                   
                   g2d.drawRect((startx*64), (starty*64), 64, 64);
                    while(i<7 && (Echiquier.getPiece((startx)*64, ((starty-i) + direction)*64)==null || Echiquier.getPiece((startx)*64, ((starty-i) + direction)*64).estBlanc != tour.estBlanc))
                    { 
                        g2d.drawRect((startx*64), ((starty-i)*64+direction*64), 64, 64);     
                         if ((Echiquier.getPiece((startx)*64, ((starty-i) + direction)*64)!=null && Echiquier.getPiece((startx)*64, ((starty-i) + direction)*64).estBlanc != tour.estBlanc))
                         {
                              g2d.setColor(Color.yellow);
                              g2d.drawRect((startx*64), ((starty-i)*64+direction*64), 64, 64); 
                              break;
                         }
                         
                         i++;
                    }
               }
                    
                    if(tour.posy<=7)
                    {
                        g2d.setColor(Color.DARK_GRAY);
                        int j = 7-tour.posy;
                        int e =1;  
                          while(e<=j && (Echiquier.getPiece((startx)*64, ((starty+e))*64)==null || Echiquier.getPiece((startx)*64, ((starty+e))*64).estBlanc!=tour.estBlanc) )
                    { 
                          g2d.drawRect((startx*64), ((starty+e)*64), 64, 64);
                          if(Echiquier.getPiece((startx)*64, ((starty+e))*64)!=null && Echiquier.getPiece((startx)*64, ((starty+e))*64).estBlanc!=tour.estBlanc)
                          {
                              g2d.setColor(Color.yellow);
                              g2d.drawRect((startx*64), ((starty+e)*64), 64, 64);
                              break;
                          }
                             e++;
                    }
                    }
                    //ligne gauche
                    if(tour.posx<=7)
                    {
                        g2d.setColor(Color.DARK_GRAY);
                        int j = 7-tour.posx;
                        int e =1;
                          while(e<=j && (Echiquier.getPiece((startx+e)*64, (starty)*64)==null || Echiquier.getPiece((startx+e)*64, (starty)*64).estBlanc!=tour.estBlanc))
                        { 
                          g2d.drawRect(((startx+e)*64), (starty*64), 64, 64);  
                             if ((Echiquier.getPiece((startx+e)*64, ((starty))*64)!=null && Echiquier.getPiece((startx+e)*64, (starty)*64).estBlanc != tour.estBlanc))
                         {
                              g2d.setColor(Color.yellow);
                              g2d.drawRect((startx+e)*64, ((starty)*64), 64, 64); 
                              break;
                         }
                             e++;
                        }
                        
                    }
                    //ligne droite
                    if(tour.posx>=0)
                    {
                        g2d.setColor(Color.DARK_GRAY);
                        int j = tour.posx;
                        int e =1;
                         while(e<=j && (Echiquier.getPiece((startx-e)*64, (starty)*64)==null || Echiquier.getPiece((startx-e)*64, (starty)*64).estBlanc!=tour.estBlanc))
                        { 
                          g2d.drawRect(((startx-e)*64), (starty*64), 64, 64);  
                             if ((Echiquier.getPiece((startx-e)*64, ((starty))*64)!=null && Echiquier.getPiece((startx-e)*64, (starty)*64).estBlanc != tour.estBlanc))
                         {
                              g2d.setColor(Color.yellow);
                              g2d.drawRect((startx-e)*64, ((starty)*64), 64, 64); 
                              break;
                         }
                             e++;
                        }
                    }
                    
                }
               
                    
         };
        pn.setBounds(0, 0, 512, 512); // Ajustez cette taille en fonction de votre échiquier
        pn.setOpaque(false);
    return pn;


}
  
}