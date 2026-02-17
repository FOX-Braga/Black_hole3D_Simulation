package com.mycompany.black_hole3d.lib;

import javafx.scene.shape.Sphere;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class particular {
    public double x, y, z;
    public double velocidade_x, velocidade_y, velocidade_z;
    public Sphere FormaVisual;
    private PhongMaterial material; 

    public particular(double x, double y, double z, double vx, double vy, double vz) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.velocidade_x = vx;
        this.velocidade_y = vy;
        this.velocidade_z = vz;

        this.FormaVisual = new Sphere(1.5 + Math.random() * 2);
        
        this.material = new PhongMaterial();
        
        this.material.setSpecularColor(Color.WHITE);
        this.material.setSpecularPower(128); 
        
        this.material.setSelfIlluminationMap(null); 
        
        this.FormaVisual.setMaterial(this.material);
    }

    public void atualizar() {
        x += velocidade_x;
        y += velocidade_y;
        z += velocidade_z;

        FormaVisual.setTranslateX(x);
        FormaVisual.setTranslateY(y);
        FormaVisual.setTranslateZ(z);
    }

    public void aplicarGravidade(double centroX, double centroY, double centroZ, double massa) {
        double dx = centroX - x;
        double dy = centroY - y;
        double dz = centroZ - z;
        double distSq = dx * dx + dy * dy + dz * dz;
        double dist = Math.sqrt(distSq);

        double distLonge = 1200.0; 
        double distPerto = 50.0; 
        
        double t = (dist - distPerto) / (distLonge - distPerto);
        t = Math.max(0.0, Math.min(1.0, t));

        Color corAtual = Color.WHITE.interpolate(Color.RED, t);
        this.material.setDiffuseColor(corAtual);

        if (dist > 45) {
            double forca = massa / distSq;
            velocidade_x += forca * (dx / dist);
            velocidade_y += forca * (dy / dist);
            velocidade_z += forca * (dz / dist);
        } else {
            FormaVisual.setVisible(false);
        }
    }
}