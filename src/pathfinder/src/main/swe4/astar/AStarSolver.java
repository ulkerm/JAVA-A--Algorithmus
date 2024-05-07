package swe4.astar;

import java.util.*;

public class AStarSolver {

  private final Set<SearchNode> nodes = new HashSet<>();
  private final Map<State, Set<SearchNode>> connections = new HashMap<>();
  private PriorityQueue<SearchNode> openList = new PriorityQueue<>();

  private double getNodeCost(SearchNode node) {
    for (SearchNode n : nodes) {
      if (n.equals(node)) {
        return n.costsFromStart();
      }
    }
    return Double.POSITIVE_INFINITY;
  }

  private void generateSuccessorNodes(SearchNode current, State target) {
    List<? extends Transition> possibleTransitions = current.getState().transitions();
    for (Transition transition : possibleTransitions) {
      State nextState = current.getState().apply(transition);
      SearchNode succ = new SearchNode(nextState, target, current, transition);
      succ.setCostsFromStart(current.costsFromStart() + transition.costs());
      if (connections.containsKey(succ.getState()) || succ.costsFromStart() < getNodeCost(succ)) {
        openList.add(succ);
      }
    }
  }

  // Berechnet eine kostenoptimale Folge von Transitionen, welche den gegebenen
  // Anfangszustand initialState in den Zielzustand targetState überführt.
  // Wirft NoSolutionException (Checked Exception), falls es keine derartige
  // Folge von Transitionen gibt.
  public List<? extends Transition> solve(State initialState, State targetState) throws NoSolutionException {
    SearchNode n = new SearchNode(initialState, targetState);
    n.setCostsFromStart(0);
    openList.add(n);

    if (!connections.containsKey(initialState)) {
      connections.put(initialState, new HashSet<>());
    }
    connections.get(initialState).add(n);

    while (!openList.isEmpty()) {
      SearchNode currentNode = openList.poll();
      if (currentNode.getState().equals(targetState)) {
        return currentNode.getTransitionsFromStart();
      }
      generateSuccessorNodes(currentNode, targetState);
    }

    throw new NoSolutionException();
  }
}
