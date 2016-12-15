package impl

import api.Direction

/**
 * Created by emccarthy on 02/12/2016.
 */
class CodeRetriever {

    private static final char[][] KEYPAD = Arrays.asList(
            Arrays.asList(' ', ' ', '1', ' ', ' '),
            Arrays.asList(' ', '2', '3', '4', ' '),
            Arrays.asList('5','6','7','8','9'),
            Arrays.asList(' ', 'A', 'B', 'C', ' '),
            Arrays.asList(' ', ' ', 'D', ' ', ' '))

    int currentXIndex = 0
    int currentYIndex = 2

    public CodeRetriever() {

    }

    public char retrieveCode(String input) {
        for(int i = 0 ; i < input.length() ; i++){
            Direction nextDirection = Direction.toDirection(String.valueOf(input.charAt(i)))
            switch (nextDirection) {
                case Direction.UP:
                    int tempIndex = currentYIndex - 1
                    currentYIndex = validYIndex(tempIndex) ? tempIndex : this.currentYIndex
                    break;
                case Direction.DOWN:
                    int tempIndex = currentYIndex + 1
                    currentYIndex = validYIndex(tempIndex) ? tempIndex : this.currentYIndex
                    break;
                case Direction.LEFT:
                    int tempIndex = currentXIndex - 1
                    currentXIndex = validXIndex(tempIndex) ? tempIndex : this.currentXIndex
                    break
                case Direction.RIGHT:
                    int tempIndex = currentXIndex + 1
                    currentXIndex = validXIndex(tempIndex) ? tempIndex : this.currentXIndex
                    break
                default:
                    throw new IllegalArgumentException()
            }
        }
        return KEYPAD[currentYIndex][currentXIndex]
    }

    private boolean validYIndex (int index) {
        return index >= 0 && index <= 4 && KEYPAD[index][this.currentXIndex] != ' '
    }

    private boolean validXIndex(int index) {
        return index >= 0 && index <= 4 && KEYPAD[this.currentYIndex][index] != ' '
    }

}
