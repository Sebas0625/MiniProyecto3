package project.miniproject3.model;

import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;

public class Ships {

    public static Group carrier() {
        Group group = new Group();

        // Polygons
        Polygon polygon1 = new Polygon(0.0, 146.5, 0.0, 0.0, 25.0, 0.0, 25.0, 11.5, 40.0, 36.5, 40.0, 104.0, 29.5, 130.25, 29.5, 160.0, 6.75, 160.0);
        polygon1.setLayoutX(187.0);
        polygon1.setLayoutY(120.0);
        polygon1.setStroke(Color.BLACK);
        polygon1.setFill(null);
        group.getChildren().add(polygon1);

        Polygon polygon2 = new Polygon(0.0, 146.5, 0.0, 0.0, 25.0, 0.0, 25.0, 11.5, 40.0, 36.5, 40.0, 104.0, 29.5, 130.25, 29.5, 160.0, 6.75, 160.0);
        polygon2.setLayoutX(185.0);
        polygon2.setLayoutY(120.0);
        polygon2.setStroke(Color.BLACK);
        polygon2.setFill(Color.web("#727272"));
        group.getChildren().add(polygon2);

        // Rectangles
        Rectangle rectangle1 = new Rectangle(187.0, 123.0, 20.0, 136.0);
        rectangle1.setArcWidth(5.0);
        rectangle1.setArcHeight(5.0);
        rectangle1.setFill(Color.web("#23940c"));
        rectangle1.setStroke(Color.BLACK);
        group.getChildren().add(rectangle1);

        Rectangle rectangle2 = new Rectangle(209.0, 152.0, 11.0, 11.0);
        rectangle2.setArcWidth(5.0);
        rectangle2.setArcHeight(5.0);
        rectangle2.setFill(Color.DODGERBLUE);
        rectangle2.setStroke(Color.BLACK);
        group.getChildren().add(rectangle2);

        Rectangle rectangle3 = new Rectangle(196.0, 163.0, 2.0, 23.0);
        rectangle3.setArcWidth(5.0);
        rectangle3.setArcHeight(5.0);
        rectangle3.setFill(Color.web("#c6c6c6"));
        rectangle3.setStroke(Color.BLACK);
        group.getChildren().add(rectangle3);

        Rectangle rectangle4 = new Rectangle(195.0, 163.0, 4.0, 15.0);
        rectangle4.setArcWidth(5.0);
        rectangle4.setArcHeight(5.0);
        rectangle4.setFill(Color.web("#c6c6c6"));
        rectangle4.setRotate(90.0);
        rectangle4.setStroke(Color.BLACK);
        group.getChildren().add(rectangle4);

        Rectangle rectangle5 = new Rectangle(196.0, 178.0, 2.0, 10.0);
        rectangle5.setArcWidth(5.0);
        rectangle5.setArcHeight(5.0);
        rectangle5.setFill(Color.web("#c6c6c6"));
        rectangle5.setRotate(90.0);
        rectangle5.setStroke(Color.BLACK);
        group.getChildren().add(rectangle5);

        Rectangle rectangle6 = new Rectangle(196.0, 163.0, 2.0, 3.0);
        rectangle6.setArcWidth(5.0);
        rectangle6.setArcHeight(5.0);
        rectangle6.setFill(Color.web("#aae7f2"));
        rectangle6.setStroke(Color.BLACK);
        group.getChildren().add(rectangle6);

        Rectangle rectangle7 = new Rectangle(203.0, 169.0, 2.0, 3.0);
        rectangle7.setArcWidth(5.0);
        rectangle7.setArcHeight(5.0);
        rectangle7.setFill(Color.RED);
        rectangle7.setStroke(Color.BLACK);
        group.getChildren().add(rectangle7);

        Rectangle rectangle8 = new Rectangle(189.0, 169.0, 2.0, 3.0);
        rectangle8.setArcWidth(5.0);
        rectangle8.setArcHeight(5.0);
        rectangle8.setFill(Color.web("#2f00ff"));
        rectangle8.setStroke(Color.BLACK);
        group.getChildren().add(rectangle8);

        // Lines
        for (double layoutX : new double[]{287.0, 307.0}) {
            for (double layoutY : new double[]{124.0, 136.0, 147.0, 158.0, 169.0, 180.0, 192.0, 203.0, 215.0, 227.0, 239.0, 251.0}) {
                Line line = new Line(-100.0, 0.0, -100.0, 7.5);
                line.setLayoutX(layoutX);
                line.setLayoutY(layoutY);
                line.setStroke(Color.RED);
                group.getChildren().add(line);
            }
        }

        Line line13 = new Line(-100.0, 0.0, -100.0, 7.5);
        line13.setLayoutX(307.0);
        line13.setLayoutY(124.0);
        line13.setStroke(Color.RED);
        group.getChildren().add(line13);

        // Lines with specific colors
        Line yellowLine1 = new Line(-100.0, 0.0, -100.0, 57.25);
        yellowLine1.setLayoutX(314.0);
        yellowLine1.setLayoutY(164.0);
        yellowLine1.setStroke(Color.web("#fbff00"));
        group.getChildren().add(yellowLine1);

        Line yellowLine2 = new Line(-102.25, 0.0, -100.0, 7.75);
        yellowLine2.setLayoutX(314.0);
        yellowLine2.setLayoutY(233.0);
        yellowLine2.setStroke(Color.web("#ddff00"));
        group.getChildren().add(yellowLine2);

        Line whiteLine = new Line(-100.0, 0.0, -100.0, 135.0);
        whiteLine.setLayoutX(297.0);
        whiteLine.setLayoutY(124.0);
        whiteLine.setStroke(Color.WHITE);
        group.getChildren().add(whiteLine);

        // Gray lines
        Line grayLine1 = new Line(-100.0, 0.0, -100.0, 7.5);
        grayLine1.setLayoutX(309.0);
        grayLine1.setLayoutY(270.0);
        grayLine1.setStroke(Color.web("#c6c6c6"));
        group.getChildren().add(grayLine1);

        Line grayLine2 = new Line(-101.25, 0.0, -94.75, 7.5);
        grayLine2.setLayoutX(307.0);
        grayLine2.setLayoutY(265.0);
        grayLine2.setStroke(Color.web("#c6c6c6"));
        group.getChildren().add(grayLine2);

        return group;
    }


