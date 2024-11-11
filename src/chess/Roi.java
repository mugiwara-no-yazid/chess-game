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
         
         //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          if (Debordement(posx,posy) && MouvRoi( this,  startX,  startY,  posx, posy))
          {
              super.bouger(this,startX,startY,posx, posy);
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.nom+" bouge");
              System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          }
     
     }
    private boolean MouvRoi(Roi roi, int startX, int startY, int endX, int endY) {
    // Vérifier que le mouvement est d'une case dans n'importe quelle direction
    int deltaX = Math.abs(endX - startX);
    int deltaY = Math.abs(endY - startY);
    
    if (deltaX <= 1 && deltaY <= 1) {
        Piece destinationPion = Echiquier.getPiece(endX, endY);
        // Vérifier si la case de destination est vide ou contient une pièce d'une couleur opposée
        if (destinationPion == null || destinationPion.estBlanc != roi.estBlanc) {
            return true;
        }
    }
    
    return false;
}
   
}
