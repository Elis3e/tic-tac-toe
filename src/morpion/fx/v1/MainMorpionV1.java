package morpion.fx.v1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainMorpionV1 extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
	        primaryStage.setTitle("Morpions V1");
			FXMLLoader loader = new FXMLLoader() ;
            loader.setLocation(MainMorpionV1.class.getResource("morpionV1.fxml"));
            Parent root = (Parent) loader.load();
			Scene scene = new Scene(root,400,400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
