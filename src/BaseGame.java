import java.util.*;

/**
 * This program is a simple example for Conway's Game of Life. Each cell will stay the same, grow, or die depending on the number of alive cells around it
 * @author Tyler Becker
 */
public class BaseGame {

    int mapSize;
    int[][] map;

    /**
     * Initializes the map
     */
    public BaseGame(){
        mapSize = 5;
        map = new int[mapSize][mapSize];

        for(int i = 0; i < map.length; i++){
            for(int e = 0; e < map[i].length; e++){
                map[i][e] = 0;
            }
        }
    }

    /**
     * Prints the map out as text in the console
     */
    public void printMap(){
        for(int i = 0; i < map.length; i++){
            for(int e = 0; e < map[i].length; e++){
                System.out.print(" " + map[i][e] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Finds all the neighbors to the given cell
     * @param x - gives the X value of the desired cell
     * @param y - gives the Y value of the desired cell
     * @return - the total number of alive neighbors around the desired cell
     */
    public int findNeighbors(int x, int y){
        ArrayList<Integer> neighbors = new ArrayList<>();

        if(inbounds(x-1) && inbounds(y-1)){
            neighbors.add(map[x-1][y-1]);
        }
        if(inbounds(x) && inbounds(y-1)){
            neighbors.add(map[x][y-1]);
        }
        if(inbounds(x+1) && inbounds(y-1)){
            neighbors.add(map[x+1][y-1]);
        }
        if(inbounds(x-1) && inbounds(y)){
            neighbors.add(map[x-1][y]);
        }
        if(inbounds(x+1) && inbounds(y)){
            neighbors.add(map[x+1][y]);
        }
        if(inbounds(x-1) && inbounds(y+1)){
            neighbors.add(map[x-1][y+1]);
        }
        if(inbounds(x) && inbounds(y+1)){
            neighbors.add(map[x][y+1]);
        }
        if(inbounds(x+1) && inbounds(y+1)){
            neighbors.add(map[x+1][y+1]);
        }

        return numberOfAliveNeighbors(neighbors);
    }

    /**
     * Checks if a value is valid
     * @param coordinate - the X or Y value that wants to be checked
     * @return - true or false depending on if the value is > 0 or < map size
     */
    public boolean inbounds(int coordinate){
        return coordinate >= 0 && coordinate < map.length;
    }

    /**
     * Returns the count for number of neighbors that are alive
     * @param neighbors - the alive/death status of all neighbors in an ArrayList
     * @return - number of 1's (Alive) cells
     */
    public int numberOfAliveNeighbors(ArrayList<Integer> neighbors){
        int counter = 0;
        for (Integer neighbor : neighbors) {
            if (neighbor.equals(1)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Causes the cells to die or grow based on the number of neighbors.
     */
    public void grow(){
        //Keeps track of the X and Y of cells that can grow
        Stack<Integer> birthX = new Stack<>();
        Stack<Integer> birthY = new Stack<>();
        //Keeps track of the X and Y of cells that need to die
        Stack<Integer> killX = new Stack<>();
        Stack<Integer> killY = new Stack<>();

        //Iterates through the whole map
        for(int i = 0; i < map.length; i++){
            for(int e = 0; e < map[i].length; e++){ //Goes through the whole map
                int counter = findNeighbors(i,e); //Finds number of alive neighbors
                boolean alive = false; //Keeps track if the current element is alive or not

                //Check if current cell is alive
                if(map[i][e] == 1){
                    alive = true;
                }

                if(!alive){ //if the current cell is dead, check if it has 3 neighbors to become alive
                    if(counter == 3){
                        //Add current cell to the X and Y birth stacks
                        birthX.push(i);
                        birthY.add(e);
                    }
                }else{ //if the current cell is alive, check if it has 2 or 3 neighbors to survive, else kill it
                    if(counter < 2 || counter > 3){
                        //Add current cell to the X and Y kill stacks
                        killX.push(i);
                        killY.add(e);
                    }

                }
            }
        }

        //Kill every cell in the stacks
        while(!killX.isEmpty()){
            map[killX.pop()][killY.pop()] = 0;
        }
        //Birth every cell in the stacks
        while(!birthX.isEmpty()){
            map[birthX.pop()][birthY.pop()] = 1;
        }

    }
}
