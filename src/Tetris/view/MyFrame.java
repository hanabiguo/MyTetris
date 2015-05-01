package Tetris.view;

import javax.swing.JFrame;

import Tetris.Globle.Globle;

public class MyFrame extends JFrame {

	public MyFrame(String title){
		super(title);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(300,100);
        setSize(Globle.GRID_L*Globle.WIDTH+15,Globle.GRID_L*Globle.LENGTH+37); 
        
	}
}
