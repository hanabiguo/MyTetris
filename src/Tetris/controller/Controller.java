package Tetris.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import Tetris.view.*;
import Tetris.Globle.Globle;
import Tetris.entities.*;
import Tetris.listener.*;

public class Controller extends KeyAdapter implements ShapeListener
{
	private Shape shape;
	private ShapeFactory shapeFactory;
	private GamePanel gamePanel;
	private static Ground ground;
	private int Lines;


	public Controller(ShapeFactory shapeFactory, Ground ground, GamePanel gamePanel)
	{
		this.shapeFactory = shapeFactory;
		this.ground = ground;
		this.gamePanel = gamePanel;
	}

	public void shapeMovedDown(Shape shape)
	{
		//System.out.println("shapedmd"+ground==null);
		gamePanel.display(ground, shape);
	}

	public boolean ifShapeDownable(Shape shape)
	{
		//if(this.shape!=shape)
		//	return false;
		if (ground.isMoveable(shape,Shape.DOWN))
			return true;
		else 
		{
			int lines = ground.acceptShape(shape);
			Lines += lines;
			compute(lines);
			gamePanel.display(ground, shape);
			setSpeed();
			this.shape = shapeFactory.getShape(this);
			return false;
		}
	}
	public void compute(int lines)
	{
		int addPoints = 0;
		switch(lines)
		{
			case 1: addPoints = 50;break;
			case 2: addPoints = 120;break;
			case 3: addPoints = 180;break;
			case 4: addPoints = 300;break;
		}
		gamePanel.AddPoints(addPoints);
	}
	public void setSpeed()
	{
		if(Globle.downTime<=250) return;
		if(Lines>=6)
		{
			Globle.downTime -= 50;
			Lines -= 6;
		}
		
	}

	public void newGame()
	{
		shape = shapeFactory.getShape(this);
		//System.out.println("newgame"+ground==null);
		gamePanel.display(ground, shape);
	}

	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
				if(ground.isMoveable(shape,Shape.RIGHT))
					shape.moveRight();break;
			case KeyEvent.VK_LEFT:
				if(ground.isMoveable(shape,Shape.LEFT))
					shape.moveLeft();break;
			case KeyEvent.VK_UP:
				if(ground.isMoveable(shape,Shape.ROTATE))
					shape.rotate();break;
			case KeyEvent.VK_DOWN:
				if(ground.isMoveable(shape,Shape.DOWN))
					shape.moveDown();break;
		}
		gamePanel.display(ground, shape);
	}
	public static void pause(){
		Globle.pause = true;
	}
	//continue
	public static void cntn(){
		Globle.pause = false;
	}

	public static void gameover() {
		int select = JOptionPane.showConfirmDialog(null, "游戏即将关闭，要重来吗", "游戏结束", JOptionPane.YES_NO_OPTION);
		if(select==1) System.exit(0);
		else restart();
	}
	public static void restart(){
		Ground.clean();
		Globle.points = 0;
	}
}


