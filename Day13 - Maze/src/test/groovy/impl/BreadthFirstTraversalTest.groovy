package impl

import static org.junit.Assert.assertEquals

import org.junit.Before
import org.junit.Test

/**
 * Created by emccarthy on 15/12/2016.
 */
class BreadthFirstTraversalTest {

    private BreadthFirstTraversal traversal

    @Before
    public void setUp(){
        Maze maze = new Maze(10)
        this.traversal = new BreadthFirstTraversal(maze)
    }

    @Test
    public void testTraverseToPosition() {
        Position initialPosition = new Position(1, 1)
        Position targetPosition = new Position(7, 4)
        assertEquals("It should take 11 steps to navigate to 7,4 in the example maze", 11, this.traversal.traverse(initialPosition, targetPosition))
    }

}
