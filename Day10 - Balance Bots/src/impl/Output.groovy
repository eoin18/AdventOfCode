package impl

/**
 * Created by eoin on 11/12/2016.
 */
class Output {

    Integer value
    List<Chip> chips

    Output(Integer value) {
        this.value = value
        this.chips = new ArrayList<>()
    }

    Integer getValue() {
        return this.value
    }

    void addChip(Chip chip){
        this.chips.add(chip)
    }
}
