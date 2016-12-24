package impl

/**
 * Created by eoin on 24/12/2016.
 */
class GridPosition {

    static final String UP = "U"
    static final String DOWN = "D"
    static final String LEFT = "L"
    static final String RIGHT = "R"

    int x
    int y
    String path

    GridPosition(int x, int y) {
        this(x, y, "")
    }

    GridPosition(int x, int y, String path) {
        this.x = x
        this.y = y
        this.path = path
    }

    boolean isEndPoint(){
        return this.x == 3 && this.y == 3
    }

    List<GridPosition> getNextValidPositions(String passcode){
        List<GridPosition> validNextPositions = new ArrayList<>()
        RoomStatus roomStatus = RoomStatus.buildRoomStatus(this, passcode)
        if (roomStatus.upValid) {
            validNextPositions.add(new GridPosition(this.x, this.y - 1, this.path + UP))
        }
        if (roomStatus.downValid) {
            validNextPositions.add(new GridPosition(this.x, this.y + 1, this.path + DOWN))
        }
        if (roomStatus.leftValid) {
            validNextPositions.add(new GridPosition(this.x - 1, this.y, this.path + LEFT))
        }
        if (roomStatus.rightValid) {
            validNextPositions.add(new GridPosition(this.x + 1, this.y, this.path + RIGHT))
        }
        return validNextPositions
    }

}
