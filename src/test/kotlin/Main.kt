import test.TestCategory
import util.runAllCheckTests

fun main() {
    val isAllTestCasedPassed = runAllCheckTests(
        listOf(
            TestCategory()
        )
    )

    if (!isAllTestCasedPassed) {
        println("❌ Some tests failed.")
        kotlin.system.exitProcess(1)
    } else {
        println("✅ All tests passed.")
    }
}