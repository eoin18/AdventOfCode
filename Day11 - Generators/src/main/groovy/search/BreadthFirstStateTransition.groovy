package search

import state.State

import java.util.stream.Collectors

/**
 * Created by emccarthy on 12/12/2016.
 */
class BreadthFirstStateTransition {

    Set<State> visitedStates

    public BreadthFirstStateTransition(){
        visitedStates = new HashSet<>()
    }

    int transition(State initialState) {
        Queue<State> stateQueue = new LinkedList<>()
        stateQueue.add(initialState)
        while (!stateQueue.isEmpty()){
            State currentState = stateQueue.remove(0)
            if(currentState.isSuccessState()){
                println currentState.visitedStates
                print currentState
                return currentState.stateDepth
            }
            if(!visitedStates.contains(currentState)){
                stateQueue.addAll(currentState.getValidNextStates().stream().filter({
                    State s -> !visitedStates.contains(s)
                }).collect(Collectors.toList()))
                visitedStates.add(currentState)
            }
        }
        return -1
    }

}
