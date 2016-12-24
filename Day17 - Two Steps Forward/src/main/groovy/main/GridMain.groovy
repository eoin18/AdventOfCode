package main

import impl.Grid
import impl.GridPosition

/**
 * Created by eoin on 24/12/2016.
 */
class GridMain {

    static void main(String[] args) {
        GridPosition initialPosition = new GridPosition(0, 0)
        Grid grid = new Grid("awrkjxxr")

        println("Part 1 Result : " + grid.findShortestPathToEnd(initialPosition))

        println("Part 2 Result : " + grid.findLengthOfLongestPathToEnd(initialPosition))
    }

}