    public static Group submarine() {
        return new Group(
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(242.0);
                    setLayoutY(243.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(243.0);
                    setLayoutY(242.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(244.0);
                    setLayoutY(241.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(245.0);
                    setLayoutY(240.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(268.0);
                    setLayoutY(241.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(264.0);
                    setLayoutY(241.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(248.0);
                    setLayoutY(241.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -59.25, 0) {{
                    setLayoutX(237.0);
                    setLayoutY(245.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -59.25, 0) {{
                    setLayoutX(237.0);
                    setLayoutY(247.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-105.3, 0, -87.0, 0) {{
                    setLayoutX(240.0);
                    setLayoutY(249.0);
                }},
                new Line(-79.78, 0.07, -86.0, 0) {{
                    setLayoutX(217.6);
                    setLayoutY(246.0);
                    setRotate(90.0);
                }},
                new Line(-105.3, 0, -87.0, 0) {{
                    setLayoutX(266.0);
                    setLayoutY(249.0);
                }},
                new Line(-79.78, 0.07, -86.0, 0) {{
                    setLayoutX(262.6);
                    setLayoutY(246.0);
                    setRotate(90.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(245.0);
                    setLayoutY(241.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(240.0);
                    setLayoutY(244.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(246.0);
                    setLayoutY(238.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(248.0);
                    setLayoutY(239.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(247.0);
                    setLayoutY(237.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(247.0);
                    setLayoutY(236.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(248.0);
                    setLayoutY(235.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(269.0);
                    setLayoutY(241.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(270.0);
                    setLayoutY(242.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(272.0);
                    setLayoutY(243.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(275.0);
                    setLayoutY(244.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(277.0);
                    setLayoutY(244.5);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(268.0);
                    setLayoutY(240.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(266.0);
                    setLayoutY(237.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(265.0);
                    setLayoutY(235.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(267.0);
                    setLayoutY(239.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                new Polygon(33.5, -31.76, 32.0, 20.25, 32.0, 32.0, 30.0, 37.5, 27.75, 43.75, 23.0, 60.0, 17.0, 43.75, 14.75, 37.5, 12.75, 32.0, 12.75, 20.25, 12.75, -31.76, 10.0, -31.76, 10.0, -35.5, 12.75, -35.5, 12.75, -40.0, 24.0, -40.0, 33.5, -40.0, 33.5, -35.5, 35.75, -35.5, 35.75, -31.76) {{
                    setFill(Color.web("#3f3f3f"));
                    setStroke(Color.BLACK);
                    setLayoutX(134.0);
                    setLayoutY(200.0);
                }},
                new QuadCurve(-15.0, 31.0, -3.5, 18.75, 6.75, 31.0) {{
                    setFill(Color.web("#3f3f3f"));
                    setStroke(Color.BLACK);
                    setLayoutX(161.0);
                    setLayoutY(129.0);
                }},
                new QuadCurve(43.0, 31.5, 52.0, 31.5, 56.0, 19.75) {{
                    setFill(Color.web("#1f93ff00"));
                    setStroke(Color.BLACK);
                    setLayoutX(91.0);
                    setLayoutY(212.0);
                }},
                new QuadCurve(70.453, 30.0, 58.5, 30.0, 56.0, 18.5) {{
                    setFill(Color.web("#1f93ff00"));
                    setStroke(Color.BLACK);
                    setLayoutX(110.0);
                    setLayoutY(213.0);
                }},
                new Line(-85.5, 0, -102.75, 0) {{
                    setLayoutX(251.0);
                    setLayoutY(173.0);
                    setStroke(Color.WHITE);
                    setStrokeWidth(3.0);
                }},

                new Line(-100.0, 0, -100.0, 0) {{
                    setLayoutX(242.0);
                    setLayoutY(243.0);
                    setStroke(Color.web("#3f3f3f"));
                    setStrokeWidth(4.0);
                }},
                // (Se omiten otras líneas para simplificar; este es el resto del código base proporcionado)

                // Hélice en la parte inferior
                new Line(-100.0, 0, -100.0, 6.5) {{
                    setLayoutX(257.0);
                    setLayoutY(261.0);
                    setStrokeWidth(0.5);
                }},
                new Line(-100.0, 0, -98.5, 0) {{
                    setLayoutX(256.0);
                    setLayoutY(260.0);
                    setStrokeWidth(2.0);
                }},

                // Aletas laterales con líneas blancas
                new Line(-93.0, 0, -100.0, 0) {{
                    setLayoutX(240.0);
                    setLayoutY(249.0);
                    setStroke(Color.WHITE);
                    setStrokeWidth(2.0);
                }},
                new Line(-93.0, 0, -100.0, 0) {{
                    setLayoutX(265.0);
                    setLayoutY(249.0);
                    setStroke(Color.WHITE);
                    setStrokeWidth(2.0);
                }},

                // Primera arma en la parte superior
                new Polygon(94.0, 28.25, 94.0, 17.25, 103.0, 17.25, 103.0, 28.25, 101.5, 28.25, 101.5, 38.5, 100.0, 38.5, 100.0, 50.25, 97.5, 50.25, 97.5, 38.5, 95.5, 38.5, 95.5, 28.25) {{
                    setFill(Color.web("#848484"));
                    setStroke(Color.BLACK);
                    setStrokeWidth(1);
                    setLayoutX(58.5);
                    setLayoutY(178.0);
                }},


                // Línea blanca central en el cuerpo del submarino
                new Line(-85.5, 0, -102.75, 0) {{
                    setLayoutX(251.0);
                    setLayoutY(173.0);
                    setStroke(Color.WHITE);
                    setStrokeWidth(3.0);
                }}
        );


    }


    public static Group destroyer() {
        Group barco = new Group();

        // Polígono principal del barco
        Polygon polygon = new Polygon(
                180.0, 103.5, 180.0, 116.5, 180.0, 116.5, 180.0, 126.0, 182.5, 126.0,
                182.5, 161.5, 180.0, 161.5, 180.0, 167.5, 175.5, 167.5, 170.0, 167.5,
                170.0, 161.5, 167.75, 161.5, 167.75, 126.0, 170.0, 126.0, 170.0, 116.5,
                170.0, 116.5, 170.0, 103.5, 171.75, 103.5, 178.5, 103.5
        );
        polygon.setFill(Color.web("#ac8b62"));
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(1);
        polygon.setLayoutX(75.0);
        polygon.setLayoutY(30.0);

        // Rectángulos
        Rectangle rect1 = new Rectangle(245.0, 190.0, 10.0, 9.0);
        rect1.setFill(Color.web("#6a6a6a"));
        rect1.setStroke(Color.BLACK);

        Rectangle rect2 = new Rectangle(245.0, 161.0, 10.0, 21.0);
        rect2.setFill(Color.web("#848484"));
        rect2.setStroke(Color.BLACK);
        rect2.setStrokeWidth(0.5);

        Rectangle rect3 = new Rectangle(246.5, 159.0, 7.0, 23.0);
        rect3.setFill(Color.web("#848484"));
        rect3.setStroke(Color.BLACK);
        rect3.setArcWidth(2.0);
        rect3.setArcHeight(2.0);
        rect3.setStrokeWidth(0.5);

        Rectangle rect4 = new Rectangle(244.0, 163.0, 12.0, 2.0);
        rect4.setFill(Color.web("#848484"));
        rect4.setStroke(Color.BLACK);
        rect4.setArcWidth(2.0);
        rect4.setArcHeight(2.0);
        rect4.setStrokeWidth(0.3);

        Rectangle rect5 = new Rectangle(244.0, 175.0, 12.0, 2.0);
        rect5.setFill(Color.web("#848484"));
        rect5.setStroke(Color.BLACK);
        rect5.setArcWidth(2.0);
        rect5.setArcHeight(2.0);
        rect5.setStrokeWidth(0.3);

        Rectangle rect6 = new Rectangle(243.0, 169.0, 14.0, 4.0);
        rect6.setFill(Color.web("#848484"));
        rect6.setStroke(Color.BLACK);
        rect6.setArcWidth(2.0);
        rect6.setArcHeight(2.0);
        rect6.setRotate(90.0);
        rect6.setStrokeWidth(0.3);

        // Curva cuadrática
        QuadCurve quadCurve = new QuadCurve(
                4.25, 27.5, // Inicio
                10.25, 6.5, // Control
                16.0, 27.5  // Final
        );
        quadCurve.setLayoutX(240.0);
        quadCurve.setLayoutY(107.0);
        quadCurve.setFill(Color.web("#6a6a6a"));
        quadCurve.setStroke(Color.BLACK);

        // Líneas
        Line line1 = new Line(-100.0, 4.25, -100.0, 4.25);
        line1.setLayoutX(348.0);
        line1.setLayoutY(193.0);
        line1.setStroke(Color.WHITE);

        Line line2 = new Line(-100.0, 4.25, -100.0, 4.25);
        line2.setLayoutX(352.0);
        line2.setLayoutY(193.0);
        line2.setStroke(Color.WHITE);

        Line line3 = new Line(-100.0, 4.25, -100.0, 4.25);
        line3.setLayoutX(350.0);
        line3.setLayoutY(193.0);
        line3.setRotate(90.0);
        line3.setStroke(Color.WHITE);

        Line line6 = new Line(-94.5, -99.5, -99.5, -99.5);
        line6.setLayoutX(347.0);
        line6.setLayoutY(238);
        line6.setStroke(Color.WHITE);
        line6.setStrokeWidth(4.0);

        // Línea blanca de abajo
        Line lineaHorizontalHelipuerto = new Line(98, 160, 102, 160);
        lineaHorizontalHelipuerto.setStroke(Color.WHITE);
        lineaHorizontalHelipuerto.setStrokeWidth(2);
        lineaHorizontalHelipuerto.setLayoutX(150.0);
        lineaHorizontalHelipuerto.setLayoutY(35.0);



        // Agregar todos los elementos al grupo
        barco.getChildren().addAll(
                polygon, rect1, rect2, rect3, rect4, rect5, rect6, quadCurve,
                line1, line2, line3,  line6, lineaHorizontalHelipuerto
        );

        return barco;
    }

    public static Group frigate() {
        Group group = new Group();

        // Proa (Polígono superior)
        Polygon proa = new Polygon();
        proa.getPoints().addAll(
                103.0, 10.75,
                103.0, 14.75,
                120.75, 14.75,
                120.75, 10.75,
                118.25, 5.0,
                112.5, -3.0,
                105.5, 5.0
        );
        proa.setFill(Color.web("#8d704b"));
        proa.setStroke(Color.web("#711c0f"));
        proa.setLayoutX(99.0);
        proa.setLayoutY(129.0);

        // Parte trasera (Polígono)
        Polygon parteTrasera = new Polygon();
        parteTrasera.getPoints().addAll(
                231.5, 77.25,
                233.0, 83.1245,
                248.0, 83.1245,
                250.0, 77.25
        );
        parteTrasera.setFill(Color.web("#711c0f"));
        parteTrasera.setStroke(Color.web("#711c0f"));
        parteTrasera.setLayoutX(-30.0);
        parteTrasera.setLayoutY(67.0);

        // Círculos azules (ventanas)
        Circle ventana1 = new Circle(206.0, 147.0, 2.0);
        ventana1.setFill(Color.DODGERBLUE);
        ventana1.setStroke(Color.BLACK);
        ventana1.setStrokeWidth(0.6);


        Circle ventana2 = new Circle(211.0, 147.0, 2.0);
        ventana2.setFill(Color.DODGERBLUE);
        ventana2.setStroke(Color.BLACK);
        ventana2.setStrokeWidth(0.6);

        Circle ventana3 = new Circle(216.0, 147.0, 2.0);
        ventana3.setFill(Color.DODGERBLUE);
        ventana3.setStroke(Color.BLACK);
        ventana3.setStrokeWidth(0.6);

        // Rectángulo gris (estructura central)
        Rectangle estructuraCentral = new Rectangle(206.5, 133.0, 9.0, 8.0);
        estructuraCentral.setArcHeight(5.0);
        estructuraCentral.setArcWidth(2.0);
        estructuraCentral.setFill(Color.web("#757575"));
        estructuraCentral.setStroke(Color.BLACK);

        // Línea vertical (detalle inferior)
        Line lineaVertical = new Line(-100.0,0.0 , -93.25, 0.0);
        lineaVertical.setRotate(90.0);
        lineaVertical.setStrokeWidth(2.0);
        lineaVertical.setLayoutX(307.8);
        lineaVertical.setLayoutY(131.0);


        // Agregar todos los elementos al grupo
        group.getChildren().addAll(
                proa,
                parteTrasera,
                ventana1,
                ventana2,
                ventana3,
                estructuraCentral,
                lineaVertical
        );

        return group;
    }

    public static Group createBomb() {
        Group bombGroup = new Group();

        // Dimensiones para 40x40 píxeles
        double bombRadius = 16; // Radio de la esfera
        double fuseBaseRadius = 4; // Base del cable
        double sparkSize = 6; // Tamaño de la chispa

        // Material para la esfera (brillo y color negro)
        PhongMaterial bombMaterial = new PhongMaterial();
        bombMaterial.setDiffuseColor(Color.BLACK);
        bombMaterial.setSpecularColor(Color.DARKGRAY);

        // Cuerpo de la bomba
        Sphere bombBody = new Sphere(bombRadius);
        bombBody.setMaterial(bombMaterial);
        bombBody.setTranslateX(20); // Centrado en X (mitad de 40)
        bombBody.setTranslateY(24); // Alineado hacia abajo para dejar espacio al cable y chispa
        bombBody.setTranslateZ(0);

        // Base del cable
        Sphere fuseBase = new Sphere(fuseBaseRadius);
        PhongMaterial fuseMaterial = new PhongMaterial(Color.LIGHTGRAY);
        fuseBase.setMaterial(fuseMaterial);
        fuseBase.setTranslateX(20);
        fuseBase.setTranslateY(8); // Justo encima del cuerpo de la bomba

        // cable
        Line fuse = new Line(20, 8, 28, 2); // Línea diagonal desde la base al espacio superior
        fuse.setStroke(Color.SADDLEBROWN);
        fuse.setStrokeWidth(2);

        // Chispa de
        Polygon spark = new Polygon();
        spark.getPoints().addAll(
                28.0, 2.0,  // Centro de la chispa
                25.0, -2.0, // Puntas exteriores
                31.0, -2.0,
                27.0, -6.0,
                29.0, -6.0,
                28.0, -10.0,
                25.0, -4.0,
                31.0, -4.0
        );
        spark.setFill(Color.ORANGE);
        spark.setStroke(Color.RED);
        spark.setStrokeWidth(0.8);

//        luz apuntando a la esfera
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(10.0);
        light.setTranslateY(0.0);
        light.setTranslateZ(0.0);

//        luz de ambiente que ilumina a la esfera y la base del cable de la bomba
        AmbientLight ambientLight = new AmbientLight(Color.LIGHTGRAY);

        // Agregar las partes al grupo
        bombGroup.getChildren().addAll(bombBody, fuseBase, fuse, spark,light, ambientLight);

        bombGroup.setLayoutX(100.0);
        bombGroup.setLayoutY(100.0);

        return bombGroup;
    }

    public static Group drawX(){
        Group xShape = new Group();
        // Crear el primer rectángulo de la "X"
        Rectangle rect1 = new Rectangle(40, 8); // Largo de 40, ancho de 8
        rect1.setFill(Color.RED); // Color de la "X"
        rect1.setRotate(45); // Rotar 45 grados
        rect1.setTranslateX(20); // Centrar en X
        rect1.setTranslateY(20); // Centrar en Y

        // Crear el segundo rectángulo de la "X"
        Rectangle rect2 = new Rectangle(40, 8); // Largo de 40, ancho de 8
        rect2.setFill(Color.RED); // Color de la "X"
        rect2.setRotate(-45); // Rotar -45 grados
        rect2.setTranslateX(20); // Centrar en X
        rect2.setTranslateY(20); // Centrar en Y

        // Grupo raíz para contener los rectángulos
         xShape.getChildren().addAll(rect1, rect2);
        return xShape;
    }

    public static Group createCrosshair() {
        Group crosshairGroup = new Group();

        // Círculo exterior
        Circle outerCircle = new Circle(20, 20, 18);
        outerCircle.setStroke(Color.BLACK);
        outerCircle.setFill(Color.TRANSPARENT);
        outerCircle.setStrokeWidth(2);

        // Círculo interior
        Circle innerCircle = new Circle(20, 20, 10);
        innerCircle.setStroke(Color.BLACK);
        innerCircle.setFill(Color.TRANSPARENT);
        innerCircle.setStrokeWidth(1.5);

        // Punto rojo en el centro
        Circle centerDot = new Circle(20, 20, 2);
        centerDot.setFill(Color.RED);

        // Líneas horizontales
        Line horizontalLine = new Line(2, 20, 12, 20);
        Line horizontalLine2 = new Line(28, 20, 38, 20);
        horizontalLine.setStroke(Color.BLACK);
        horizontalLine.setStrokeWidth(2);

        // Líneas verticales
        Line verticalLine = new Line(20, 2, 20, 12);
        Line verticalLine2 = new Line(20, 28, 20, 38);
        verticalLine.setStroke(Color.BLACK);
        verticalLine.setStrokeWidth(2);

        // Pequeñas marcas en el círculo interior (cruces)
        Line leftCross = new Line(12, 20, 14, 20);
        Line rightCross = new Line(26, 20, 28, 20);
        Line topCross = new Line(20, 12, 20, 14);
        Line bottomCross = new Line(20, 26, 20, 28);
        leftCross.setStroke(Color.BLACK);
        rightCross.setStroke(Color.BLACK);
        topCross.setStroke(Color.BLACK);
        bottomCross.setStroke(Color.BLACK);
        leftCross.setStrokeWidth(1.5);
        rightCross.setStrokeWidth(1.5);
        topCross.setStrokeWidth(1.5);
        bottomCross.setStrokeWidth(1.5);

        // Agregar todos los elementos al grupo
        crosshairGroup.getChildren().addAll(
                outerCircle, innerCircle, centerDot,
                horizontalLine2,horizontalLine,
                verticalLine,verticalLine2,
                leftCross, rightCross, topCross, bottomCross
        );

        return crosshairGroup;
    }

    public static Group createFire() {
        Group fireGroup = new Group();

        // Llama externa (roja, más picos hacia arriba con puntos adicionales)
        Polygon outerFlame = new Polygon();
        outerFlame.getPoints().addAll(
                12.0, 19.0, 12.0, 26.0, 14.0, 32.0, 12.0, 28.0, 12.0, 23.0,
                14.0, 19.0, 16.0, 16.75, 18.5, 14.25, 21.5, 12.75, 25.0, 10.5,
                28.5, 10.5, 25.0, 14.25, 23.25, 16.75, 23.25, 21.5, 25.0, 24.5,
                28.5, 26.0, 28.5, 21.5, 30.0, 24.5, 32.25, 28.0, 32.25, 32.0,
                32.25, 38.5, 30.0, 42.0, 25.0, 44.0, 18.5, 44.0, 12.0, 44.0,
                8.0, 42.0, 4.0, 38.5, 2.25, 32.0, 4.0, 28.0, 6.0, 24.5, 8.0, 21.5
        );
        outerFlame.setFill(Color.RED);

        // Llama intermedia (naranja, con más picos hacia arriba)
        Polygon middleFlame = new Polygon();
        middleFlame.getPoints().addAll(
                20.0, 30.0, 14.0, 26.0, 9.75, 23.75, 9.75, 20.5, 9.75, 16.0,
                11.75, 14.0, 14.0, 18.0, 16.0, 14.0, 22.0, 6.5, 22.0, 10.0,
                22.0, 14.0, 26.0, 16.0, 29.5, 18.0, 29.5, 22.0, 27.5, 26.0,
                24.5, 27.75, 20.0, 30.0
        );
        middleFlame.setFill(Color.ORANGE);
        middleFlame.setLayoutY(11.0);
        middleFlame.setLayoutX(-2.0);

        // Llama interna (amarilla, con picos definidos)
        Polygon innerFlame = new Polygon();
        innerFlame.getPoints().addAll(
                20.0, 26.0, 18.0, 24.0, 16.0, 22.0, 18.0, 20.0, 20.0, 18.0,
                22.0, 20.0, 24.0, 22.0, 22.0, 24.0, 20.0, 26.0
        );
        innerFlame.setFill(Color.YELLOW);
        innerFlame.setLayoutY(10.0);
        innerFlame.setLayoutX(-2.0);

        // Agregar las llamas al grupo
        fireGroup.getChildren().addAll(outerFlame, middleFlame, innerFlame);

        return fireGroup;
    }

}
