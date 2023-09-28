import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
  public static boolean TESTING = true;
  private final Field[] board = new Field[32];
  private final Player[] players;
  boolean gameRunning = true;

  public Board(int numberOfPlayers) {
    //initialise Players
    players = new Player[numberOfPlayers];
    PlayerName[] nameList = playerNameList(numberOfPlayers);
    for (int i = 0; i < players.length; i++) {
      players[i] = new Player(nameList[i]);
    }

    //initialise Board
    board[0] = new StartField(0);
    for (int i = 1; i < 32; i++) {
      if (i == 2 || i == 4 || i == 12 || i == 15 ||
              i == 20 || i == 23 || i == 27 || i == 30) {
        board[i] = new ActionField(i);
      } else if (i == 5 || i == 8 || i == 14 ||
              i == 18 || i == 24 || i == 29) {
        board[i] = new EventField(i);
      } else if (i == 9 || i == 16 || i == 25) {
        board[i] = new ItemField(i);
      } else if (i == 1 || i == 19) {
        board[i] = new UnluckyField(i);
      } else {
        board[i] = new Field(i);
      }
    }
  }

  public void play() {
    while (gameRunning) {
      for (int i = 0; i < players.length; i++) {
        int dice = throwDice();
        if (players[i].getField() + dice >= 32) {
          if (players[i].getMoney() >= 50) {
            gameRunning = false;
            if (TESTING) System.out.println(players[i].getName() + " hat gewonnen!");
            break;
          }
          playerOverStart(players[i]);
        }
        players[i].move(dice);

        if (board[players[i].getField()] instanceof ActionField) {

        } else if (board[players[i].getField()] instanceof EventField) {

        } else if (board[players[i].getField()] instanceof ItemField) {

        } else if (board[players[i].getField()] instanceof UnluckyField) {

        }
      }
    }
  }

  private void playerOverStart(Player player) {
    player.addMoney(10);
    player.addToAttributeSemiRandom(1);
  }

  public Field getField(int number) {
    return board[number]; //hässlich und unsicher >:(
  }

  public int throwDice() {
    return (int) (Math.random() * 6 + 1);
  }

  public PlayerName[] playerNameList(int numOfPlayers) {
    //create array of unique random numbers
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 14; i++) {
      list.add(i);
    }
    Collections.shuffle(list);
    int[] randomInts = new int[numOfPlayers];
    for (int i = 0; i < numOfPlayers; i++)
      randomInts[i] = list.get(i);

    PlayerName[] playerList = new PlayerName[numOfPlayers];
    for (int i = 0; i < numOfPlayers; i++) {
      playerList[i] = PlayerName.values()[randomInts[i]];
    }
    return playerList;
  }

  public void printAttributes() {
    //print attributes of each player using Player.attributesToString()
    for (Player player : players) {
      System.out.println(player.toString());
      System.out.println(player.attributesToString());
      System.out.println();
    }
  }
}
