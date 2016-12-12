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

    void addChip(Chip chip){
        this.chips.add(chip)
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder("Output ")
            .append(value)
            .append(" contains chips :")
        for (Chip chip : chips) {
            builder.append(chip)
                .append(", ")
        }
        return builder.toString()
    }
}
