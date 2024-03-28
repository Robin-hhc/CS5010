package tictactoe;

import java.util.Scanner;

public class TicTacToeConsoleController implements TicTacToeController{
  private Appendable out;
  private Scanner scan;

  public TicTacToeConsoleController(Readable in, Appendable out) {
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(TicTacToe m) {
    int row = scan.nextInt();
    int col = scan.nextInt();

    m.move(row, col);
//    out.append("not a valid move");
  }
}
