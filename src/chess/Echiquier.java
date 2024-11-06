/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

/**
 *
 * @author Yazid Soumanou
 */
public class Echiquier {
    public static LinkedList <Piece> piece=new  LinkedList();
    public static Piece pieceSelectionner = null;
    public void dessiner() throws IOException
    {
            JFrame fenetre = new JFrame("Echec et mat");
            fenetre.setBounds(50,50,512,512);
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setUndecorated(true);
            
            BufferedImage tous=ImageIO.read(new File("C:\\Users\\Yazid Soumanou\\Desktop\\Chess\\chess.png"));
            Image imgs[]=new Image[12];
            int ind=0;
            for(int y=0;y<400;y+=200){
                for(int x=0;x<1200;x+=200){
                    imgs[ind]=tous.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                    ind++;
            }    }
            Piece nTour=new Piece(0, 0, false, "tour", piece);
            Piece nChevalier=new Piece(1, 0, false, "chevalier", piece);
            Piece nFou=new Piece(2, 0, false, "fou", piece);
            Piece nReine=new Piece(3, 0, false, "reine", piece);
            Piece nRoi=new Piece(4, 0, false, "roi", piece);
            Piece nFou2=new Piece(5, 0, false, "fou", piece);
            Piece nChevalier2=new Piece(6, 0, false, "chevalier", piece);
            Piece nTour2=new Piece(7, 0, false, "tour", piece);
            Piece nPion1=new Piece(1, 1, false, "pion", piece);
            Piece nPion2=new Piece(2, 1, false, "pion", piece);
            Piece nPion3=new Piece(3, 1, false, "pion", piece);
            Piece nPion4=new Piece(4, 1, false, "pion", piece);
            Piece nPion5=new Piece(5, 1, false, "pion", piece);
            Piece nPion6=new Piece(6, 1, false, "pion", piece);
            Piece nPion7=new Piece(7, 1, false, "pion", piece);
            Piece nPion8=new Piece(0, 1, false, "pion", piece);

            Piece bTour=new Piece(0, 7, true, "tour", piece);
            Piece bChevalier=new Piece(1, 7, true, "chevalier", piece);
            Piece bFou=new Piece(2, 7, true, "fou", piece);
            Piece bReine=new Piece(3, 7, true, "reine", piece);
            Piece bRoi=new Piece(4, 7, true, "roi", piece);
            Piece bFou2=new Piece(5, 7, true, "fou", piece);
            Piece bChevalier2=new Piece(6, 7, true, "chevalier", piece);
            Piece bTour2=new Piece(7, 7, true, "tour", piece);
            Piece bPion1=new Piece(1, 6, true, "pion", piece);
            Piece bPion2=new Piece(2, 6, true, "pion", piece);
            Piece bPion3=new Piece(3, 6, true, "pion", piece);
            Piece bPion4=new Piece(4, 6, true, "pion", piece);
            Piece bPion5=new Piece(5, 6, true, "pion", piece);
            Piece bPion6=new Piece(6, 6, true, "pion", piece);
            Piece bPion7=new Piece(7, 6, true, "pion", piece);
            Piece bPion8=new Piece(0, 6, true, "pion", piece);
            
            
            JPanel pn = new JPanel(){
                @Override
                public void paint(Graphics g) {
                    boolean blanc =true;
                    for(int x =0 ;x<8;x++)
                    {
                        for(int y=0;y<8;y++)
                        {
                            if (blanc)
                            {
                                g.setColor(Color.white.darker());
                            }
                            else
                            {
                                g.setColor(new Color(115,145,65));
                            }
                            g.fillRect(x*64, y*64, 64, 64);
                         blanc = !blanc;
                        }
                        blanc = !blanc;
                    }
                    
                    for(Piece p: piece){
                    int ind=0;
                    if(p.nom.equalsIgnoreCase("roi")){
                        ind=0;
                    }
                    if(p.nom.equalsIgnoreCase("reine")){
                        ind=1;
                    }
                    if(p.nom.equalsIgnoreCase("fou")){
                        ind=2;
                    }
                    if(p.nom.equalsIgnoreCase("chevalier")){
                        ind=3;
                    }
                    if(p.nom.equalsIgnoreCase("tour")){
                        ind=4;
                    }
                    if(p.nom.equalsIgnoreCase("pion")){
                        ind=5;
                    }
                    if(!p.estBlanc){
                        ind+=6;
                    }
                    g.drawImage(imgs[ind], p.piecex, p.piecey, this);
                }

                }

            };
            
            fenetre.add(pn);
            
            fenetre.addMouseListener( new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    //System.out.println((getPiece(e.getX(),e.getY()).estBlanc?"Blanc ":"Noir ") + getPiece(e.getX(),e.getY()).nom);
                    pieceSelectionner=getPiece(e.getX(),e.getY());
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                   pieceSelectionner.bouger(e.getX()/64,e.getY()/64);
                   fenetre.repaint();
                  
                }

                @Override
                public void mouseEntered(MouseEvent e) {
               }

                @Override
                public void mouseExited(MouseEvent e) {
               }
            }
            );
            
            fenetre.addMouseMotionListener(new MouseMotionListener()
            {
                @Override
                public void mouseDragged(MouseEvent e) {
                     if(pieceSelectionner!=null)
                    {
                        pieceSelectionner.piecex =e.getX()-32;
                        pieceSelectionner.piecey =e.getY()-32;
                        fenetre.repaint();
                    }
                     
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                }
            }
            );
            fenetre.setVisible(true);
    }
    public static Piece getPiece(int x, int y)
    {
        //int posx=x/65; 
        //int posy=y/95;
        int posx=x/64; 
        int posy=y/64;

        for (Piece p:piece)
        {
            if(posx == p.posx && p.posy == posy)
            {
                return p;
            }
        }
        return null;
    }
}
