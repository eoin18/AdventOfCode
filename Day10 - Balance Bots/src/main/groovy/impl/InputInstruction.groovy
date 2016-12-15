package impl

/**
 * Created by eoin on 11/12/2016.
 */
class InputInstruction {

    Integer targetId
    Integer targetChipId

    InputInstruction(Integer targetId, Integer targetChipId) {
        this.targetId = targetId
        this.targetChipId = targetChipId
    }

    Integer getTargetId() {
        this.targetId
    }

    Integer getTargetChipId() {
        this.targetChipId
    }

}
