package com.mycompany.black_hole3d;

import com.mycompany.black_hole3d.lib.EspacoTempo;
import com.mycompany.black_hole3d.lib.particular;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Black_hole3D extends Application {

    private List<particular> listaDeParticulas = new ArrayList<>();
    private Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);

    @Override
    public void start(Stage stage) {
        PhongMaterial matBuraco = new PhongMaterial(Color.BLACK);
        matBuraco.setSpecularColor(Color.BLACK);
        Sphere buracoNegro = new Sphere(150);
        buracoNegro.setMaterial(matBuraco);
        buracoNegro.setDrawMode(DrawMode.FILL);

        Group raiz = new Group(buracoNegro);

        AmbientLight luz360 = new AmbientLight(Color.WHITE);
        raiz.getChildren().add(luz360);

        EspacoTempo manto = new EspacoTempo(3000, 150, 1200.0);
        raiz.getChildren().add(manto.grade);
        manto.grade.setTranslateY(800);


        for (int i = 0; i < 7000; i++) {
            double ang = Math.random() * 2 * Math.PI;
            double raio = 300 + Math.random() * 800;
            double vMag = (Math.sqrt(5000.0 / raio)) * (0.8 + Math.random() * 0.3);
            
            double px, py, pz, vx, vy, vz;
            if (Math.random() < 0.6) {
                px = Math.cos(ang) * raio; pz = Math.sin(ang) * raio; py = (Math.random() - 0.5) * 200;
                vx = -Math.sin(ang) * vMag; vz = Math.cos(ang) * vMag; vy = (Math.random() - 0.5) * 0.4;
            } else {
                px = Math.cos(ang) * raio; py = Math.sin(ang) * raio; pz = (Math.random() - 0.5) * 200;
                vx = -Math.sin(ang) * vMag; vy = Math.cos(ang) * vMag; vz = (Math.random() - 0.5) * 0.4;
            }

            particular p = new particular(px, py, pz, vx, vy, vz);
            listaDeParticulas.add(p);
            raiz.getChildren().add(p.FormaVisual);
        }

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(150000.0);
        camera.setTranslateZ(-9000);

        Group cameraPivot = new Group(camera);
        cameraPivot.getTransforms().addAll(rotateX, rotateY);
        raiz.getChildren().add(cameraPivot);

        Scene cena = new Scene(raiz, 1280, 720, true, SceneAntialiasing.BALANCED);
        cena.setFill(Color.BLACK);
        cena.setCamera(camera);

        cena.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W -> camera.setTranslateZ(camera.getTranslateZ() + 250);
                case S -> camera.setTranslateZ(camera.getTranslateZ() - 250);
                case UP -> rotateX.setAngle(rotateX.getAngle() - 3);
                case DOWN -> rotateX.setAngle(rotateX.getAngle() + 3);
                case LEFT -> rotateY.setAngle(rotateY.getAngle() - 3);
                case RIGHT -> rotateY.setAngle(rotateY.getAngle() + 3);
                case SPACE -> { rotateX.setAngle(0); rotateY.setAngle(0); camera.setTranslateZ(-9000); }
            }
        });

        stage.setTitle("Buraco Negro - Degradê Dinâmico");
        stage.setScene(cena);
        stage.show();
        timer.start();
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            for (particular p : listaDeParticulas) {
                p.aplicarGravidade(0, 0, 0, 5000.0);
                p.atualizar();
            }
        }
    };

    public static void main(String[] args) {
        launch(args);
    }
}