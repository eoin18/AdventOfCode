package impl

import api.CardinalDirections

/**
 * Created by emccarthy on 02/12/2016.
 */
class Position {

    int xpos = 0
    int ypos = 0
    boolean stopped = false
    List<Position> positionsVisitedToReachPoint

    public Position() {
        this.positionsVisitedToReachPoint = new ArrayList<>()
    }

    public Position(int x, int y) {
        this()
        xpos = x
        ypos = y
    }

    public Position(Position other) {
        this.xpos = other.xpos
        this.ypos = other.ypos
        this.positionsVisitedToReachPoint = new ArrayList<>(other.positionsVisitedToReachPoint)
        this.positionsVisitedToReachPoint.add(other)
    }

    public static Position movePosition(Position other, String step, CardinalDirections changedDirection) {
        Position newPosition
        if(other == null) {
            newPosition = new Position()
        } else {
            newPosition = new Position(other)
        }
        int distanceTravelled = Integer.parseInt(step.substring(1))
        newPosition.navigate(changedDirection, distanceTravelled)
        return newPosition
    }

    private void navigate(CardinalDirections direction, int distanceTravelled) {
        Position nextPosition = new Position(this.xpos, this.ypos);
        for(int i = 0 ; i < distanceTravelled-1 ; i++) {
            move(direction, nextPosition)
            if(this.positionsVisitedToReachPoint.contains(nextPosition)){
                this.stopped = true
                break
            }
            this.positionsVisitedToReachPoint.add(nextPosition)
            nextPosition = new Position(nextPosition.xpos, nextPosition.ypos)
        }
        if(!this.stopped) {
            move(direction, nextPosition)
        }
        this.xpos = nextPosition.xpos
        this.ypos = nextPosition.ypos
        this.stopped = this.stopped || contains(this)
    }

    private void move(CardinalDirections direction, Position nextPosition) {
        switch (direction) {
            case CardinalDirections.EAST:
                nextPosition.xpos++
                break
            case CardinalDirections.WEST:
                nextPosition.xpos--
                break
            case CardinalDirections.NORTH:
                nextPosition.ypos++
                break
            case CardinalDirections.SOUTH:
                nextPosition.ypos--
                break
            default:
                throw new IllegalArgumentException()
        }
    }

    public int calculateDistanceFromStart() {
        int distanceDueEastOrWest = Math.abs(this.xpos)
        int distanceDueNorthOrSouth = Math.abs(this.ypos)
        return distanceDueEastOrWest + distanceDueNorthOrSouth
    }

    public boolean isStopped() {
        return this.stopped
    }

    @Override
    boolean equals(Object obj) {
        if(obj instanceof Position) {
            Position other = (Position)obj
            return other.xpos == this.xpos && other.ypos == this.ypos
        }
        return false
    }


    private boolean contains(Position position) {
        return this.positionsVisitedToReachPoint.contains(position)
    }

}
