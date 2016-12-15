package state

import org.codehaus.groovy.util.HashCodeHelper

/**
 * Created by emccarthy on 12/12/2016.
 */
class State {

    Integer elevatorFloor
    Map<Integer, FloorState> floorStateMap
    Set<State> visitedStates
    int stateDepth = 0

    State(Integer elevatorFloor, Map<Integer, FloorState> floorStateMap, int stateDepth, Set<State> visitedStates){
        this.elevatorFloor = elevatorFloor
        this.floorStateMap = floorStateMap
        this.stateDepth = stateDepth
        this.visitedStates = visitedStates
    }

    State(Integer elevatorFloor, Map<Integer, FloorState> floorStateMap, int stateDepth){
        this(elevatorFloor, floorStateMap, stateDepth, new LinkedHashSet<State>())
    }

    boolean isValid() {
        for(FloorState floorState : floorStateMap.values()) {
            if (!floorState.isValid()){
                return false
            }
        }
        return true
    }

    //Success state if all floors are valid - and the fourth floor is the only one with anything on it
    boolean isSuccessState() {
        boolean success = isValid()
        for(int i = 1 ; i < 4 ; i++){
            success &= floorStateMap.get(Integer.valueOf(i)).isEmpty()
        }
        return success
    }

    Set<State> getValidNextStates(){
        Set<State> validNextStates = new HashSet<>()
        if (this.elevatorFloor.intValue() == 1) {
            validNextStates.addAll(testMovingUpwardsStateTransitions())
        } else if (this.elevatorFloor.intValue() ==4) {
            validNextStates.addAll(testMovingDownwardsStateTransitions())
        } else {
            validNextStates.addAll(testMovingDownwardsStateTransitions())
            validNextStates.addAll(testMovingUpwardsStateTransitions())
        }
        return validNextStates
    }

    Set<State> testMovingUpwardsStateTransitions() {
        Set<State> validUpwardsState = new HashSet<>()
        Integer targetElevatorFloor = Integer.valueOf(this.elevatorFloor.intValue()+1)
        validUpwardsState.addAll(testMovingSingleGeneratorToTargetFloor(targetElevatorFloor))
        validUpwardsState.addAll(testMovingMultipleGeneratorsToTargetFloor(targetElevatorFloor) )
        validUpwardsState.addAll(testMovingSingleMicroChipToTargetFloor(targetElevatorFloor))
        validUpwardsState.addAll(testMovingMultipleMicroChipToTargetFloor(targetElevatorFloor))
        validUpwardsState.addAll(testMovingMixedToTargetFloor(targetElevatorFloor))

        return validUpwardsState
    }

    Set<State> testMovingDownwardsStateTransitions(){
        Set<State> validUpwardsState = new HashSet<>()
        Integer targetElevatorFloor = Integer.valueOf(this.elevatorFloor.intValue()-1)
        validUpwardsState.addAll(testMovingSingleGeneratorToTargetFloor(targetElevatorFloor))
        validUpwardsState.addAll(testMovingMultipleGeneratorsToTargetFloor(targetElevatorFloor) )
        validUpwardsState.addAll(testMovingSingleMicroChipToTargetFloor(targetElevatorFloor))
        validUpwardsState.addAll(testMovingMultipleMicroChipToTargetFloor(targetElevatorFloor))
        validUpwardsState.addAll(testMovingMixedToTargetFloor(targetElevatorFloor))
        return validUpwardsState
    }

    Set<State> testMovingMixedToTargetFloor(Integer floor) {
        Set<State> validStates = new HashSet<>()
        FloorState currentFloor = this.floorStateMap.get(this.elevatorFloor)
        if(!currentFloor.microChipMap.isEmpty() && !currentFloor.generatorMap.isEmpty()) {
            currentFloor.getMicroChipMap().keySet().each { String microChip ->
                currentFloor.getGeneratorMap().keySet().each { String generator ->
                    Map<Integer, FloorState> cloneFloorState = deepCloneFloorState(this.floorStateMap)
                    FloorState clonedCurrentFloor = cloneFloorState.get(this.elevatorFloor)
                    FloorState clonedTargetFloor = cloneFloorState.get(floor)
                    clonedTargetFloor.moveMicroChipTo(clonedCurrentFloor.moveMicroChipFrom(microChip))
                    clonedTargetFloor.moveGeneratorTo(clonedCurrentFloor.moveGeneratorFrom(generator))
                    State resultState = newState(floor, cloneFloorState)
                    if(resultState.isValid() && !this.visitedStates.contains(resultState)) {
                        validStates.add(resultState)
                    }
                }
            }
        }
        return validStates
    }

