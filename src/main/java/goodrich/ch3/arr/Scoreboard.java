package goodrich.ch3.arr;

public class Scoreboard {
  private int numEntries = 0;
  private final GameEntry[] board;

  public Scoreboard(int capacity) {
    this.board = new GameEntry[capacity];
  }

  public void add(GameEntry e) {
    int newScore = e.score();
    if(this.numEntries < this.board.length
    || newScore > this.board[this.numEntries - 1].score()) {

      if(this.numEntries < this.board.length) {
        this.numEntries++;
      }

      int j = numEntries - 1;
      while(j > 0 && this.board[j-1].score() < newScore) {
        this.board[j] = this.board[j-1];
        j--;
      }
      this.board[j] = e;
    }
  }

  public GameEntry remove(int i) throws IndexOutOfBoundsException {
    if (i < 0 || i >= numEntries) {
      throw new IndexOutOfBoundsException("Invalid index: " + i);
    }
    var temp = board[i];
    for(int j = i; j < numEntries - 1; j++) {
      board[j] = board[j+1];
    }
    board[numEntries - 1] = null;
    numEntries--;
    return temp;
  }


}
