package api
/**
 * Created by emccarthy on 02/12/2016.
 */
enum Direction {

    UP,DOWN,LEFT,RIGHT

    private static final String U = "U"
    private static final String L = "L"
    private static final String R = "R"
    private static final String D = "D"

    public static Direction toDirection(String input) {
        switch (input) {
            case U:
                return UP
            case L:
                return LEFT
            case R:
                return RIGHT
            case D:
                return DOWN
        }
    }
}