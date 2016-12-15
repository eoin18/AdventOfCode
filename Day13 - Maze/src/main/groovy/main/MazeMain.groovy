package main

import impl.BreadthFirstTraversal
import impl.Maze
import impl.Position

/**
 * Created by emccarthy on 14/12/2016.
 */
class MazeMain {

    public static void main(String[] args){
        Maze maze = new Maze(1352)

        //Part 1
        Position targetPosition = new Position(31,39)
        BreadthFirstTraversal traversal = new BreadthFirstTraversal(maze)
        println("Part 1 Result: " + traversal.traverse(new Position(1,1), targetPosition))

        //Part 2
        traversal = new BreadthFirstTraversal(maze)
        println("Part 2 Result: " + traversal.traverse(new Position(1,1), 50))
    }
}
