
import java.util.Scanner;
public class NPuzzle {
    private Tiles tiles;

    public NPuzzle(Tiles t ){

        tiles = t;
    }
    public void play() throws ConfigurationFormatException {
        System.out.println("Please mention the size of the board,- 3x3, 4x4, 5x5");
        Scanner n = new Scanner(System.in);
        String userConf = n.next();
        Configuration c = new Configuration(userConf);
        c.setSize(Integer.parseInt(userConf));
        System.out.println("Please Enter: RIGHT to Move the empty tile to Right");
        System.out.println("Please Enter: LEFT to Move the empty tile to Left");
        System.out.println("Please Enter: UP to Move the empty tile Up");
        System.out.println("Please Enter: DOWN to Move the empty tile Down");
        System.out.println("Please Enter: Q to Quit The Game");
                print(tiles.getSize());
        Scanner zvo = new Scanner(System.in);
        boolean continuation = tiles.isSolved();
        while(!continuation){
            String user = zvo.next();
            Tiles.Direction direction = Tiles.Direction.valueOf(user);
            tiles.move(direction);
            print(tiles.getSize());
            continuation = tiles.isSolved();
        }
        System.out.println("Congratulations You Won!!!!!!");
        System.exit(0);
        //here we use method isSolved to see after every user move, if he or she won.
        //then if he or she did not win we use the method move and print to give a user another move
        // and after the move print method prints the resulting board on the screen.
    }
    public void print(int size){
        if(size == 4) {
            System.out.println("-- " + tiles.getMoveCount() + " moves");
            System.out.println("- - - - - - - - - - - - - - - - - ");
            for (int i = 0; i < tiles.getSize(); i++) {
                for (int j = 0; j < tiles.getSize(); j++) {
                    System.out.print("|");
                    if (tiles.getTile(i, j) == Tiles.EMPTY)
                        System.out.print("  " + " \t ");
                    else
                        System.out.print("  " + tiles.getTile(i, j) + " \t ");
                }
                System.out.print("|");
                System.out.println("\n- - - - - - - - - - - - - - - - - ");
            }
        }
        else if(size == 3){
            System.out.println("-- " + tiles.getMoveCount() + " moves");
            System.out.println("- - - - - - - - - - - - -");
            for (int i = 0; i < tiles.getSize(); i++) {
                for (int j = 0; j < tiles.getSize(); j++) {
                    System.out.print("|");
                    if(tiles.getTile(i,j) == Tiles.EMPTY)
                        System.out.print("  " + " \t ");
                    else
                        System.out.print("  " + tiles.getTile(i,j) + " \t ");
                }
                System.out.print("|");
                System.out.println("\n- - - - - - - - - - - - -");
            }
        }
        else if(size == 5){
            System.out.println("-- " + tiles.getMoveCount() + " moves");
            System.out.println("- - - - - - - - - - - - - - - - - - - - -");
            for (int i = 0; i < tiles.getSize(); i++) {
                for (int j = 0; j < tiles.getSize(); j++) {
                    System.out.print("|");
                    if(tiles.getTile(i, j) == Tiles.EMPTY)
                        System.out.print("  " + " \t ");
                    else
                        System.out.print("  " + tiles.getTile(i, j) + " " + "\t" + " ");
                }
                System.out.print("|");
                System.out.println("\n- - - - - - - - - - - - - - - - - - - - -");
            }
        }
    }
    public static void main(String[] args){
        try {
            Tiles t;
            if (args[0].equalsIgnoreCase("--matrix")) {
                t = new MatrixTiles(args[1]);
            } else if (args[0].equalsIgnoreCase("--array")) {
                t = new ArrayTiles(args[1]);
            } else
                t = new MatrixTiles(args[0]);
            NPuzzle np = new NPuzzle(t);
            //if(!t.isSolvable()){
            //    System.out.println("Initial Configuration Is Not Solvable Please Change it");
            //    System.exit(0);
           // }
            np.play();
        }
        catch (ConfigurationFormatException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch (InvalidConfigurationException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
