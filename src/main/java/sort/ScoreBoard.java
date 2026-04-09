package basic;

public class ScoreBoard {
    private int numOfEntries = 0;
    private GameEntry[] entries;

    public ScoreBoard(int capacity) {
        entries = new GameEntry[capacity];
    }

    // array
    // number of entries
    // position of an entry
    // assignment to shift entries
    // while loop to go through entries

    public void add(GameEntry e) {
        var newScore = e.score();
        if(numOfEntries < entries.length || newScore > entries[numOfEntries-1].score()) {
            // no score drops from the board so overall number increases
            if(numOfEntries < entries.length) {
                numOfEntries++;
            }
            // shift any lower scores rightward to make room for the new entry
            var j = numOfEntries - 1;
            while(j > 0 && entries[j-1].score() < newScore) {
                entries[j] = entries[j-1]; // shift entry from j-1 to j
                j--;                       // and decrement j
            }
            // set the new score
            entries[j] = e;

        }
    }

    public GameEntry remove(int i) throws IndexOutOfBoundsException {
        if(i < 0 || i > numOfEntries) {
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
        var temp = entries[i];
        var j = i;
        while(j < numOfEntries -1) {
            entries[j] = entries[j+1];
            j++;
        }
        entries[numOfEntries-1] = null;
        numOfEntries--;
        return temp;
    }
}
