package connectfour;

import java.util.List;
import java.util.Objects;

import gamealgorithms.*;

/**
 * Provides an implementation of the Tic-tac-toe game which can be used for
 * experiments with the Minimax algorithm.
 * 
 * @author Ruediger Lunde
 * 
 */
public class ConnectFourGame implements Game<ConnectFourState, XYLocation, String> {

	private  ConnectFourState initialState = new ConnectFourState();

	@Override
	public ConnectFourState getInitialState() {
		return initialState;
	}

	@Override
	public String[] getPlayers() {
		return new String[] { ConnectFourState.X, ConnectFourState.O };
	}

	@Override
	public String getPlayer(ConnectFourState state) {
		return state.getPlayerToMove();
	}

	@Override
	public List<XYLocation> getActions(ConnectFourState state) {
		return state.getUnMarkedPositions();
	}

	@Override
	public ConnectFourState getResult(ConnectFourState state, XYLocation action) {
		ConnectFourState result = state.clone();
		result.mark(action);
		return result;
	}

	@Override
	public boolean isTerminal(ConnectFourState state) {
		return state.getUtility() != -1;
	}

	@Override
	public double getUtility(ConnectFourState state, String player) {
		double result = state.getUtility();
		if (result != -1) {
			if (Objects.equals(player, ConnectFourState.O))
				result = 1 - result;
		} else {
			throw new IllegalArgumentException("State is not terminal.");
		}
		return result;
	}
}