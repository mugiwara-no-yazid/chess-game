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
public class Fou extends Piece {
    
    public Fou(int posx, int posy, boolean estBlanc, String nom, LinkedList<Piece> piece) {
        super(posx, posy, estBlanc, nom, piece);
    }
    @Override
     public void bouger(Piece piece, int startX, int startY,int posx,int posy)
     {
       
         //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          if (Debordement(posx,posy) && MouvFou(this,  startX,  startY,  posx, posy))
          {
              super.bouger(this,startX,startY,posx, posy);
              
          }
     
     }
    private boolean MouvFou(Fou fou, int startX, int startY, int endX, int endY) {
        
        // Vérifie que le mouvement est bien en diagonale
        if (Math.abs(endX - startX) != Math.abs(endY - startY)) {
            return false;
        }

        int xDirection = (endX - startX) > 0 ? 1*Math.abs(endX - startX) : -1*Math.abs(endX - startX);
        int yDirection = (endY - startY) > 0 ? 1*Math.abs(endY - startY) : -1*Math.abs(endY - startY); 

        int x = startX + ((endX - startX) > 0 ? 1 : -1);
        int y = startY + ((endY - startY) > 0 ? 1 : -1);
       
        // Vérifie que toutes les cases sur le chemin sont vides
        while (x != endX && y != endY) {
 
            if (Echiquier.getPiece(x*64, y*64) != null) {
                
                return false;
            }
            x += ((endX - startX) > 0 ? 1 : -1);
            y += ((endY - startY) > 0 ? 1 : -1);
            
        }

        // Vérifie la case de destination
        Piece destinationPion = Echiquier.getPiece(endX*64, endY*64);
        if (destinationPion == null || destinationPion.estBlanc != fou.estBlanc) {
            return true;
        }
        return false;
}

    
}
