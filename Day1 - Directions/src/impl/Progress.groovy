package impl

import api.CardinalDirections

/**
 * Created by emccarthy on 02/12/2016.
 */
class Progress {

    CardinalDirections currentDirection
    Position currentPosition

    public Progress() {
        this.currentDirection = CardinalDirections.NORTH
    }

    public boolean move(String step) {
        this.currentDirection = this.currentDirection.changeDirection(step)
        this.currentPosition = Position.movePosition(this.currentPosition, step, this.currentDirection)
        return this.currentPosition.isStopped()
    }

    public int calculateDistanceTravelledFromStart(){
        return this.currentPosition.calculateDistanceFromStart()
    }


}
