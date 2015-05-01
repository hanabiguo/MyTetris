package Tetris.entities;

import java.awt.Graphics;
import java.awt.Color;

import Tetris.listener.ShapeListener;
import Tetris.Globle.Globle;

public class Shape 
{
	private int[][] body;
	private int status=0, X=Globle.SET_MID, Y=-1;
	private Color c;

	private ShapeListener l;

	public final static int LEFT=0;
	public final static int RIGHT=1;
	public final static int DOWN=2;
	public final static int ROTATE=3;

	public Shape(int[][] body, Color c, ShapeListener l)
	{
		this.c = c;
		this.body = body;
		this.l = l;
		new Thread(new shapeDriver()).start();
	}

	public int getX()
	{
		return this.X;
	}

	public int getY()
	{
		return this.Y;
	}

	public Color getColor()
	{
		return this.c;
	}
	public void rotate()
	{
		this.status = (status+1)%body.length;
		//System.out.println("旋转"+this.status);
	}
	public void moveLeft()
	{
		X--;
		//System.out.println("向左");
	}
	public void moveRight()
	{
		X++;
		//System.out.println("向右");
	}
	public void moveDown()
	{
		Y++;
		//System.out.println("向下");
	}
	public boolean isMember(boolean rotate,int x, int y)
	{
		int tempStatus = status;
		if(rotate)
			tempStatus = (status+1)%body.length;
		return body[tempStatus][y*4+x]==1;
	}
	private boolean drawFlag(int status,int x, int y)
	{
		return body[status][y*4+x]==1;
	}
	public void DrawThis(Graphics g)
	{
		//System.out.println("绘出shape");
		g.setColor(c);
		for(int x=0;x<4;x++)
		{
			for(int y=0;y<4;y++)
			{
				if(drawFlag(status,x,y))
				{	
					g.fill3DRect(Globle.GRID_L*(x+X), Globle.GRID_L*(y+Y), Globle.GRID_L, Globle.GRID_L, true);
				}
			}
		}
	}

	class shapeDriver implements Runnable
	{
		public void run()
		{
			while(l.ifShapeDownable(Shape.this))
			{
				if(Globle.pause) continue;
				moveDown();
				l.shapeMovedDown(Shape.this);
				try
				{
					Thread.sleep(Globle.downTime);
				}
				catch (InterruptedException ie)
				{
					System.out.println(ie);
				}
				
			}
		}
	}

}
