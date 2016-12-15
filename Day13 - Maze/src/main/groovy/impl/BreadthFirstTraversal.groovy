package impl

import java.util.stream.Collectors

/**
 * Created by emccarthy on 14/12/2016.
 */
class BreadthFirstTraversal {

    Maze maze
    Set<Position> visitedPositions

    BreadthFirstTraversal(Maze maze){
        this.maze = maze
        this.visitedPositions = new HashSet<>()
    }

    int traverse(Position initialPosition, Position targetPosition){
        Queue<Position> stateQueue = new LinkedList<>()
        stateQueue.add(initialPosition)

        while(!stateQueue.isEmpty()) {
            Position currentPosition = stateQueue.remove(0)
            if(currentPosition.equals(targetPosition)){
                return currentPosition.prevPositions.size()
            }

            this.visitedPositions.add(currentPosition)

            stateQueue.addAll(this.maze.getNextPositions(currentPosition).stream().filter({
                Position p -> !this.visitedPositions.contains(p)
            }).collect(Collectors.toSet()))
        }
        return -1
    }

    int traverse(Position initialPosition, int maxSteps){
        Queue<Position> stateQueue = new LinkedList<>()
        stateQueue.add(initialPosition)

        while(!stateQueue.isEmpty()) {
            Position currentPosition = stateQueue.remove(0)
            if(currentPosition.prevPositions.size() > maxSteps){
                return this.visitedPositions.size()
            }

            this.visitedPositions.add(currentPosition)

            stateQueue.addAll(this.maze.getNextPositions(currentPosition).stream().filter({
                Position p -> !this.visitedPositions.contains(p)
            }).collect(Collectors.toSet()))
        }
        return -1
    }

}
