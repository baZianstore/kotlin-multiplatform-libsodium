package debug.test

import com.goterl.lazycode.lazysodium.SodiumJava
import com.goterl.lazycode.lazysodium.interfaces.Hash
import com.goterl.lazycode.lazysodium.interfaces.SecretStream
import kotlin.ByteArray
import kotlin.Int
import kotlin.UByteArray

val sodium: SodiumJava = SodiumJava()

actual typealias Sha256State = Hash.State256

actual typealias Sha512State = Hash.State512

actual typealias GenericHashState = ByteArray

actual typealias SecretStreamState = SecretStream.State

actual class Crypto internal actual constructor() {
  /**
   * Initialize the SHA256 hash
   * returns sha 256 state
   */
  actual fun crypto_hash_sha256_init(): Sha256State {
    val state = debug.test.Sha256State()
    println("Debug crypto_hash_sha256_init")
    sodium.crypto_hash_sha256_init(state)
    return state
  }

  actual fun crypto_hash_sha256_update(state: Sha256State, input: UByteArray) {
    println("Debug crypto_hash_sha256_update")
    sodium.crypto_hash_sha256_update(state, input.asByteArray(), input.size.toLong())
  }

  actual fun crypto_hash_sha256_final(state: Sha256State): UByteArray {
    val out = UByteArray(32)
    println("Debug crypto_hash_sha256_final")
    sodium.crypto_hash_sha256_final(state, out.asByteArray())
    return out
  }

  actual fun crypto_hash_sha512_init(): Sha512State {
    val state = debug.test.Sha512State()
    println("Debug crypto_hash_sha512_init")
    sodium.crypto_hash_sha512_init(state)
    return state
  }

  actual fun crypto_hash_sha512_update(state: Sha512State, input: UByteArray) {
    println("Debug crypto_hash_sha512_update")
    sodium.crypto_hash_sha512_update(state, input.asByteArray(), input.size.toLong())
  }

  actual fun crypto_hash_sha512_final(state: Sha512State): UByteArray {
    val out = UByteArray(64)
    println("Debug crypto_hash_sha512_final")
    sodium.crypto_hash_sha512_final(state, out.asByteArray())
    return out
  }

  actual fun crypto_generichash_init(key: UByteArray, outlen: Int): GenericHashState {
    val state = debug.test.GenericHashState(sodium.crypto_generichash_statebytes())
    println("Debug crypto_generichash_init")
    sodium.crypto_generichash_init(state, key.asByteArray(), key.size, outlen)
    return state
  }

  actual fun crypto_secretstream_xchacha20poly1305_init_push(key: UByteArray): UByteArray {
    println("Debug crypto_secretstream_xchacha20poly1305_init_push")
    sodium.crypto_secretstream_xchacha20poly1305_init_push(key.asByteArray())
    return state
  }
}
