package swe4.astar;

public class Enviroment {
    private Shape rectangle;

    public Enviroment(Shape rec) {
        this.rectangle = rec;
    }

    public Shape getRectangle() { return rectangle; }
}