    Set<State> testMovingMultipleMicroChipToTargetFloor(Integer floor) {
        Set<State> validStates = new HashSet<>()
        FloorState currentFloor = this.floorStateMap.get(this.elevatorFloor)
        if (currentFloor.getMicroChipMap().size() > 1) {
            currentFloor.getMicroChipMap().keySet().each { String microChip ->
                currentFloor.getMicroChipMap().keySet().each {String secondMicroChip ->
                    if(!microChip.equals(secondMicroChip)){
                        Map<Integer, FloorState> cloneFloorState = deepCloneFloorState(this.floorStateMap)
                        FloorState clonedCurrentFloor = cloneFloorState.get(this.elevatorFloor)
                        FloorState clonedTargetFloor = cloneFloorState.get(floor)
                        clonedTargetFloor.moveMicroChipTo(clonedCurrentFloor.moveMicroChipFrom(microChip))
                        clonedTargetFloor.moveMicroChipTo(clonedCurrentFloor.moveMicroChipFrom(secondMicroChip))
                        State resultState = newState(floor, cloneFloorState)
                        if(resultState.isValid() && !this.visitedStates.contains(resultState)) {
                            validStates.add(resultState)
                        }
                    }
                }
            }
        }
        return validStates
    }

    Set<State> testMovingSingleMicroChipToTargetFloor(Integer floor) {
        Set<State> validStates = new HashSet<>()
        FloorState currentFloor = this.floorStateMap.get(this.elevatorFloor)
        currentFloor.getMicroChipMap().keySet().each { String microChip ->
            Map<Integer, FloorState> cloneFloorState = deepCloneFloorState(this.floorStateMap)
            FloorState clonedCurrentFloor = cloneFloorState.get(this.elevatorFloor)
            FloorState clonedTargetFloor = cloneFloorState.get(floor)
            clonedTargetFloor.moveMicroChipTo(clonedCurrentFloor.moveMicroChipFrom(microChip))
            State resultState = newState(floor, cloneFloorState)
            if(resultState.isValid() && !this.visitedStates.contains(resultState)) {
                validStates.add(resultState)
            }
        }
        return validStates
    }

    Set<State> testMovingMultipleGeneratorsToTargetFloor(Integer floor) {
        Set<State> validStates = new HashSet<>()
        FloorState currentFloor = this.floorStateMap.get(this.elevatorFloor)
        if (currentFloor.getGeneratorMap().size() > 1) {
            currentFloor.getGeneratorMap().keySet().each { String generator ->
                currentFloor.getGeneratorMap().keySet().each {String secondGenerator ->
                    if(!generator.equals(secondGenerator)){
                        Map<Integer, FloorState> cloneFloorState = deepCloneFloorState(this.floorStateMap)
                        FloorState clonedCurrentFloor = cloneFloorState.get(this.elevatorFloor)
                        FloorState clonedTargetFloor = cloneFloorState.get(floor)
                        clonedTargetFloor.moveGeneratorTo(clonedCurrentFloor.moveGeneratorFrom(generator))
                        clonedTargetFloor.moveGeneratorTo(clonedCurrentFloor.moveGeneratorFrom(secondGenerator))
                        State resultState = newState(floor, cloneFloorState)
                        if(resultState.isValid() && !this.visitedStates.contains(resultState)) {
                            validStates.add(resultState)
                        }
                    }
                }
            }
        }
        return validStates
    }

    Set<State> testMovingSingleGeneratorToTargetFloor(Integer floor) {
        Set<State> validStates = new HashSet<>()
        FloorState currentFloor = this.floorStateMap.get(this.elevatorFloor)
        currentFloor.getGeneratorMap().keySet().each { String generator ->
            Map<Integer, FloorState> cloneFloorState = deepCloneFloorState(this.floorStateMap)
            FloorState clonedCurrentFloor = cloneFloorState.get(this.elevatorFloor)
            FloorState clonedTargetFloor = cloneFloorState.get(floor)
            clonedTargetFloor.moveGeneratorTo(clonedCurrentFloor.moveGeneratorFrom(generator))
            State resultState = newState(floor, cloneFloorState)
            if(resultState.isValid() && !this.visitedStates.contains(resultState)) {
                validStates.add(resultState)
            }
        }
        return validStates
    }

    private State newState(Integer targetFloor, Map<Integer, FloorState> clonedFloorState) {
        Set<State> visitedStates = new LinkedHashSet<>(this.visitedStates)
        visitedStates.add(new State(this.elevatorFloor, deepCloneFloorState(this.floorStateMap), this.stateDepth))
        return new State(targetFloor, clonedFloorState, this.stateDepth+1, visitedStates)
    }

    static Map<Integer, FloorState> deepCloneFloorState(Map<Integer, FloorState> toClone) {
        Map<Integer, FloorState> clone = new HashMap<>()
        for (Map.Entry<Integer, FloorState> entry : toClone) {
            clone.put(entry.getKey(), entry.getValue().clone() as FloorState)
        }
        return clone
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        builder.append("Elevator @ " + this.elevatorFloor).append("\n")
        for(Map.Entry<Integer, FloorState> floors : this.floorStateMap) {
            builder.append("Floor " + floors.getKey() + ": " + floors.getValue()).append("\n")
        }
        return builder.toString()
    }

    @Override
    boolean equals(Object obj) {
        State other = obj as State
        return this.elevatorFloor.equals(other.elevatorFloor) && this.floorStateMap.equals(other.floorStateMap)
    }

    @Override
    int hashCode() {
        int hashCode = HashCodeHelper.initHash()
        hashCode = HashCodeHelper.updateHash(hashCode, this.elevatorFloor)
        hashCode = HashCodeHelper.updateHash(hashCode, this.floorStateMap)
        return hashCode
    }
}
