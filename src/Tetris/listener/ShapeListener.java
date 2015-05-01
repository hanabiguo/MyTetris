package Tetris.listener;

import Tetris.entities.Shape;

public interface ShapeListener
{
	public void shapeMovedDown(Shape shape);

	public boolean ifShapeDownable(Shape shape);
}