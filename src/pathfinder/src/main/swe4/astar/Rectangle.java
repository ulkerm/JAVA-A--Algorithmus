package swe4.astar;

public class Rectangle implements Shape{
    private final double topleft;
    private final double topright;
    private final double bottomleft;
    private final double bottomright;

    public Rectangle(double topleft, double topright, double bottomleft, double bottomright) {
        this.topleft = topleft;
        this.topright = topright;
        this.bottomleft = bottomleft;
        this.bottomright = bottomright;
    }

    @Override
    public boolean collide(Object other) {
        Point n = (Point) other;
        return ((n.getX() >= bottomleft && n.getX() <= topright) ||
                (n.getY() >= topleft && n.getY() <= bottomright));
    }
}
