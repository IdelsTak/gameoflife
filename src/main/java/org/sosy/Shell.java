package org.sosy;

import java.io.IOException;
import java.util.Scanner;
import org.sosy.model.Game;
import org.sosy.model.Grid;

/**
 * This class provides the utility to let a user play the Game of Life
 * application interactively on a shell.
 */
class Shell {

    private static final String PROMPT = "gol";
    private static final String ERROR = "Error! ";

    private static final String HELP = """
      Game of Life - possible commands:
      alive i j    set cell in column i and row j alive
      clear        kill all cells and reset generations
      dead i j     kill cell in column i and row j
      generate     compute next generation
      help         print this help
      new x y      start a new game with dimensions x times y
      print        print the gameboard
      quit         quit the program
      resize x y   resize current game to dimensions x times y
      shape name   load initial population""";

    private Grid game;

    /**
     * The main loop that handles the shell interaction. It takes commands from
     * the user and executes them.
     *
     * @throws IOException thrown when reading from stdin fails
     */
    void run() throws IOException {

        game = new Game(5, 5);

        // TODO: add code here
        try ( Scanner scanner = new Scanner(System.in)) {
            System.out.println(HELP);
            playGoL(scanner);
        }
    }

    // TODO: add code here
    /**
     *
     * @param scanner
     * @throws IOException
     */
    private void playGoL(Scanner scanner) throws IOException {
        prompt();

        if (scanner.hasNextLine()) {
            String next = scanner.nextLine();

            String[] split = next.split("\\s+");

            if (split.length > 3) {
                System.out.println(ERROR);
                playGoL(scanner);
            } else if (split[0].trim().equals("alive")) {
                if (split.length != 3) {
                    System.out.println(ERROR);
                    playGoL(scanner);
                } else {
                    try {
                        int i = Integer.parseInt(split[1]);
                        int j = Integer.parseInt(split[2]);

                        game.setCellAlive(i, j);
                        playGoL(scanner);
                    } catch (NumberFormatException ex) {
                        throw new IOException(ex);
                    }
                }
            } else if (split[0].trim().equals("clear")) {
                game.clear();
                playGoL(scanner);
            } else if (split[0].trim().equals("dead")) {
                if (split.length != 3) {
                    System.out.println(ERROR);
                    playGoL(scanner);
                } else {
                    try {
                        int i = Integer.parseInt(split[1]);
                        int j = Integer.parseInt(split[2]);

                        game.setCellDead(i, j);
                        playGoL(scanner);
                    } catch (NumberFormatException ex) {
                        throw new IOException(ex);
                    }
                }
            } else if (split[0].trim().equals("generate")) {
                game.next();
                playGoL(scanner);
            } else if (split[0].trim().equals("help")) {
                System.out.println(HELP);
                playGoL(scanner);
            } else if (split[0].trim().equals("new")) {
                if (split.length != 3) {
                    System.out.println(ERROR);
                    playGoL(scanner);
                } else {
                    try {
                        int x = Integer.parseInt(split[1]);
                        int y = Integer.parseInt(split[2]);

                        game = new Game(x, y);
                        playGoL(scanner);
                    } catch (NumberFormatException ex) {
                        throw new IOException(ex);
                    }
                }
            } else if (split[0].trim().equals("print")) {
                System.out.println(game.toString());
                playGoL(scanner);
            } else if (split[0].trim().equals("quit")) {
                System.exit(0);
            } else if (split[0].trim().equals("resize")) {
                if (split.length != 3) {
                    System.out.println(ERROR);
                    playGoL(scanner);
                } else {
                    try {
                        int x = Integer.parseInt(split[1]);
                        int y = Integer.parseInt(split[2]);

                        game.resize(x, y);
                        playGoL(scanner);
                    } catch (NumberFormatException ex) {
                        throw new IOException(ex);
                    }
                }
            } else if (split[0].trim().equals("shape")) {
                if (split.length != 2) {
                    System.out.println(ERROR);
                    playGoL(scanner);
                } else {
                    if (split[1].trim().equals("name")) {
                        game = new Game(5, 5);
                        System.out.println(game.toString());
                        playGoL(scanner);
                    }
                }
            } else {
                System.out.println(ERROR);
                playGoL(scanner);
            }
        }
    }

    private void prompt() {
        System.out.print(PROMPT + "> ");
    }
}
