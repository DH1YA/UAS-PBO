package Main;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
public class RescaleGambar {
    public Image resize(int w, int h, Image img){
        BufferedImage bufimg = new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB );
        Graphics2D dimensi = bufimg.createGraphics();
        dimensi.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        dimensi.drawImage(img, 0, 0, w, h, null);
        dimensi.dispose();
        return bufimg;
    } 
}
