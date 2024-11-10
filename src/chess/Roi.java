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
public class Roi extends Piece {
    
    public Roi(int posx, int posy, boolean estBlanc, String nom, LinkedList<Piece> piece) {
        super(posx, posy, estBlanc, nom, piece);
    }
    @Override
     public void bouger(Piece piece, int startX, int startY,int posx,int posy)
     {
         super.bouger(this,startX,startY,posx, posy);
         //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          if (Debordement(posx,posy) && MouvPion( this,  startX,  startY,  posx, posy))
          {
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.nom+" bouge");
              System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          }
     
     }
      private boolean MouvPion(Roi roi, int startX, int startY, int endX, int endY) {
        int direction = roi.estBlanc == true ? 1 : -1;

        // Mouvement d'une case en avant
        if (endX == startX + direction && endY == startY) {
            return true;
        }

        // Mouvement de deux cases en avant depuis la position initiale
        if (startX == (roi.estBlanc == true ? 1 : 6) && endX == startX + 2 * direction && endY == startY) {
            return true;
        }

        // Capture en diagonale
        if (endX == startX + direction && Math.abs(endY - startY) == 1) {
            return true;
        }

        return false;
    }   
}
