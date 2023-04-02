/**
 * Stefan Perkovic
 * Solves the given maze using DFS or BFS
 * @version 4/1/2023
 */
import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        Stack<MazeCell> reverse = new Stack<MazeCell>();
        MazeCell currentCell = maze.getEndCell();
        while (currentCell != maze.getStartCell()){
            reverse.push(currentCell);
            currentCell = currentCell.getParent();
        }
        reverse.push(maze.getStartCell());
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        while(reverse.isEmpty()== false){
            solution.add(reverse.pop());
        }

        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        MazeCell currentCell = maze.getStartCell();
        MazeCell end = maze.getEndCell();
        Stack<MazeCell> toExplore = new Stack<MazeCell>();
        /**
         * Explores neighboring cells in order North, East, South, West
         * Tracks path to the maze end
         */
        while(currentCell != end){
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            if (maze.isValidCell(row - 1, col)) {
                MazeCell nextCell = maze.getCell(row - 1, col);
                toExplore.push(nextCell);
                editCell(nextCell, currentCell);
            }
            if (maze.isValidCell(row, col + 1)){
                MazeCell nextCell = maze.getCell(row, col + 1);
                toExplore.push(nextCell);
                editCell(nextCell, currentCell);
            }
            if (maze.isValidCell(row + 1, col)){
                MazeCell nextCell = maze.getCell(row + 1, col);
                toExplore.push(nextCell);
                editCell(nextCell, currentCell);
            }
            if (maze.isValidCell(row, col - 1)) {
                MazeCell nextCell = maze.getCell(row, col - 1);
                toExplore.push(nextCell);
                editCell(nextCell, currentCell);
            }
            currentCell = toExplore.pop();
        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        MazeCell currentCell = maze.getStartCell();
        MazeCell end = maze.getEndCell();
        Queue<MazeCell> toExplore = new LinkedList<MazeCell>();
        /**
         * Explores neighboring cells in order North, East, South, West
         * Tracks path to the maze end
         */
        while(currentCell != end){
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            if (maze.isValidCell(row - 1, col)) {
                MazeCell nextCell = maze.getCell(row - 1, col);
                toExplore.add(nextCell);
                editCell(nextCell, currentCell);
            }
            if (maze.isValidCell(row, col + 1)){
                MazeCell nextCell = maze.getCell(row, col + 1);
                toExplore.add(nextCell);
                editCell(nextCell, currentCell);
            }
            if (maze.isValidCell(row + 1, col)){
                MazeCell nextCell = maze.getCell(row + 1, col);
                toExplore.add(nextCell);
                editCell(nextCell, currentCell);
            }
            if (maze.isValidCell(row, col - 1)) {
                MazeCell nextCell = maze.getCell(row, col - 1);
                toExplore.add(nextCell);
                editCell(nextCell, currentCell);
            }
            currentCell = toExplore.remove();
        }
        return getSolution();
    }

    /**
     * Sets the parent and marks the cell explored
     */
    public void editCell(MazeCell nextCell, MazeCell currentCell){
        nextCell.setParent(currentCell);
        nextCell.setExplored(true);
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
