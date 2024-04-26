package swe4.astar.test;

import org.junit.jupiter.api.Test;
import swe4.astar.AStarSolver;
import swe4.astar.NoSolutionException;
import swe4.astar.Transition;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AStarSolverTest {

  @Test
  void singleStateSolutionExists() {
    AStarSolver solver     = new AStarSolver();
    Vertex      finalState = new Vertex(1, 0.0);
    try {
      List<? extends Transition> transitions = solver.solve(finalState, finalState);
      assertTrue(transitions.isEmpty());
    }
    catch (NoSolutionException e) {
      fail("NoSolutionException not expected");
    }
  }

  @Test
  void oneTransition() {
    AStarSolver solver  = new AStarSolver();
    Vertex      initial = new Vertex(1, 1.0);
    Vertex      target  = new Vertex(2, 0.0);
    initial.addEdge(new Edge(target, 1.0));

    try {
      List<? extends Transition> transitions = solver.solve(initial, target);
      assertEquals(1, transitions.size());
      assertEquals(target, initial.apply(transitions.get(0)));
    }
    catch (NoSolutionException e) {
      fail("NoSolutionException not expected");
    }
  }

  @Test
  void twoIsolatedNodesNoSolution() {
    AStarSolver solver = new AStarSolver();

    Vertex initial = new Vertex(1, 1.0);
    Vertex target  = new Vertex(2, 0.0);

    assertThrows(NoSolutionException.class, () -> solver.solve(initial, target));
  }

  @Test
  void twoNodesTwoTransition() {
    AStarSolver solver = new AStarSolver();

    Vertex initial = new Vertex(1, 1.0);
    Vertex target  = new Vertex(2, 0.0);
    Edge   e1      = new Edge(target, 3.0);
    Edge   e2      = new Edge(target, 2.0);
    initial.addEdge(e1);
    initial.addEdge(e2);

    try {
      List<? extends Transition> transitions = solver.solve(initial, target);
      assertEquals(1, transitions.size());
      assertSame(e2, transitions.get(0));
      assertEquals(target, initial.apply(transitions.get(0)));
    }
    catch (NoSolutionException e) {
      fail("NoSolutionException not expected");
    }
  }

  @Test
  void triangleSumTwoSidesShorter() {
    AStarSolver solver = new AStarSolver();

    Vertex v1 = new Vertex(1, 8.0);
    Vertex v2 = new Vertex(2, 5);
    Vertex v3 = new Vertex(3, 0.0);

    Edge e13 = new Edge(v3, 10.0);
    v1.addEdge(e13);
    Edge e12 = new Edge(v2, 3.0);
    v1.addEdge(e12);
    Edge e23 = new Edge(v3, 4.0);
    v2.addEdge(e23);

    try {
      List<? extends Transition> transitions = solver.solve(v1, v3);
      assertEquals(2, transitions.size());
      assertSame(e12, transitions.get(0));
      assertSame(e23, transitions.get(1));
    }
    catch (NoSolutionException e) {
      fail("NoSolutionException not expected");
    }
  }

  @Test
  void triangleOneSideShorter() {
    AStarSolver solver = new AStarSolver();

    Vertex v1 = new Vertex(1, 8.0);
    Vertex v2 = new Vertex(2, 5);
    Vertex v3 = new Vertex(3, 0.0);

    Edge e13 = new Edge(v3, 10.0);
    v1.addEdge(e13);
    Edge e12 = new Edge(v2, 6.0);
    v1.addEdge(e12);
    Edge e23 = new Edge(v3, 7.0);
    v2.addEdge(e23);

    try {
      List<? extends Transition> transitions = solver.solve(v1, v3);
      assertEquals(1, transitions.size());
      assertSame(e13, transitions.get(0));
    }
    catch (NoSolutionException e) {
      fail("NoSolutionException not expected");
    }
  }

  @Test
  void quadrangle() {
    AStarSolver solver = new AStarSolver();

    Vertex v1 = new Vertex(1, 8.0);
    Vertex v2 = new Vertex(2, 4.0);
    Vertex v3 = new Vertex(3, 5.0);
    Vertex v4 = new Vertex(4, 0.0);

    Edge e12 = new Edge(v2, 6.0);
    v1.addEdge(e12);
    Edge e13 = new Edge(v3, 4.0);
    v1.addEdge(e13);
    Edge e32 = new Edge(v2, 1.0);
    v3.addEdge(e32);
    Edge e24 = new Edge(v4, 5.0);
    v2.addEdge(e24);
    Edge e34 = new Edge(v4, 8.0);
    v3.addEdge(e34);

    try {
      List<? extends Transition> transitions = solver.solve(v1, v4);
      assertEquals(3, transitions.size());
      assertSame(e13, transitions.get(0));
      assertSame(e32, transitions.get(1));
      assertSame(e24, transitions.get(2));
    }
    catch (NoSolutionException e) {
      fail("NoSolutionException not expected");
    }
  }
}


