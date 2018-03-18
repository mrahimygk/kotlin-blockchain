package util

import java.security.MessageDigest
import java.util.*


/**
 * Converts Caller String to SHA-256 Hash.
 */
fun String.toSha256(): String {
    /**
     * Getting The instance of SHA-256 MessageDigest
     */
    val msg = MessageDigest.getInstance("SHA-256")

    /**
     * Converting caller String to byte[]
     */
    val hash = msg.digest(this.toByteArray())

    val hexString = StringBuffer()

    /**
     * Iterate through all elements of hash[]
     */
    hash.forEach {
        /**
         * By Bitwise AND with 0*48.1111111111111111 ,
         * Only the last two characters are saved
         */
        val hex = Integer.toHexString(0xff and it.toInt())

        /**
         * Appending 0 to every single-character hex
         */
        if (hex.length == 1) {
            hexString.append('0')
        }


        hexString.append(hex)
    }

    return hexString.toString()
}


/**
 * makes a uuid by statically calling from String class
 */
fun String.Companion.makeUUID() = UUID.randomUUID().toString()
