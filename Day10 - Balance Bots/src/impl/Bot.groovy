package impl

/**
 * Created by eoin on 11/12/2016.
 */
class Bot {

    SortedSet<Chip> chips
    Instruction command
    Integer value

    Bot(Integer value) {
        this.chips = new TreeSet<>(new Comparator<Chip>() {
            @Override
            int compare(Chip o1, Chip o2) {
                return o1.getValue().compareTo(o2.getValue())
            }
        })
        this.value = value
    }

    void addChip(Chip chip){
        this.chips.add(chip)
        maybeExecuteCommand()
    }

    Chip removeChip(Integer value) {
        Chip toRemove = new Chip(value)
        this.chips.remove(toRemove)
        return toRemove
    }

    void addInstruction(Instruction instruction) {
        this.command = instruction
        maybeExecuteCommand()
    }

    void maybeExecuteCommand(){
        if(this.chips.size() == 2 && this.command != null){
            Iterator<Chip> iterator = this.chips.iterator()
            this.command.getLowTarget().setChip(iterator.next().getValue())
            this.command.getHighTarget().setChip(iterator.next().getValue())
            this.command.execute()
        }
    }

    Integer getValue() {
        return this.value
    }

}
