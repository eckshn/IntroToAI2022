package nim;

import gamealgorithms.*;
import java.util.*;

// Data type of state of game is custom class -- NimState
// Data type of moves is integer (number of dragons to subtract)
// Data type of player is integer (1=MAX first / 2=MINNIE second)
public class NimGame implements Game<NimState, Integer, Integer> {

  public static final int NDRAGONS = 18;
  public static final int FATAL_STATE = 1;
  public static final int[] MOVES = new int[] {-1, -2, -3};

  private NimState initialState = new NimState();
  private Integer[] players = {1, 2};

  public NimState getInitialState() {
    return initialState;
  }

  public Integer[] getPlayers() {
    return players;
  }

  public Integer getPlayer(NimState s) {
    return s.getPlayerToMove();
  }

  public List<Integer> getActions(NimState s) {
    return s.getValidMoves();
  }

  public NimState getResult(NimState s, Integer move) {
    NimState result = s.clone();
    result.enact(move);
    return result;
    // TODO
  }

  public boolean isTerminal(NimState s) {
    return s.getUtility() != Integer.MIN_VALUE;
    // TODO
  }

  public double getUtility(NimState s, Integer player) {
    double result = s.getUtility();
    if (result != Integer.MIN_VALUE) {
			if (player == 2) 
				result = 1 - result;
		} else {
			throw new IllegalArgumentException("State is not terminal.");
		}
    return result;
  }
  
}