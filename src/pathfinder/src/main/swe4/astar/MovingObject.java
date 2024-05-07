package swe4.astar;

import java.util.ArrayList;
import java.util.List;

public class MovingObject implements State{
    private final int id;
    private Point obj;
    private Enviroment enviroment;
    private final double costs;
    private final List<Move> moves = new ArrayList<>();

    public MovingObject(int id, Point obj, double costs) {
        this.id = id;
        this.obj = obj;
        this.costs = costs;
    }

    public void setObstacles(Rectangle e) {
        this.enviroment = new Enviroment(e);
    }
    @Override
    public List<? extends Transition> transitions() { return moves; }

    public void addMove(Move m) {
        obj.setX(m.getDeltaX());
        obj.setY(m.getDeltaY());
        if (!enviroment.getRectangle().collide(obj)) {
            moves.add(m);
        }
    }

    @Override
    public State apply(Transition transition) {
        return ((Move)transition).getTo();
    }

    @Override
    public double estimatedCostsToTarget(State targetState) {
        return costs;
    }

    public Enviroment getEnvironment() {
        return enviroment;
    }

    @Override
    public int hashCode() { return id; }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MovingObject))
            return false;
        return this.id == ((MovingObject)other).id;
    }
}
