package swe4.astar;

public class Point implements Shape{
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x += x; }
    public void setY(int y) { this.y += y; }
    @Override
    public boolean collide(Object other) {
        Point n = (Point) other;
        return (this.x == n.x || this.y == n.y);
    }
}
