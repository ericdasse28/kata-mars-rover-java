import java.util.HashMap;
import java.util.List;

public class MarsRover {
    public Point point;
    public Direction direction;

    public MarsRover(Point point, Direction direction) {
        this.point = point;
        this.direction = direction;
    }

    public void accept(List<Character> commands) {
        for (char command : commands) {
            if (command == 'f') {
                moveForward();
            } else if (command == 'b') {
                moveBackward();
            } else if (command == 'l') {
                turnLeft();
            }
        }
    }

    private void turnLeft() {
        var directionAfterTurn = new HashMap<Direction, Direction>();
        directionAfterTurn.put(Direction.N, Direction.W);
        directionAfterTurn.put(Direction.S, Direction.E);
        directionAfterTurn.put(Direction.E, Direction.N);
        directionAfterTurn.put(Direction.W, Direction.S);

        this.direction = directionAfterTurn.get(this.direction);
    }

    private void moveBackward() {
        var pointGivenDirection = new HashMap<Direction, Point>();

        pointGivenDirection.put(Direction.N, new Point(this.point.x(), this.point.y() - 1));
        pointGivenDirection.put(Direction.S, new Point(this.point.x(), this.point.y() + 1));
        pointGivenDirection.put(Direction.E, new Point(this.point.x() - 1, this.point.y()));
        pointGivenDirection.put(Direction.W, new Point(this.point.x() + 1, this.point.y()));

        this.point = pointGivenDirection.get(this.direction);
    }

    private void moveForward() {
        var pointGivenDirection = new HashMap<Direction, Point>();

        pointGivenDirection.put(Direction.N, new Point(this.point.x(), this.point.y() + 1));
        pointGivenDirection.put(Direction.S, new Point(this.point.x(), this.point.y() - 1));
        pointGivenDirection.put(Direction.E, new Point(this.point.x() + 1, this.point.y()));
        pointGivenDirection.put(Direction.W, new Point(this.point.x() - 1, this.point.y()));

        this.point = pointGivenDirection.get(this.direction);
    }
}

