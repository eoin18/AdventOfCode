package api
/**
 * Created by emccarthy on 02/12/2016.
 */
enum CardinalDirections implements Moveable {

    NORTH {
        @Override
        CardinalDirections changeDirection(String direction) {
            return direction.startsWith(RIGHT) ? EAST : WEST
        };
    },
    SOUTH {
        @Override
        CardinalDirections changeDirection(String direction) {
            return direction.startsWith(RIGHT) ? WEST : EAST
        }
    },
    EAST{
        @Override
        CardinalDirections changeDirection(String direction) {
            return direction.startsWith(RIGHT) ? SOUTH : NORTH
        }
    },
    WEST {
        @Override
        CardinalDirections changeDirection(String direction) {
            return direction.startsWith(RIGHT) ? NORTH : SOUTH
        }
    };

    private static final String RIGHT = "R"

    @Override
    CardinalDirections changeDirection(String direction) {
        return null
    }
}