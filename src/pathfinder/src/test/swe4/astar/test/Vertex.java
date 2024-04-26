package swe4.astar.test;

import swe4.astar.State;
import swe4.astar.Transition;

import java.util.ArrayList;
import java.util.List;

class Vertex implements State {

  private final int              id;
  private final double           costs;
  private final List<Transition> transitions = new ArrayList<>();

  public Vertex(int id, double costs) {
    this.id          = id;
    this.costs       = costs;
  }

  @Override
  public List<Transition> transitions() { return transitions; }

  public void addEdge(Edge edge) {
    transitions.add(edge);
  }

  @Override
  public State apply(Transition transition) {
    return ((Edge)transition).getTo();
  }

  @Override
  public double estimatedCostsToTarget(State target) {
    return costs;
  }

  @Override
  public int hashCode() { return id; }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Vertex))
      return false;
    return this.id == ((Vertex)other).id;
  }
}

