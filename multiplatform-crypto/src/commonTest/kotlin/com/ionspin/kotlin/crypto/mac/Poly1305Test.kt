package com.ionspin.kotlin.crypto.mac

import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Created by Ugljesa Jovanovic
 * ugljesa.jovanovic@ionspin.com
 * on 18-Jun-2020
 */
class Poly1305Test {

    /**
     * From RFC7539
     */
    @Test
    fun testPoly1305() {
        assertTrue {
            val key = ubyteArrayOf(
                0x85U, 0xd6U, 0xbeU, 0x78U, 0x57U, 0x55U, 0x6dU,
                0x33U, 0x7fU, 0x44U, 0x52U, 0xfeU, 0x42U, 0xd5U,
                0x06U, 0xa8U, 0x01U, 0x03U, 0x80U, 0x8aU, 0xfbU,
                0x0dU, 0xb2U, 0xfdU, 0x4aU, 0xbfU, 0xf6U, 0xafU,
                0x41U, 0x49U, 0xf5U, 0x1bU
            )
            val message = ubyteArrayOf(
                0x43U, 0x72U, 0x79U, 0x70U, 0x74U, 0x6fU, 0x67U, 0x72U,
                0x61U, 0x70U, 0x68U, 0x69U, 0x63U, 0x20U, 0x46U, 0x6fU,
                0x72U, 0x75U, 0x6dU, 0x20U, 0x52U, 0x65U, 0x73U, 0x65U,
                0x61U, 0x72U, 0x63U, 0x68U, 0x20U, 0x47U, 0x72U, 0x6fU,
                0x75U, 0x70U
            )
            val expected = ubyteArrayOf(
                0xA8U, 0x06U, 0x1DU, 0xC1U,
                0x30U, 0x51U, 0x36U, 0xC6U,
                0xC2U, 0x2BU, 0x8BU, 0xAFU,
                0x0CU, 0x01U, 0x27U, 0xA9U,
            )

            val result =
                Poly1305.poly1305Authenticate(
                    key,
                    message,
                )
            expected.contentEquals(result)
        }
        assertTrue {
            val key = ubyteArrayOf(
                0x85U, 0x1fU, 0xc4U, 0x0cU, 0x34U, 0x67U, 0xacU, 0x0bU,
                0xe0U, 0x5cU, 0xc2U, 0x04U, 0x04U, 0xf3U, 0xf7U, 0x00U,
                0x58U, 0x0bU, 0x3bU, 0x0fU, 0x94U, 0x47U, 0xbbU, 0x1eU,
                0x69U, 0xd0U, 0x95U, 0xb5U, 0x92U, 0x8bU, 0x6dU, 0xbcU
            )
            val message = ubyteArrayOf(
                0xf3U, 0xf6U
            )
            val expected = ubyteArrayOf(
                0xf4U, 0xc6U, 0x33U, 0xc3U, 0x04U, 0x4fU, 0xc1U, 0x45U,
                0xf8U, 0x4fU, 0x33U, 0x5cU, 0xb8U, 0x19U, 0x53U, 0xdeU
            )

            val result =
                Poly1305.poly1305Authenticate(
                    key,
                    message,
                )
            expected.contentEquals(result)
        }

        assertTrue {
            val key = ubyteArrayOf(
                0x75U, 0xdeU, 0xaaU, 0x25U, 0xc0U, 0x9fU, 0x20U, 0x8eU,
                0x1dU, 0xc4U, 0xceU, 0x6bU, 0x5cU, 0xadU, 0x3fU, 0xbfU,
                0xddU, 0x3fU, 0xabU, 0x22U, 0x51U, 0xf1U, 0x1aU, 0xc7U,
                0x59U, 0xf0U, 0x88U, 0x71U, 0x29U, 0xccU, 0x2eU, 0xe7U,
            )
            val message = ubyteArrayOf(

            )
            val expected = ubyteArrayOf(
                0xddU, 0x3fU, 0xabU, 0x22U, 0x51U, 0xf1U, 0x1aU, 0xc7U,
                0x59U, 0xf0U, 0x88U, 0x71U, 0x29U, 0xccU, 0x2eU, 0xe7U
            )

            val result =
                Poly1305.poly1305Authenticate(
                    key,
                    message,
                )
            expected.contentEquals(result)
        }

        assertTrue {
            val key = ubyteArrayOf(
                0x12U, 0x97U, 0x6aU, 0x08U, 0xc4U, 0x42U, 0x6dU, 0x0cU,
                0xe8U, 0xa8U, 0x24U, 0x07U, 0xc4U, 0xf4U, 0x82U, 0x07U,
                0x80U, 0xf8U, 0xc2U, 0x0aU, 0xa7U, 0x12U, 0x02U, 0xd1U,
                0xe2U, 0x91U, 0x79U, 0xcbU, 0xcbU, 0x55U, 0x5aU, 0x57U
            )
            val message = ubyteArrayOf(
                0xabU, 0x08U, 0x12U, 0x72U, 0x4aU, 0x7fU, 0x1eU, 0x34U,
                0x27U, 0x42U, 0xcbU, 0xedU, 0x37U, 0x4dU, 0x94U, 0xd1U,
                0x36U, 0xc6U, 0xb8U, 0x79U, 0x5dU, 0x45U, 0xb3U, 0x81U,
                0x98U, 0x30U, 0xf2U, 0xc0U, 0x44U, 0x91U, 0xfaU, 0xf0U,
                0x99U, 0x0cU, 0x62U, 0xe4U, 0x8bU, 0x80U, 0x18U, 0xb2U,
                0xc3U, 0xe4U, 0xa0U, 0xfaU, 0x31U, 0x34U, 0xcbU, 0x67U,
                0xfaU, 0x83U, 0xe1U, 0x58U, 0xc9U, 0x94U, 0xd9U, 0x61U,
                0xc4U, 0xcbU, 0x21U, 0x09U, 0x5cU, 0x1bU, 0xf9U,
            )
            val expected = ubyteArrayOf(
                0x51U, 0x54U, 0xadU, 0x0dU, 0x2cU, 0xb2U, 0x6eU, 0x01U,
                0x27U, 0x4fU, 0xc5U, 0x11U, 0x48U, 0x49U, 0x1fU, 0x1bU
            )

            val result =
                Poly1305.poly1305Authenticate(
                    key,
                    message,
                )
            expected.contentEquals(result)
        }
    }

