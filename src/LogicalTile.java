import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class LogicalTile
{
	private int x;
	private int y;
	private Rectangle rectangle;
	private Line[] walls = new Line[4];
	private boolean visited;
	private boolean leftW;
	private boolean rightW;
	private boolean upW;
	private boolean downW;
	
	public LogicalTile(int i, int j, double sizex, double sizey, Group layout)
	{
		x = i;
		y = j;
		visited = false;
		leftW = true;
		rightW = true;
		upW = true;
		downW = true;
		
		//top
		walls[0] = new Line(x*sizex, y*sizey, (x + 1)*sizex, y*sizey);
		
		//bottom
		walls[1] = new Line(x*sizex, (y + 1)*sizey, (x + 1)*sizex, (y + 1)*sizey);
		
		//left
		walls[2] = new Line(x*sizex, y*sizey, x*sizex, (y + 1)*sizey);
		
		//right
		walls[3] = new Line((x + 1)*sizex, y*sizey, (x + 1)*sizex, (y + 1)*sizey);

		//rectangle
		rectangle = new Rectangle(x*sizex, y*sizey, sizex, sizey);
        rectangle.setStroke(Color.LIGHTBLUE);
        rectangle.setFill(Color.LIGHTBLUE);
        
        layout.getChildren().addAll(rectangle);
        
        for(int k = 0; k < 4; k++)
        {
        	walls[k].setStroke(Color.BLACK);
        	walls[k].setStrokeWidth(5);
        	layout.getChildren().addAll(walls[k]);
        }
	}
	
	public void setVisit(boolean flag)
	{
		visited = flag;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean isVisited()
	{
		return visited;
	}
	
	public void triggerVisited()
	{
		visited = !visited;
	}
	
	public void triggerLeftW()
	{
		leftW = !leftW;
	}
	
	public void triggerRightW()
	{
		rightW = !rightW;
	}
	
	public void triggerUpW()
	{
		upW = !upW;
	}
	
	public void triggerDownW()
	{
		downW = !downW;
	}
	
	public void changeColor(Color stroke, Color fill)
	{
		rectangle.setStroke(stroke);
		rectangle.setFill(fill);
	}
	
	public void markTile()
	{
		changeColor(Color.PURPLE, Color.PURPLE);
	}
	
	public Line[] getWalls()
	{
		return walls;
	}
}
