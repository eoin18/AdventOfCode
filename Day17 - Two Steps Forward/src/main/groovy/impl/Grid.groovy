package impl

/**
 * Created by eoin on 24/12/2016.
 */
class Grid {

    String passcode

    Grid(String passcode) {
        this.passcode = passcode
    }

    String findShortestPathToEnd(GridPosition initialPosition) {
        Queue<GridPosition> searchQueue = new LinkedList<>()
        searchQueue.add(initialPosition)

        while(!searchQueue.isEmpty()) {
            GridPosition currentPosition = searchQueue.remove(0)

            if(currentPosition.isEndPoint()) {
                return currentPosition.path
            }

            searchQueue.addAll(currentPosition.getNextValidPositions(this.passcode))
        }

        return "No possible path"
    }

    int findLengthOfLongestPathToEnd(GridPosition initialPosition) {
        int longestPath = -1
        Queue<GridPosition> searchQueue = new LinkedList<>()
        searchQueue.add(initialPosition)

        while(!searchQueue.isEmpty()) {
            GridPosition currentPosition = searchQueue.remove(0)

            if(currentPosition.isEndPoint()) {
                longestPath = currentPosition.path.length()
            } else {
                searchQueue.addAll(currentPosition.getNextValidPositions(this.passcode))
            }
        }

        return longestPath

    }

}