    @Test
    fun testUpdateablePoly1305() {
        assertTrue {
            val key = ubyteArrayOf(
                0x85U, 0xd6U, 0xbeU, 0x78U, 0x57U, 0x55U, 0x6dU,
                0x33U, 0x7fU, 0x44U, 0x52U, 0xfeU, 0x42U, 0xd5U,
                0x06U, 0xa8U, 0x01U, 0x03U, 0x80U, 0x8aU, 0xfbU,
                0x0dU, 0xb2U, 0xfdU, 0x4aU, 0xbfU, 0xf6U, 0xafU,
                0x41U, 0x49U, 0xf5U, 0x1bU
            )
            val message = ubyteArrayOf(
                0x43U, 0x72U, 0x79U, 0x70U, 0x74U, 0x6fU, 0x67U, 0x72U,
                0x61U, 0x70U, 0x68U, 0x69U, 0x63U, 0x20U, 0x46U, 0x6fU,
                0x72U, 0x75U, 0x6dU, 0x20U, 0x52U, 0x65U, 0x73U, 0x65U,
                0x61U, 0x72U, 0x63U, 0x68U, 0x20U, 0x47U, 0x72U, 0x6fU,
                0x75U, 0x70U
            )
            val expected = ubyteArrayOf(
                0xA8U, 0x06U, 0x1DU, 0xC1U,
                0x30U, 0x51U, 0x36U, 0xC6U,
                0xC2U, 0x2BU, 0x8BU, 0xAFU,
                0x0CU, 0x01U, 0x27U, 0xA9U,
            )
            val poly = Poly1305(key)
            poly.updateMac(message.sliceArray(0 until 16))
            poly.updateMac(message.sliceArray(16 until 32))
            val result = poly.finalizeMac(message.sliceArray(32 until 34))

            expected.contentEquals(result)
        }
        assertTrue {
            val key = ubyteArrayOf(
                0x85U, 0x1fU, 0xc4U, 0x0cU, 0x34U, 0x67U, 0xacU, 0x0bU,
                0xe0U, 0x5cU, 0xc2U, 0x04U, 0x04U, 0xf3U, 0xf7U, 0x00U,
                0x58U, 0x0bU, 0x3bU, 0x0fU, 0x94U, 0x47U, 0xbbU, 0x1eU,
                0x69U, 0xd0U, 0x95U, 0xb5U, 0x92U, 0x8bU, 0x6dU, 0xbcU
            )
            val message = ubyteArrayOf(
                0xf3U, 0xf6U
            )
            val expected = ubyteArrayOf(
                0xf4U, 0xc6U, 0x33U, 0xc3U, 0x04U, 0x4fU, 0xc1U, 0x45U,
                0xf8U, 0x4fU, 0x33U, 0x5cU, 0xb8U, 0x19U, 0x53U, 0xdeU
            )

            val poly = Poly1305(key)
            val result = poly.finalizeMac(message)
            expected.contentEquals(result)
        }

        assertTrue {
            val key = ubyteArrayOf(
                0x75U, 0xdeU, 0xaaU, 0x25U, 0xc0U, 0x9fU, 0x20U, 0x8eU,
                0x1dU, 0xc4U, 0xceU, 0x6bU, 0x5cU, 0xadU, 0x3fU, 0xbfU,
                0xddU, 0x3fU, 0xabU, 0x22U, 0x51U, 0xf1U, 0x1aU, 0xc7U,
                0x59U, 0xf0U, 0x88U, 0x71U, 0x29U, 0xccU, 0x2eU, 0xe7U,
            )
            val message = ubyteArrayOf(

            )
            val expected = ubyteArrayOf(
                0xddU, 0x3fU, 0xabU, 0x22U, 0x51U, 0xf1U, 0x1aU, 0xc7U,
                0x59U, 0xf0U, 0x88U, 0x71U, 0x29U, 0xccU, 0x2eU, 0xe7U
            )

            val poly = Poly1305(key)
            val result = poly.finalizeMac(message)
            expected.contentEquals(result)
        }

        assertTrue {
            val key = ubyteArrayOf(
                0x12U, 0x97U, 0x6aU, 0x08U, 0xc4U, 0x42U, 0x6dU, 0x0cU,
                0xe8U, 0xa8U, 0x24U, 0x07U, 0xc4U, 0xf4U, 0x82U, 0x07U,
                0x80U, 0xf8U, 0xc2U, 0x0aU, 0xa7U, 0x12U, 0x02U, 0xd1U,
                0xe2U, 0x91U, 0x79U, 0xcbU, 0xcbU, 0x55U, 0x5aU, 0x57U
            )
            val message = ubyteArrayOf(
                0xabU, 0x08U, 0x12U, 0x72U, 0x4aU, 0x7fU, 0x1eU, 0x34U,
                0x27U, 0x42U, 0xcbU, 0xedU, 0x37U, 0x4dU, 0x94U, 0xd1U,
                0x36U, 0xc6U, 0xb8U, 0x79U, 0x5dU, 0x45U, 0xb3U, 0x81U,
                0x98U, 0x30U, 0xf2U, 0xc0U, 0x44U, 0x91U, 0xfaU, 0xf0U,
                0x99U, 0x0cU, 0x62U, 0xe4U, 0x8bU, 0x80U, 0x18U, 0xb2U,
                0xc3U, 0xe4U, 0xa0U, 0xfaU, 0x31U, 0x34U, 0xcbU, 0x67U,
                0xfaU, 0x83U, 0xe1U, 0x58U, 0xc9U, 0x94U, 0xd9U, 0x61U,
                0xc4U, 0xcbU, 0x21U, 0x09U, 0x5cU, 0x1bU, 0xf9U,
            )
            val expected = ubyteArrayOf(
                0x51U, 0x54U, 0xadU, 0x0dU, 0x2cU, 0xb2U, 0x6eU, 0x01U,
                0x27U, 0x4fU, 0xc5U, 0x11U, 0x48U, 0x49U, 0x1fU, 0x1bU
            )

            val poly = Poly1305(key)
            poly.updateMac(message.sliceArray(0 until 16))
            poly.updateMac(message.sliceArray(16 until 32))
            poly.updateMac(message.sliceArray(32 until 48))
            val result = poly.finalizeMac(message.sliceArray(48 until 63))
            expected.contentEquals(result)
        }
    }
}
