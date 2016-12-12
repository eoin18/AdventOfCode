package impl

/**
 * Created by emccarthy on 12/12/2016.
 */
class Comparison {

    Integer high
    Integer low

    Comparison(Integer high, Integer low) {
        this.high = high
        this.low = low
    }

    @Override
    boolean equals(Object obj) {
        Comparison other = obj as Comparison
        return this.high.equals(other.high) && this.low.equals(other.low)
    }
}
