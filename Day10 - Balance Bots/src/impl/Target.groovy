package impl

import api.Targets

/**
 * Created by eoin on 11/12/2016.
 */
class Target {

    Targets target
    Integer chip
    Integer targetId

    Target(Targets targets, Integer target){
        this.target = targets
        this.targetId = target
    }

    Targets getTargets(){
        this.target
    }

    Integer getChip() {
        this.chip
    }

    void setChip(Integer chip)  {
        this.chip = chip
    }

    Integer getTargetId() {
        this.targetId
    }

    @Override
    String toString() {
        String.format("Moving chip %s to %s %s ", getChip(), getTargets(), getTargetId())
    }
}
