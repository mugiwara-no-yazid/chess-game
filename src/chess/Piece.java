/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import static chess.Echiquier.TAILLE_GRILLE;
import java.util.*;
import javax.swing.JPanel;

/**
 *
 * @author Yazid Soumanou
 */
public class Piece {
    int posx;
    int posy;
    int piecex;
    int piecey;
    String nom;
    boolean estBlanc;
    LinkedList <Piece> piece;

    public Piece(int posx, int posy, boolean estBlanc, String nom, LinkedList<Piece> piece) {
        this.posx = posx;
        this.posy = posy;
        this.piecex = (posx)*64;
        this.piecey = (posy)*64; 
        this.estBlanc = estBlanc;
        this.nom = nom;
        this.piece = piece;
        piece.add(this);
    }
    protected boolean Debordement(int x, int y) {
        return x >= 0 && x < TAILLE_GRILLE && y >= 0 && y < TAILLE_GRILLE;
    }
    public void bouger(Piece piece, int startX, int startY,int posx,int posy){
        if (Debordement(posx,posy))
        {
            if(Echiquier.getPiece(posx*64, posy*64)!=null){
            if(Echiquier.getPiece(posx*64, posy*64).estBlanc!=estBlanc)
            {
                Echiquier.getPiece(posx*64, posy*64).tuerpiece();   
            }else
            {
                this.piecex=this.posx*64;
                this.piecey=this.posy*64;
                return;
            } }
        this.posx=posx;
        this.posy=posy;
        this.piecex=posx*64;
        this.piecey=posy*64;
        }
        else
            {
                System.out.println("Mouvement non autorisé");
                this.piecex=this.posx*64;
                this.piecey=this.posy*64;
                return;
            }
        
    }
    public JPanel chemin(Piece piece, int startX, int startY)
    {
        JPanel pn = new JPanel();
        switch (piece.nom){
            case "pion":
                Pion p = (Pion) this;
                return pn = p.cheminPion(p, startX, startY);
            
            case "tour":
                Tour t = (Tour) this;
                return pn = t.cheminTour(t, startX, startY);
               
            case "fou":
                Fou f = (Fou) this;
                return pn = f.cheminFou(f, startX, startY);
            
               
        }
                   
       /* if(piece.nom=="pion")
        {
         Pion p = (Pion) this;
         
         return pn = p.cheminPion(p, startX, startY);
         //System.out.println("Mouvement chemin");   
         
        }*/
     //System.out.println(this.nom);
     return pn;   
    }
    
    public void tuerpiece()
    {
        piece.remove(this);
    }
}
