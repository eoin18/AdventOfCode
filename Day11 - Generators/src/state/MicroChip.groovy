package state

import org.codehaus.groovy.util.HashCodeHelper

/**
 * Created by emccarthy on 12/12/2016.
 */
class MicroChip {

    String type

    MicroChip(String type) {
        this.type = type
    }

    @Override
    boolean equals(Object obj) {
        MicroChip other = obj as MicroChip
        return this.type.equals(other.type)
    }

    @Override
    int hashCode() {
        int hashCode = HashCodeHelper.initHash()
        hashCode = HashCodeHelper.updateHash(hashCode, this.type)
        return hashCode
    }
}
