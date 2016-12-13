package state

import org.codehaus.groovy.util.HashCodeHelper

/**
 * Created by emccarthy on 12/12/2016.
 */
class FloorState implements Cloneable {

    Map<String, Generator> generatorMap
    Map<String, MicroChip> microChipMap

    FloorState(){
        generatorMap = new HashMap<>()
        microChipMap = new HashMap<>()
    }

    boolean isValid() {
        if (!microChipMap.isEmpty()) {
            if (!generatorMap.isEmpty()){
                for(Map.Entry<String, MicroChip> microChipEntry : microChipMap) {
                    if(!generatorMap.containsKey(microChipEntry.getKey())) {
                        return false
                    }
                }
            }
        }
        return true
    }

    void moveGeneratorTo(Generator generator){
        this.generatorMap.put(generator.getType(), generator)
    }

    void moveMicroChipTo(MicroChip microChip){
        this.microChipMap.put(microChip.getType(), microChip)
    }

    Generator moveGeneratorFrom(String type) {
        this.generatorMap.remove(type)
    }

    MicroChip moveMicroChipFrom(String type) {
        this.microChipMap.remove(type)
    }

    boolean isEmpty() {
        this.microChipMap.isEmpty() && this.generatorMap.isEmpty()
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        FloorState clone = super.clone() as FloorState
        clone.generatorMap = new HashMap<>(this.generatorMap)
        clone.microChipMap = new HashMap<>(this.microChipMap)
        return clone
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        for(String generator : this.generatorMap.keySet()) {
            builder.append(generator).append(" Generator, ")
        }
        for(String microChip : this.microChipMap.keySet()) {
            builder.append(microChip).append(" Microchip, ")
        }
        return builder.toString()
    }

    @Override
    boolean equals(Object obj) {
        FloorState other = obj as FloorState
        return this.hashCode() == other.hashCode()
    }

    @Override
    int hashCode() {
        //Since a state is basically the same if it has the same amount of matching pairs and singles of each type
        int hashCode = HashCodeHelper.initHash()
        for (String generator : this.generatorMap.keySet()){
            if (microChipMap.containsKey(generator)) {
                hashCode *= 31
            } else {
                hashCode *= 17
            }
        }
        for(String microChip : this.microChipMap.keySet()){
            if(!this.generatorMap.containsKey()) {
                hashCode *= 57
            }
        }
        return hashCode
    }
}
