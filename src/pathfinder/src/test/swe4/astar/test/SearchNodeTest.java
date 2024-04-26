package swe4.astar.test;

import org.junit.jupiter.api.Test;
import swe4.astar.MathUtil;
import swe4.astar.SearchNode;
import swe4.astar.State;
import swe4.astar.Transition;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchNodeTest {


  @Test
  public void oneParamConstructorInitializesCorrectly() {
    Vertex     v  = new Vertex(1, 10.0);
    SearchNode sn = new SearchNode(v, v);
    assertNotNull(sn.getState());
    assertNull(sn.getPredecessor());
    assertNull(sn.getTransition());
  }

  @Test
  public void threeParamConstructorInitializesCorrectly() {
    Vertex v1  = new Vertex(1, 10.0);
    Vertex v2  = new Vertex(2, 10.0);
    Edge   e12 = new Edge(v2, 15.0);
    v1.addEdge(e12);

    SearchNode sn1 = new SearchNode(v1, v2);
    SearchNode sn2 = new SearchNode(v2, v2, sn1, e12);

    assertSame(v2, sn2.getState());
    assertSame(sn1, sn2.getPredecessor());
    assertSame(e12, sn2.getTransition());
  }

  @Test
  public void constructorInitializesCostsCorrectly() {
    Vertex v1  = new Vertex(1, 10.0);
    Vertex v2  = new Vertex(2, 10.0);
    Edge   e12 = new Edge(v2, 15.0);
    v1.addEdge(e12);

    SearchNode sn1 = new SearchNode(v1, v2);
    SearchNode sn2 = new SearchNode(v2, v2, sn1, e12);

    assertTrue(MathUtil
                 .isDoubleEqual(v2.estimatedCostsToTarget(v2), sn2.estimatedCostsToTarget()));
    assertTrue(MathUtil
                 .isDoubleEqual(sn1.costsFromStart() + e12.costs(), sn2.costsFromStart()));
  }

  @Test
  public void costsCalculatedCorrectly() {
    Vertex v1  = new Vertex(1, 10.0);
    Vertex v2  = new Vertex(2, 10.0);
    Edge   e12 = new Edge(v2, 15.0);
    v1.addEdge(e12);

    SearchNode sn1 = new SearchNode(v1, v2);
    SearchNode sn2 = new SearchNode(v2, v2, sn1, e12);

    assertTrue(MathUtil.isDoubleEqual(sn2.costsFromStart() + sn2.estimatedCostsToTarget(),
                                      sn2.estimatedTotalCosts()));
  }

  @Test
  public void compareSearchNodes() {
    Vertex v1  = new Vertex(1, 20.0);
    Vertex v2  = new Vertex(2, 10.0);
    Vertex v3  = new Vertex(3, 10.0);
    Edge   e12 = new Edge(v2, 10.0);
    Edge   e13 = new Edge(v3, 15.0);
    v1.addEdge(e12);
    v1.addEdge(e13);

    SearchNode sn1 = new SearchNode(v1, v3);
    SearchNode sn2 = new SearchNode(v2, v3, sn1, e12);
    SearchNode sn3 = new SearchNode(v3, v3, sn1, e13);

    assertTrue(sn2.compareTo(sn3) < 0);
  }

  @Test
  public void hashCodeConsistent() {
    Vertex v1 = new Vertex(1, 10.0);
    Vertex v2 = new Vertex(2, 0.0);

    SearchNode sn1 = new SearchNode(v1, v2);
    SearchNode sn2 = new SearchNode(v1, v2);

    assertTrue(sn1.hashCode() == sn2.hashCode());
  }

  @Test
  public void equalsConsistent() {
    Vertex v1 = new Vertex(1, 10.0);
    Vertex v2 = new Vertex(2, 0.0);

    SearchNode sn1 = new SearchNode(v1, v2);
    SearchNode sn2 = new SearchNode(v1, v2);

    assertTrue(sn1.equals(sn2));
  }
}
