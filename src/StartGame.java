import java.util.Scanner;

/**
 * Starts the game with 4 alive cells
 * @author Tyler Becker
 */
public class StartGame {

    public static void main(String[] args) {
        BaseGame game = new BaseGame();

        //Sets 4 cells to "alive" for the game to allow growth and death
        game.map[3][3] = 1;
        game.map[3][2] = 1;
        game.map[3][4] = 1;
        game.map[2][2] = 1;
        game.printMap();

        Scanner scanner = new Scanner(System.in); //Reads console input
        while(true){ //Runs until the user tells the program to stop
            System.out.println("Do you wish to continue?");
            if(scanner.next().equals("no")){
                break;
            }
            else{
                game.grow(); //Grows the map
                game.printMap(); //Prints the new iteration of the map
            }
        }
    }
}
