import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InitMazeCreator extends Application
{
	private final double windowWidth = 1440;
	private final double windowHeight = 988;
	private final int x = 50;
	private final int y = 50;
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		Group layout = new Group();	
		primaryStage.setTitle("Maze Creator");
		Scene scene = new Scene(layout, windowWidth, windowHeight);
		primaryStage.setScene(scene);	
		
		double sizex = windowWidth/x;
		double sizey = windowHeight/y;
		Grid grid = new Grid(x, y, sizex, sizey, layout);
		grid.generateMaze();
		
		primaryStage.show();
	}
	
	public static void main(String[] args)
	{			
		launch(args);
	}
}
