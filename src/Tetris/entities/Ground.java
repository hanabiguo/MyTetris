package Tetris.entities;

import java.awt.Graphics;
import java.awt.Color;

import Tetris.Globle.Globle;
import Tetris.controller.Controller;
import Tetris.entities.Shape;

public class Ground 
{
	//障碍位置和颜色
	private static int[][] obstruct = new int[Globle.WIDTH][Globle.LENGTH];
	private static int[][] colors = new int[Globle.WIDTH][Globle.LENGTH];

	//返回消除行数
	public int acceptShape(Shape shape)
	{
		//System.out.println("接收shape");
		for(int x=0;x<4;x++)
		{
			for(int y=0;y<4;y++)
			{
				if(shape.isMember(false,x,y))
				{
					if(shape.getY()+y<0){
						Controller.gameover();
						return 0;
					}
					obstruct[shape.getX()+x][shape.getY()+y]=1;
					colors[shape.getX()+x][shape.getY()+y]=shape.getColor().getRGB();
				}
			}
		}
		return delFullLine();
	}
	//消行，返回消除行数
	private int delFullLine()
	{
		int lines = 0;
		for(int y=Globle.LENGTH-1;y>=0;y--)
		{
			boolean full = true;
			for(int x=0;x<Globle.WIDTH;x++)
			{
				//System.out.println(x+"  "+y);
				if(obstruct[x][y]==0)
					full = false;
			}
			if(full)
			{
				//System.out.println(y);
				delLine(y);
				y++;
				lines++;
			}
		}
		return lines;
	}
	private void delLine(int l)
	{
		for(int y=l;y>0;y--)
		{
			for(int x=0;x<Globle.WIDTH;x++)
			{
				//System.out.println(x+"  "+y);
				obstruct[x][y] = obstruct[x][y-1];
				colors[x][y] = colors[x][y-1];
			}
		}
		for(int x=0;x<Globle.WIDTH;x++)
		{
			obstruct[x][0] = 0;
			colors[x][0] = Color.LIGHT_GRAY.getRGB();
		}
	}
	public void DrawThis(Graphics g)
	{
		//System.out.println("绘出ground");
		for(int x=0;x<Globle.WIDTH;x++)
		{
			for(int y=0;y<Globle.LENGTH;y++)
			{
				if(obstruct[x][y]==1)
				{
					g.setColor(new Color(colors[x][y]));
					g.fill3DRect(x*Globle.GRID_L, y*Globle.GRID_L, Globle.GRID_L, Globle.GRID_L,true);
				}
			}
		}
	}

	public boolean isMoveable(Shape shape, int action)
	{
		int X = shape.getX();
		int Y = shape.getY();

		switch(action)
		{
			case Shape.LEFT :X--;break;
			case Shape.RIGHT :X++;break;
			case Shape.DOWN :Y++;break;
		}

		for(int x=0;x<4;x++)
		{
			for(int y=0;y<4;y++)
			{
				if(shape.isMember(action==Shape.ROTATE,x,y))
				{
					if((X+x) >= Globle.WIDTH||(X+x)<0||(Y+y)>=Globle.LENGTH||obstruct[X+x][Y+y]==1)
						return false;
				}
			}
		}
		return true;
	}
	public static void clean(){
		for(int i=0;i<Globle.WIDTH;i++){
			for(int j=0;j<Globle.LENGTH;j++){
				obstruct[i][j] = 0;
				colors[i][j] = 0;
			}
		
		}
	}
}
