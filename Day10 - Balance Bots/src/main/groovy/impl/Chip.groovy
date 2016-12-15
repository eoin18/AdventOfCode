package impl

/**
 * Created by eoin on 11/12/2016.
 */
class Chip {

    Integer value

    Chip(Integer value) {
        this.value = value
    }

    Integer getValue() {
        return this.value
    }

    @Override
    boolean equals(Object obj) {
        Chip other = obj as Chip
        return this.value.equals(other.value)
    }

    @Override
    String toString() {
        return "Chip " + value
    }
}
