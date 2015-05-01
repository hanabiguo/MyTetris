package main;

import Tetris.view.*;
import Tetris.entities.*;
import Tetris.listener.JFrameListener;
import Tetris.controller.*;

class Main 
{
	public static void main(String[] args) 
	{
		ShapeFactory shapeFactory = new ShapeFactory();
		
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel(ground);
		Controller controller = new Controller(shapeFactory, ground, gamePanel);

		MyFrame frame = new MyFrame("MyTetris");
		frame.addWindowListener(new JFrameListener());
		frame.addKeyListener(controller);
		frame.add(gamePanel);
		frame.setVisible(true);

		controller.newGame();
		
	}
}
