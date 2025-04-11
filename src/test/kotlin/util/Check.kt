package util


fun check(
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