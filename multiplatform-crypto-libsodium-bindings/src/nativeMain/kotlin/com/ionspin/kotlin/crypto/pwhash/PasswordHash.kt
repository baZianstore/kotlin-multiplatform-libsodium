package com.ionspin.kotlin.crypto.pwhash

import com.ionspin.kotlin.crypto.util.toPtr
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.pin
import libsodium.crypto_pwhash
import libsodium.crypto_pwhash_str
import libsodium.crypto_pwhash_str_needs_rehash

actual object PasswordHash {
    /**
     * The crypto_pwhash() function derives an outlen bytes long key from a password passwd whose length is passwdlen
     * and a salt salt whose fixed length is crypto_pwhash_SALTBYTES bytes. passwdlen should be at least crypto_pwhash_
     * PASSWD_MIN and crypto_pwhash_PASSWD_MAX. outlen should be at least crypto_pwhash_BYTES_MIN = 16 (128 bits) and
     * at most crypto_pwhash_BYTES_MAX.
     *
     * See https://libsodium.gitbook.io/doc/password_hashing/default_phf for more details
     */
    actual fun pwhash(
        outputLength: Int,
        password: String,
        salt: UByteArray,
        opsLimit: ULong,
        memLimit: Int,
        algorithm: Int
    ) : UByteArray {
        val hashedPassword = UByteArray(outputLength)

        val hashedPasswordPinned = hashedPassword.pin()
        val saltPinned = salt.pin()

        crypto_pwhash(
            hashedPasswordPinned.toPtr(),
            outputLength.convert(),
            password,
            password.length.convert(),
            saltPinned.toPtr(),
            opsLimit,
            memLimit.convert(),
            algorithm
        )
        saltPinned.unpin()
        hashedPasswordPinned.unpin()

        return hashedPassword
    }

    /**
     * The crypto_pwhash_str() function puts an ASCII encoded string into out, which includes:
     * the result of a memory-hard, CPU-intensive hash function applied to the   password passwd of length passwdlen
     * the automatically generated salt used for the previous computation
     * the other parameters required to verify the password, including the algorithm identifier, its version, opslimit and memlimit.
     * out must be large enough to hold crypto_pwhash_STRBYTES bytes, but the actual output string may be shorter.
     * The output string is zero-terminated, includes only ASCII characters and can be safely stored into SQL databases
     * and other data stores. No extra information has to be stored in order to verify the password.
     * The function returns 0 on success and -1 if it didn't complete successfully.
     */
    actual fun str(password: String, opslimit: ULong, memlimit: Int): String {
        val output = ByteArray(crypto_pwhash_STRBYTES.toInt())
        val outputPinned = output.pin()
        crypto_pwhash_str(
            outputPinned.addressOf(0),
            password,
            password.length.convert(),
            opslimit,
            memlimit.convert()
        )
        outputPinned.unpin()

        return output.decodeToString()
    }

    /**
     * Check if a password verification string str matches the parameters opslimit and memlimit, and the current default algorithm.
     * The function returns 1 if the string appears to be correct, but doesn't match the given parameters. In that situation, applications may want to compute a new hash using the current parameters the next time the user logs in.
     * The function returns 0 if the parameters already match the given ones.
     * It returns -1 on error. If it happens, applications may want to compute a correct hash the next time the user logs in.
     */
    actual fun strNeedsRehash(
        password: String,
        opslimit: ULong,
        memlimit: Int
    ): Boolean {
        val password = password.encodeToByteArray()
        crypto_pwhash_str_needs_rehash(
            ,
            opslimit,
            memlimit.convert()
        )
    }

    actual fun strVerify(passwordHash: String, password: UByteArray): Boolean {
        TODO("Not yet implemented")
    }

}