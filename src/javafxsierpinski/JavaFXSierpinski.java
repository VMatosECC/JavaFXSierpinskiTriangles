/*
 This app uses JavaFX to draw Sierpinski triangles.
 A Sierpinski triangle is a fractal with the overall shape 
 of an equilateral triangle, subdivided recursively into smaller 
 equilateral triangles. This is an example of a SELF-SIMILAR set. 
 That is, it is a mathematically generated pattern that is 
 reproducible at any magnification or reduction (Wiki). 
*/
package javafxsierpinski;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class JavaFXSierpinski extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.setTitle("Sierpinski Triangles");
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
