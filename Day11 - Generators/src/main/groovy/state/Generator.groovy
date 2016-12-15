package state

import org.codehaus.groovy.util.HashCodeHelper

/**
 * Created by emccarthy on 12/12/2016.
 */
class Generator {

    String type

    Generator(String type) {
        this.type = type
    }

    @Override
    boolean equals(Object obj) {
        Generator other = obj as Generator
        return this.type.equals(other.type)
    }

    @Override
    int hashCode() {
        int hashCode = HashCodeHelper.initHash()
        hashCode = HashCodeHelper.updateHash(hashCode, this.type)
        return hashCode
    }
}
