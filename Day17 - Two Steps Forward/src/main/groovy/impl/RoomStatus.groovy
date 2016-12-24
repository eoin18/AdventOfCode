package impl

import java.security.MessageDigest

/**
 * Created by eoin on 24/12/2016.
 */
class RoomStatus {

    boolean leftValid
    boolean rightValid
    boolean upValid
    boolean downValid

    RoomStatus(boolean leftValid, boolean rightValid, boolean upValid, boolean downValid){
        this.leftValid = leftValid
        this.downValid = downValid
        this.upValid = upValid
        this.rightValid = rightValid
    }

    static RoomStatus buildRoomStatus(GridPosition currentPosition, String passcode) {
        String hashInput = passcode + currentPosition.getPath()
        String md5Hash = MessageDigest.getInstance("MD5").digest(hashInput.bytes).encodeHex().toString()
        boolean upValid = currentPosition.y > 0 && isOpenDoor(md5Hash.charAt(0))
        boolean downValid = currentPosition.y < 3 && isOpenDoor(md5Hash.charAt(1))
        boolean leftValid = currentPosition.x > 0 && isOpenDoor(md5Hash.charAt(2))
        boolean rightValid = currentPosition.x < 3 && isOpenDoor(md5Hash.charAt(3))
        return new RoomStatus(leftValid, rightValid, upValid, downValid)
    }

    static boolean isOpenDoor(char c) {
        return c == 'b' as char || c == 'c' as char|| c == 'd' as char|| c == 'e' as char|| c == 'f' as char
    }


}
