package impl

import org.codehaus.groovy.util.HashCodeHelper

/**
 * Created by emccarthy on 14/12/2016.
 */
class Position {

    int x
    int y
    Set<Position> prevPositions

    Position(int x, int y, Set<Position> previousPositions) {
        this.x = x
        this.y = y
        this.prevPositions = previousPositions
    }

    Position(int x, int y) {
        this(x, y, new LinkedHashSet<Position>())
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        for (Position position : this.prevPositions) {
            builder.append("(").append(position.x).append(", ").append(position.y).append(")\n")
        }
        builder.append("(").append(this.x).append(", ").append(this.y).append(")\n")
        return builder.toString()
    }

    @Override
    boolean equals(Object obj) {
        Position other = obj as Position
        return this.x == other.x && this.y == other.y
    }

    @Override
    int hashCode() {
        int hashCode = HashCodeHelper.initHash()
        hashCode = HashCodeHelper.updateHash(hashCode, this.x) * 17
        hashCode = HashCodeHelper.updateHash(hashCode, this.y) * 57
        return hashCode
    }
}
