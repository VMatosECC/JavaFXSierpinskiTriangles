package javafxsierpinski;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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

//    @FXML
//    private Button btnDraw;

    @FXML
    private TextField txtOrder;
       
    @FXML
    private TextArea txtMessages;
    
    
    @FXML
    void btnClearClick(ActionEvent event) {
        borderPane.setCenter(null);
        txtOrder.setText("");
        txtMessages.setText("");
    }

    @FXML
    void btnDrawClick(ActionEvent event) {
        String strOrder = txtOrder.getText();
        if(strOrder.equals("")) {
            txtOrder.setText("0");
            strOrder = "0";
        }
        int order = 0 + Integer.valueOf(txtOrder.getText());
        System.out.println("Order is [" + strOrder + "]");
        if(order >= 7){
            txtMessages.setText(
                "CAUTION\nAn order  >= 7 is computationally very intensive. " 
              + "The app may take a long time to complete.");
        }
        
        
        int sideLength = 400;           // Polygon's base width & height

        Group root2 = new Group();

        double[] points = {             //For example:
            sideLength / 2, 0,          //P0 (200, 0)
            0, sideLength,              //P1 (0, 400)
            sideLength, sideLength      //P2 (400, 400)
        };

        Polygon triangle = new Polygon(points);
        drawSierpinski(root2, order, triangle);

       
        // Add the new Group to the center of the existing BorderPane
        borderPane.setCenter(root2);
    }
    
        private void drawSierpinski(Group group, int order, Polygon triangle) {

        // Set the poligon's attributes: fill color and stroke  
        triangle.setFill(Color.WHITE);          //Triangle color
        triangle.setStroke(Color.BLUE);         //Line color
        triangle.setStrokeWidth(2.0);           //Line width 

        if (order == 0) {
            
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

            drawSierpinski(group, order - 1, t1);
    
            drawSierpinski(group, order - 1, new Polygon(
                    points[0], points[1],
                    x0, y0,
                    x1, y1
            ));
    
            drawSierpinski(group, order - 1, new Polygon(
                    x1, y1,
                    points[4], points[5],
                    x2, y2
            ));
    
            drawSierpinski(group, order - 1, new Polygon(
                    x0, y0,
                    points[2], points[3],
                    x2, y2
            ));
        }
    }

}

