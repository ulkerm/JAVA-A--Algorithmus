package swe4.astar;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovingObjectTest {

    @Test
    void oneTransition() {
        Rectangle r = new Rectangle(2,4,2, 4);
        AStarSolver solver = new AStarSolver();
        Point p = new Point(0, 0);
        Point p1 = new Point(1, 0);
        MovingObject initial = new MovingObject(1, p, 1.0);
        initial.setObstacles(r);
        MovingObject target = new MovingObject(2, p1, 0.0);
        target.setObstacles(r);
        initial.addMove(new Move(target, 1.0,1, 0));

        try {
            List<? extends Transition> transitions = solver.solve(initial, target);
            assertEquals(1, transitions.size());
            assertEquals(target, initial.apply(transitions.get(0)));
        } catch (NoSolutionException e) {
            fail("NoSolutionException not expected");
        }
    }

    @Test
    void triangleSumTwoSidesShorter() {
        AStarSolver solver = new AStarSolver();
        Point obj1 = new Point(2, 0);
        Point obj = new Point(0, 0);
        Rectangle r = new Rectangle(4,8,4, 8);
        MovingObject v1 = new MovingObject(1, obj,8.0);
        v1.setObstacles(r);
        MovingObject v2 = new MovingObject(2, obj,5);
        v2.setObstacles(r);
        MovingObject v3 = new MovingObject(3, obj1,0.0);
        v3.setObstacles(r);

        Move e13 = new Move(v3, 10.0,1, 0);
        v1.addMove(e13);
        Move e12 = new Move(v2, 3.0,1, 0);
        v1.addMove(e12);
        Move e23 = new Move(v3, 4.0, 1, 0);
        v2.addMove(e23);

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
    public void testSetObstacles() {
        Point objPoint = new Point(0, 0);
        Rectangle rec = new Rectangle(2, 4, 2, 4);
        AStarSolver solver = new AStarSolver();
        Point objPoint1 = new Point(2, 0);
        MovingObject initial = new MovingObject(1, objPoint, 1.0);
        initial.setObstacles(rec);
        MovingObject target  = new MovingObject(2, objPoint1, 0.0);
        target.setObstacles(rec);
        Move e1      = new Move(target, 3.0, 1, 0);
        Move   e2      = new Move(target, 2.0, 1, 0);
        initial.addMove(e1);
        initial.addMove(e2);

        try {
            List<? extends Transition> transitions = solver.solve(initial, target);
            assertEquals(1, transitions.size());
            assertSame(e1, transitions.get(0));
            assertEquals(target, initial.apply(transitions.get(0)));
        }
        catch (NoSolutionException e) {
            fail("NoSolutionException not expected");
        }
    }

    @Test
    public void testAddMove() {
        Point startPoint = new Point(0, 0);
        MovingObject movingObject = new MovingObject(1, startPoint, 1.0);
        movingObject.setObstacles(new Rectangle(2, 10, 2, 10));

        Move validMove = new Move(null, 1.0, 1, 0);
        movingObject.addMove(validMove);
        assertEquals(1, movingObject.transitions().size());

        Move invalidMove = new Move(null, 1.0, 1, 0);
        movingObject.addMove(invalidMove);
        assertEquals(1, movingObject.transitions().size());
    }

}