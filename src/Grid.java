import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Grid
{
	
	private int width;
	private int height;
	private LogicalTile[][] gridArray;
	private Stack<LogicalTile> stk;
	
	public Grid(int x, int y, double sizex, double sizey, Group layout)
	{
		width = x;
		height = y;
		
		gridArray = new LogicalTile[width][height];
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				gridArray[i][j] = new LogicalTile(i, j, sizex, sizey, layout);
			}
		}
		
		stk = new Stack<LogicalTile>();
	}

	public LogicalTile getRandNeighbor(int x, int y)
	{
		ArrayList<LogicalTile> neighbors = new ArrayList<LogicalTile>();
		if(x < width - 1)
			if(!gridArray[x + 1][y].isVisited())
				neighbors.add(gridArray[x + 1][y]);
		if(x > 0)
			if(!gridArray[x - 1][y].isVisited())
				neighbors.add(gridArray[x - 1][y]);
		if(y < height - 1)
			if(!gridArray[x][y + 1].isVisited())
				neighbors.add(gridArray[x][y + 1]);
		if(y > 0)
			if(!gridArray[x][y - 1].isVisited())
				neighbors.add(gridArray[x][y - 1]);
		
		if(neighbors.size() == 0)
			return null;
		Random rand = new Random();
		int choice = rand.nextInt(neighbors.size());
			return neighbors.get(choice);
	}
	
	public void displayGridTerminal()
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				if(gridArray[i][j].isVisited())
					System.out.print("1  ");
				else
					System.out.print("0  ");
			}
			System.out.println();
		}
	}
	
	public void resetTiles()
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				if(gridArray[i][j].isVisited())
					gridArray[i][j].triggerVisited();
			}
		}
	}
	
	public void breakWall(LogicalTile current, LogicalTile next)
	{
		int x = current.getX() - next.getX();
		  if (x == 1) 
		  {
		    current.getWalls()[2].setStroke(Color.PURPLE);
		    next.getWalls()[3].setStroke(Color.PURPLE);
		    
		  } else if (x == -1) 
		  {
		    current.getWalls()[3].setStroke(Color.PURPLE);
		    next.getWalls()[2].setStroke(Color.PURPLE);
		  }
		  
		  int y = current.getY() - next.getY();
		  if (y == 1) 
		  {
			  current.getWalls()[0].setStroke(Color.PURPLE);
			  next.getWalls()[1].setStroke(Color.PURPLE);
		  } else if (y == -1) 
		  {
			  current.getWalls()[1].setStroke(Color.PURPLE);
			  next.getWalls()[0].setStroke(Color.PURPLE);
		  }
	}
	
	public void generateMaze() throws Exception
	{	
		new AnimationTimer() 
		{
			LogicalTile current = gridArray[0][0];
			LogicalTile next;
			
            public void handle(long currentNanoTime) 
            {         
            	if(!current.isVisited())
        			current.triggerVisited();
            	current.markTile();
            	
            	next = getRandNeighbor(current.getX(), current.getY());
            	if(next != null)
            	{
            		stk.push(current);
            		breakWall(current, next);
            		current = next;
            	}
            	else if(!stk.isEmpty())
                {
                	current = stk.pop();	
                }
        	}  
            
        }.start();
	}
	
}
