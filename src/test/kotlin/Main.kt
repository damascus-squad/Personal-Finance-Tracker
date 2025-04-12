import test.TestCategory
import org.example.TestTransaction
import test.TestSummary
import util.runAllCheckTests

fun main() {
    val isAllTestCasedPassed = runAllCheckTests(
        listOf(
            TestCategory(),
            TestTransaction(),
            TestSummary()
        )
    )

    if (!isAllTestCasedPassed) {
        println("❌ Some tests failed.")
        kotlin.system.exitProcess(1)
    } else {
        println("✅ All tests passed.")
    }
}