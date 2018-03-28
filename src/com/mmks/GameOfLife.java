package com.mmks;

import java.util.Arrays;
import java.util.Scanner;

public class GameOfLife {

	private static int GRID_WIDTH = 200;
	private static int GRID_HEIGHT = 200;

	private static Cell cellgrid[][];

	public static void main(String[] args) {
		// Initialize the grid
		cellgrid = new Cell[GRID_WIDTH][GRID_HEIGHT];
		intializeGridCells(cellgrid);

		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		String[] cellPositions = line.split("\\|");

		System.out.println("0: " + Arrays.toString(cellPositions));

		// "5,5|6,5|7,5|5,6|6,6|7,6"

		int liveCells[][] = new int[cellPositions.length][2];

		for (int i = 0; i < cellPositions.length; i++) {
			String currCell = cellPositions[i];
			String[] corrds = currCell.split(",");

			try {
				liveCells[i][0] = Integer.parseInt(corrds[0].trim());
				liveCells[i][1] = Integer.parseInt(corrds[1].trim());
			} catch (Exception e) {

			}
		}

		for (int i = 0; i < liveCells.length; i++) {
			int liveCellX = liveCells[i][0];
			int liveCellY = liveCells[i][1];

			cellgrid[liveCellX][liveCellY].setAlive(true);				
		}

		for (int k = 0; k < 100; k++) {
			System.out.print(k + 1 + ": ");
			for (int i = 0; i < GRID_WIDTH; i++) {
				for (int j = 0; j < GRID_HEIGHT; j++) {
					// Game of life one cycle
					gameOfLife(cellgrid[i][j]);
					System.out.print(cellgrid[i][j]);
				}
			}
			System.out.println();
		}
	}

	private static void intializeGridCells(Cell[][] cellgrid) {
		// Initialize cells
		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_HEIGHT; j++) {
				cellgrid[i][j] = new Cell();
				cellgrid[i][j].setCellPosition(new Position(i, j));

				// Calculate neighbours and their positions
				calculateNeighbourPositions(cellgrid[i][j]);
			}
		}
	}

	public static int getLiveNeighbourCount(Cell cell) {
		int liveCount = 0;
		for (int i = 0; i < cell.getNeighbours().length; i++) {
			Position neighbourPosition = cell.getNeighbours()[i];
			if ((neighbourPosition != null) && (cellgrid[neighbourPosition.getX()][neighbourPosition.getY()].isAlive())) {
				liveCount++;
			}
		}
		return liveCount;
	}

	public static void gameOfLife(Cell cell) {
		int liveNeighbours = getLiveNeighbourCount(cell);

		if (cell.isAlive()) {
			if (liveNeighbours < 2) {
				cell.setAlive(false);
			} else if (liveNeighbours == 2 || liveNeighbours == 3) {
				cell.setAlive(true);
			} else if (liveNeighbours > 3) {
				cell.setAlive(false);
			}
		} else if (liveNeighbours == 3) {
			cell.setAlive(true);
		}

	}

	public static void calculateNeighbourPositions(Cell cell) {
		int cellX = cell.getCellPosition().getX();
		int cellY = cell.getCellPosition().getY();

		int topY = -1;
		int bottomY = -1;
		int leftX = -1;
		int rightX = -1;

		// Top
		if ((cellY - 1) >= 0) {
			topY = cellY - 1;
		}

		// Bottom
		if ((cellY + 1) < (GRID_HEIGHT - 1)) {
			bottomY = cellY + 1;
		}

		// Left
		if ((cellX - 1) >= 0) {
			leftX = cellX - 1;
		}

		// Right
		if ((cellX + 1) < GRID_WIDTH) {
			rightX = cellX + 1;
		}

		// Neighbor positions - Clockwise - T TR R RB B BL L LT
		if (cellX >= 0 && topY >= 0) {
			cell.getNeighbours()[0] = new Position(cellX, topY);
		} else {
			cell.getNeighbours()[0] = null;
		}
		if (rightX >= 0 && topY >= 0) {
			cell.getNeighbours()[1] = new Position(rightX, topY);
		} else {
			cell.getNeighbours()[1] = null;
		}
		if (rightX >= 0 && cellY >= 0) {
			cell.getNeighbours()[2] = new Position(rightX, cellY);
		} else {
			cell.getNeighbours()[2] = null;
		}
		if (rightX >= 0 && bottomY >= 0) {
			cell.getNeighbours()[3] = new Position(rightX, bottomY);
		} else {
			cell.getNeighbours()[3] = null;
		}
		if (cellX >= 0 && bottomY >= 0) {
			cell.getNeighbours()[4] = new Position(cellX, bottomY);
		} else {
			cell.getNeighbours()[4] = null;
		}
		if (leftX >= 0 && bottomY >= 0) {
			cell.getNeighbours()[5] = new Position(leftX, bottomY);
		} else {
			cell.getNeighbours()[5] = null;
		}
		if (leftX >= 0 && cellY >= 0) {
			cell.getNeighbours()[6] = new Position(leftX, cellY);
		} else {
			cell.getNeighbours()[6] = null;
		}
		if (leftX >= 0 && topY >= 0) {
			cell.getNeighbours()[7] = new Position(leftX, topY);
		} else {
			cell.getNeighbours()[7] = null;
		}
	}

}
