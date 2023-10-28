package javafxsierpinski;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class FXMLDocumentController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnDraw;

    @FXML
    private TextField txtOrder;

    @FXML
    void btnDrawClick(ActionEvent event) {
        String strOrder = txtOrder.getText();
        if(strOrder.equals("")) {
            txtOrder.setText("0");
            strOrder = "0";
        }
        int order = 0 + Integer.valueOf(txtOrder.getText());
        System.out.println("Order is [" + strOrder + "]");
        
        
                int depth = 2;          // Adjust the depth for more or fewer triangles
        int sideLength = 400;   // Polygon's base width & height

        Group root2 = new Group();
        Scene scene = new Scene(root2, sideLength + 10, sideLength + 10);
        stage.setTitle("Sierpinski Triangle - Order: " + depth);
        stage.setScene(scene);

        double[] points = {             //For example:
            sideLength / 2, 0,          //P0 (200, 0)
            0, sideLength,              //P1 (0, 400)
            sideLength, sideLength      //P2 (400, 400)
        };

        Polygon triangle = new Polygon(points);
        drawSierpinski(root2, depth, triangle);

        stage.show();
        
//        // Add the new Group to the center of the existing BorderPane
//        borderPane.setCenter(root2);
//        // Create a Scene with the existing BorderPane
//        Scene scene2 = new Scene(borderPane, 400, 400);
//
//        // Set the Scene on the Stage
//        stage.setScene(scene2);
//        stage.setTitle("Group in Existing BorderPane");
//        stage.show();
        
        
        
    }
    
        private void drawSierpinski(Group group, int depth, Polygon triangle) {

        // Set the poligon's attributes: fill color and stroke  
        triangle.setFill(Color.WHITE);          //Triangle color
        triangle.setStroke(Color.BLUE);         //Line color
        triangle.setStrokeWidth(2.0);     //Line width 

        if (depth == 0) {
            
            group.getChildren().add(triangle);
        } else {
            double[] points = new double[triangle.getPoints().size()];
            
            for (int i = 0; i < points.length; i++) {
                points[i] = triangle.getPoints().get(i);
            }

            //Points in between existing points
            double x0 = (points[0] + points[2]) / 2;
            double y0 = (points[1] + points[3]) / 2;
            
            double x1 = (points[0] + points[4]) / 2;
            double y1 = (points[1] + points[5]) / 2;
            
            double x2 = (points[2] + points[4]) / 2;
            double y2 = (points[3] + points[5]) / 2;
    
            Polygon t1 = new Polygon(x0, y0, x1, y1, x2, y2);

            drawSierpinski(group, depth - 1, t1);
    
            drawSierpinski(group, depth - 1, new Polygon(
                    points[0], points[1],
                    x0, y0,
                    x1, y1
            ));
    
            drawSierpinski(group, depth - 1, new Polygon(
                    x1, y1,
                    points[4], points[5],
                    x2, y2
            ));
    
            drawSierpinski(group, depth - 1, new Polygon(
                    x0, y0,
                    points[2], points[3],
                    x2, y2
            ));
        }
    }
    
    
    
    

}

//=====================================================================
/*
package com.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

//public class SierpinskiTriangle extends Application {
public class Main extends Application {
    @Override
    public void start(Stage stage) {

        int depth = 2;          // Adjust the depth for more or fewer triangles
        int sideLength = 400;   // Polygon's base width & height

        Group root = new Group();
        Scene scene = new Scene(root, sideLength + 10, sideLength + 10);
        stage.setTitle("Sierpinski Triangle - Order: " + depth);
        stage.setScene(scene);

        double[] points = {             //For example:
            sideLength / 2, 0,          //P0 (200, 0)
            0, sideLength,              //P1 (0, 400)
            sideLength, sideLength      //P2 (400, 400)
        };

        Polygon triangle = new Polygon(points);
        drawSierpinski(root, depth, triangle);

        stage.show();
    }

    private void drawSierpinski(Group group, int depth, Polygon triangle) {

        // Set the poligon's attributes: fill color and stroke  
        triangle.setFill(Color.WHITE);          //Triangle color
        triangle.setStroke(Color.BLUE);         //Line color
        triangle.setStrokeWidth(2.0);     //Line width 

        if (depth == 0) {
            
            group.getChildren().add(triangle);
        } else {
            double[] points = new double[triangle.getPoints().size()];
            
            for (int i = 0; i < points.length; i++) {
                points[i] = triangle.getPoints().get(i);
            }

            //Points in between existing points
            double x0 = (points[0] + points[2]) / 2;
            double y0 = (points[1] + points[3]) / 2;
            
            double x1 = (points[0] + points[4]) / 2;
            double y1 = (points[1] + points[5]) / 2;
            
            double x2 = (points[2] + points[4]) / 2;
            double y2 = (points[3] + points[5]) / 2;
    
            Polygon t1 = new Polygon(x0, y0, x1, y1, x2, y2);

            drawSierpinski(group, depth - 1, t1);
    
            drawSierpinski(group, depth - 1, new Polygon(
                    points[0], points[1],
                    x0, y0,
                    x1, y1
            ));
    
            drawSierpinski(group, depth - 1, new Polygon(
                    x1, y1,
                    points[4], points[5],
                    x2, y2
            ));
    
            drawSierpinski(group, depth - 1, new Polygon(
                    x0, y0,
                    points[2], points[3],
                    x2, y2
            ));
        }
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}




*/
