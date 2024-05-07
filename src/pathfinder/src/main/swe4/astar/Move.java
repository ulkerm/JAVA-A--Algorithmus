package swe4.astar;

public class Move implements Transition{
    private final State to;
    private final double costs;
    private final int deltaX;
    private final int deltaY;


    public Move(State to, double costs, int deltaX, int deltaY) {
        this.to = to;
        this.costs = costs;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() { return deltaX; }

    public int getDeltaY() { return deltaY; }

    public State getTo() {
        return to;
    }
    @Override
    public double costs() {
        return costs;
    }

    public Point applyTo(Point obj) {
        int newX = obj.getX() + deltaX;
        int newY = obj.getY() + deltaY;
        return new Point(newX, newY);
    }
}
