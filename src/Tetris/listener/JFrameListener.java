package Tetris.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import Tetris.controller.Controller;

public class JFrameListener extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {
		Controller.pause();
		int select = JOptionPane.showConfirmDialog(null, "确定关闭吗", "游戏暂停中", JOptionPane.YES_NO_OPTION);
		if(select==0) System.exit(0);
		else Controller.cntn();
	}

	
}
