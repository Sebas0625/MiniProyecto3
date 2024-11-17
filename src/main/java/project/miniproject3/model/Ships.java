package project.miniproject3.model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;

public class Ships {

    public static Group aircraft() {
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


    public static Group destructor() {
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

//        Line line4 = new Line(-95.0, -100.0, -100.0, -100.0);
//        line4.setLayoutX(347.5);
//        line4.setLayoutY(172.0);
//        line4.setRotate(90.0);
//        line4.setStroke(Color.web("#353535"));
//        line4.setStrokeWidth(2.0);
//
//        Line line5 = new Line(-96.0, -100.0, -100.0, -100.0);
//        line5.setLayoutX(348.0);
//        line5.setLayoutY(172.0);
//        line5.setRotate(90.0);
//        line5.setStroke(Color.web("#353535"));

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


}
