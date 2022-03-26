package nim;

import java.util.*;

public class NimState implements Cloneable {

  private int nDragons;
  private int player;
  private int utility = Integer.MIN_VALUE;

  public NimState() {
    nDragons = NimGame.NDRAGONS;
    player = 1;
  }

  public int hashCode() {
    return toString().hashCode();
  }

  public String toString() {
    return "" + nDragons + " DRAGONS -- PLAYER " + player + "TURN";
  }

  public boolean equals(Object o) {
    if(!(o instanceof NimState)) {
      return false;
    } 
    NimState on = (NimState) o;
    return this.nDragons == on.nDragons && this.player == on.player;
  }

  public int getUtility() {
    return utility;
  }

  public int getPlayerToMove() {
    return player;
  }

  public List<Integer> getValidMoves() {
    List<Integer> validMoves = new LinkedList<Integer>();
    for(int i = 2; i <= 4; i++) {
      if(nDragons >= i) {
        validMoves.add(-(i-1));
      }
    }
    return validMoves;
  }

  public NimState clone() {
    try { 
      return (NimState) super.clone();
    } catch (CloneNotSupportedException e) {
      return null;
    }
  }

  public void enact(int move) {
    nDragons += move;
    if (player == 1) {
      player = 2;
    } else {
      player = 1;
    }
    analyzeUtility();
  }

  private void analyzeUtility() {
    if(nDragons == 1) {
      utility = (player == 1) ? 1 : 0;
    }
    
    // TODO
  }
  
}