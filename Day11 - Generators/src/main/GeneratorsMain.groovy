package main

import search.BreadthFirstStateTransition
import state.FloorState
import state.Generator
import state.MicroChip
import state.State

/**
 * Created by emccarthy on 12/12/2016.
 */
class GeneratorsMain {

    public static void main(String[] args) {
        State initialState = initState()
        BreadthFirstStateTransition transition = new BreadthFirstStateTransition()
        System.out.println("Result: " + transition.transition(initialState))
    }


        static State initState() {
        Map<Integer, FloorState> floorStateMap = new HashMap<>()

        FloorState firstFloorState = new FloorState()
        firstFloorState.moveGeneratorTo(new Generator("Polonium"))
        firstFloorState.moveGeneratorTo(new Generator("Thulium"))
        firstFloorState.moveMicroChipTo(new MicroChip("Thulium"))
        firstFloorState.moveGeneratorTo(new Generator("Promethium"))
        firstFloorState.moveGeneratorTo(new Generator("Ruthenium"))
        firstFloorState.moveMicroChipTo(new MicroChip("Ruthenium"))
        firstFloorState.moveGeneratorTo(new Generator("Cobalt"))
        firstFloorState.moveMicroChipTo(new MicroChip("Cobalt"))
        firstFloorState.moveGeneratorTo(new Generator("Elerium"))
        firstFloorState.moveMicroChipTo(new MicroChip("Elerium"))
        firstFloorState.moveGeneratorTo(new Generator("Dilithium"))
        firstFloorState.moveMicroChipTo(new MicroChip("Dilithium"))
        floorStateMap.put(1, firstFloorState)

        FloorState secondFloorState = new FloorState()
        secondFloorState.moveMicroChipTo(new MicroChip("Polonium"))
        secondFloorState.moveMicroChipTo(new MicroChip("Promethium"))
        floorStateMap.put(2, secondFloorState)

        FloorState thirdFloorState = new FloorState()
        floorStateMap.put(3, thirdFloorState)

        FloorState fourthFloorState = new FloorState()
        floorStateMap.put(4, fourthFloorState)
        return new State(Integer.valueOf(1), floorStateMap, 0)
    }
}
