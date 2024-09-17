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
