package com.mmks;

public class Cell {

	// Live or Dead
	private boolean isAlive;

	// Position of the cell
	private Position cellPosition;

	// Positions of 8 neighbor cells
	private Position[] neighbours;

	public Cell() {
		super();
		neighbours = new Position[8];
	}

	public Position getCellPosition() {
		return cellPosition;
	}

	public void setCellPosition(Position cellPosition) {
		this.cellPosition = cellPosition;
	}

	public Position[] getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(Position[] neighbours) {
		this.neighbours = neighbours;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	@Override
	public String toString() {
		if (isAlive) {
			return "[" + cellPosition.getX() + "," + cellPosition.getY() + "]";
		} else {
			return "";
		}
	}

}
