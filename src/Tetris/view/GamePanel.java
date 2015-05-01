package Tetris.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Tetris.Globle.Globle;
import Tetris.entities.Ground;
import Tetris.entities.Shape;

public class GamePanel extends JPanel
{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private Ground ground;
	private Shape shape;
	
	public GamePanel(Ground ground)
	{
		this.ground = ground;
		this.setSize(Globle.WIDTH*Globle.GRID_L,Globle.LENGTH*Globle.GRID_L);
	}
	
	public void AddPoints(int add)
	{
		Globle.points += add;
	}

	public void display(Ground ground, Shape shape)
	{
		//System.out.println("display");
		//System.out.println(this.ground==null);
		if(ground!=null)
			this.ground = ground;
		if(shape!=null)
			this.shape = shape;
		this.repaint();
	}
	public void paintComponent(Graphics g)
	{
		//System.out.println("paint");
		g.setColor(new Color(0xdadada));
		//g.fillRect(0,0,Globle.GRID_L*Globle.WIDTH, Globle.GRID_L*Globle.LENGTH);
		
		for(int x=0;x<Globle.WIDTH;x++)
		{
			for(int y=0;y<Globle.LENGTH;y++)
			{
				g.fill3DRect(x*Globle.GRID_L,y*Globle.GRID_L,Globle.GRID_L,Globle.GRID_L,true);
			}
		}
		
		if(ground!=null)
		ground.DrawThis(g);
		if(shape!=null)
		shape.DrawThis(g);
		
		g.setColor(new Color(0));
		g.drawString("ตรทึ:" + Globle.points, 180, 20);
	}
}
