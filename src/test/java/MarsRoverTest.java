import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class MarsRoverTest {

    public static Stream<Arguments> generateForwardTestArgs() {
        return Stream.of(
                Arguments.of(new Point(0, 0), Direction.N, new Point(0, 1)),
                Arguments.of(new Point(2, 3), Direction.S, new Point(2, 2)),
                Arguments.of(new Point(0, 0), Direction.E, new Point(1, 0)),
                Arguments.of(new Point(-1, 9), Direction.W, new Point(-2, 9)));
    }

    public static Stream<Arguments> generateBackwardTestArgs() {
        return Stream.of(
                Arguments.of(new Point(0, 0), Direction.N, new Point(0, -1)),
                Arguments.of(new Point(2, 3), Direction.S, new Point(2, 4)),
                Arguments.of(new Point(4, 5), Direction.W, new Point(5, 5)),
                Arguments.of(new Point(3, 16), Direction.E, new Point(2, 16))
        );
    }

    public static Stream<Arguments> generateTurnLeftArgs() {
        // Returns examples of starting point, starting direction and
        // direction after move when a rover turns left
        return Stream.of(
                Arguments.of(new Point(0, 0), Direction.N, Direction.W),
                Arguments.of(new Point(5, 9), Direction.S, Direction.E),
                Arguments.of(new Point(9, 8), Direction.E, Direction.N),
                Arguments.of(new Point(5, 0), Direction.W, Direction.S)
        );
    }

    public static Stream<Arguments> generateTurnRightArgs() {
        return Stream.of(
                Arguments.of(new Point(5, 4), Direction.N, Direction.E),
                Arguments.of(new Point(7, 8), Direction.S, Direction.W),
                Arguments.of(new Point(2, 5), Direction.E, Direction.S),
                Arguments.of(new Point(0, 1), Direction.W, Direction.N)
        );
    }

    @ParameterizedTest
    @MethodSource("generateForwardTestArgs")
    void acceptForwardCommand(Point startingPoint, Direction startingDirection, Point pointAfterMove) {
        var rover = new MarsRover(
                startingPoint,
                startingDirection
        );

        rover.accept(List.of('f'));

        Assertions.assertEquals(pointAfterMove, rover.point);
        Assertions.assertEquals(startingDirection, rover.direction);
    }

    @ParameterizedTest
    @MethodSource("generateBackwardTestArgs")
    void acceptBackwardCommand(Point startingPoint, Direction startingDirection, Point pointAfterMove) {
        var rover = new MarsRover(startingPoint, startingDirection);

        rover.accept(List.of('b'));

        Assertions.assertEquals(pointAfterMove, rover.point);
        Assertions.assertEquals(startingDirection, rover.direction);
    }

    @ParameterizedTest
    @MethodSource("generateTurnLeftArgs")
    void acceptTurnLeftCommand(Point startingPoint, Direction startingDirection, Direction directionAfterTurn) {
        var rover = new MarsRover(startingPoint, startingDirection);

        rover.accept(List.of('l'));

        Assertions.assertEquals(startingPoint, rover.point);
        Assertions.assertEquals(directionAfterTurn, rover.direction);
    }

    @ParameterizedTest
    @MethodSource("generateTurnRightArgs")
    void acceptTurnRightCommand(Point startingPoint, Direction startingDirection, Direction directionAfterTurn) {
        var rover = new MarsRover(startingPoint, startingDirection);

        rover.accept(List.of('r'));

        Assertions.assertEquals(startingPoint, rover.point);
        Assertions.assertEquals(directionAfterTurn, rover.direction);
    }

    @Test
    void acceptMultipleCommands() {
        var startingPoint = new Point(8, 9);
        Direction startingDirection = Direction.S;
        var rover = new MarsRover(startingPoint, startingDirection);

        rover.accept(List.of('f', 'f', 'f', 'b', 'b', 'f'));

        Assertions.assertEquals(new Point(8, 7), rover.point);
        Assertions.assertEquals(startingDirection, rover.direction);
    }
}
