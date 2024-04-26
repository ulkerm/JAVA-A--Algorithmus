package swe4.astar.test;

import swe4.astar.Transition;

class Edge implements Transition {

  private final Vertex to;
  private final double costs;

  public Edge(Vertex to, double costs) {
    this.to = to;
    this.costs = costs;
  }

  public Vertex getTo() {
    return to;
  }

  @Override
  public double costs() {
    return costs;
  }
}