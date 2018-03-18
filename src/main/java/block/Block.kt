package block

import entity.Transaction
import util.toSha256
import java.util.*

/**
 * Holds data and generates and saves the hash string of itself.
 * holds hash of the previous block
 *
 * @param timestamp the timestamp of initialization
 */
data class Block(

        var hash: String,
        var prevHash: String,
        var data: List<Transaction>,
        var timestamp: Long
) {

    init {
        timestamp = Date().time
        hash = calculateHash()
    }

    /**
     * calculates the hash string of timestamp + data as string + the hash of previous block
     */
    public fun calculateHash() =
            (prevHash + timestamp.toString() + data.toString()).toSha256()
}