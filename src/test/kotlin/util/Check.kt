package util

import org.example.Transaction


internal fun check(
    name: String,
    result: Any?,
    correctResult: Any?
): Boolean {
    return if (result == correctResult) {
        println("✅ PASS - $name")
        true
    } else {
        println("❌ FAIL - $name")
        println("   ↪ Expected: $correctResult")
        println("   ↪ Actual:   $result")
        false
    }
}

internal fun List<Transaction>.checkList(name: String, other:List<Transaction>) {
    if (size == other.size) {
        println("success - $name | found: $size transactions")
    } else {
        println("failed - ($name)... it should return ${other.size} transactions but found: $size")
    }
}