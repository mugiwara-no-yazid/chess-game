/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import java.util.LinkedList;

/**
 *
 * @author Yazid Soumanou
 */
public class Tour extends Piece {
    
    public Tour(int posx, int posy, boolean estBlanc, String nom, LinkedList<Piece> piece) {
        super(posx, posy, estBlanc, nom, piece);
    }
    @Override
     public void bouger(Piece piece, int startX, int startY,int posx,int posy)
     {
        
         //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          if (Debordement(posx,posy) && MouvTour( this,  startX,  startY,  posx, posy))
          {
               super.bouger(this,startX,startY,posx, posy);
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.nom+" bouge");
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          }
     
     }
      private boolean MouvTour(Tour tour, int startX, int startY, int endX, int endY) {
        int direction = tour.estBlanc == true ? -6 : 6;
        Piece destinationPion = Echiquier.getPiece(endX*64, endY*64);
       
        // Mouvement d'une case en avant
        if (endY == startY + direction && endX == startX && destinationPion == null) {
            return true;
        }
        return false;
    }   
}