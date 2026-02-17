package com.mycompany.black_hole3d.lib;

import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class EspacoTempo {
    public MeshView grade;

    public EspacoTempo(int tamanho, int resolucao, double forca) {
        TriangleMesh mesh = new TriangleMesh();

        int numLinhas = (tamanho * 2) / resolucao + 1;
        for (int z = -tamanho; z <= tamanho; z += resolucao) {
            for (int x = -tamanho; x <= tamanho; x += resolucao) {
                mesh.getPoints().addAll((float) x, (float) calcularDeformacao(x, z, forca), (float) z);
            }
        }

        mesh.getTexCoords().addAll(0, 0);

        for (int r = 0; r < numLinhas - 1; r++) {
            for (int c = 0; c < numLinhas - 1; c++) {
                int p1 = r * numLinhas + c;
                int p2 = p1 + 1;
                int p3 = p1 + numLinhas;
                int p4 = p3 + 1;
                mesh.getFaces().addAll(p1, 0, p3, 0, p2, 0);
                mesh.getFaces().addAll(p2, 0, p3, 0, p4, 0);
            }
        }

        this.grade = new MeshView(mesh);
        this.grade.setDrawMode(javafx.scene.shape.DrawMode.LINE); 
        
        PhongMaterial mat = new PhongMaterial(Color.web("#333333"));
        mat.setSpecularColor(Color.web("#111111")); 
        this.grade.setMaterial(mat);
    }

    private double calcularDeformacao(double x, double z, double forca) {
        double r = Math.sqrt(x * x + z * z);
        return - (forca * 2000) / (r + 200);
    }
}