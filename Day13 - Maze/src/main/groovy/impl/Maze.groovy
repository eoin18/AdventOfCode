package impl

import java.util.stream.Collectors

/**
 * Created by emccarthy on 14/12/2016.
 */
class Maze {

    int seed

    Maze(int seed){
        this.seed = seed
    }

    Set<Position> getNextPositions(Position current) {
        Set<Position> allNextPositions = new HashSet<>()
        Set<Position> visitedPositions = new LinkedHashSet<>(current.prevPositions)
        visitedPositions.add(current)
        allNextPositions.add(new Position(current.x+1, current.y, visitedPositions))
        allNextPositions.add(new Position(current.x, current.y+1, visitedPositions))

        if(current.x >= 1){
            allNextPositions.add(new Position(current.x-1, current.y, visitedPositions))
        }

        if(current.y >= 1) {
            allNextPositions.add(new Position(current.x, current.y-1, visitedPositions))
        }

        return allNextPositions.stream().filter({
            Position p -> isOpenSpace(p) && !visitedPositions.contains(p)
        }).collect(Collectors.toSet())
    }

    boolean isOpenSpace(Position position){
        int x = position.x
        int y = position.y
        int sum = x*x + 3*x + 2*x*y + y + y*y + this.seed
        return Integer.toBinaryString(sum).count('1') % 2 == 0
    }
}
