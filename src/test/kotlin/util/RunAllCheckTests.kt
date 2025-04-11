package util

import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.findAnnotation


fun runAllCheckTests(instances: List<Any>): Boolean {
    var isAllTestCasedPassed = true

    for (instance in instances) {
        val featureClass = instance::class
        println("🔍 Running tests in: ${featureClass.simpleName}")

        for (function in featureClass.declaredFunctions) {
            if (function.findAnnotation<CheckTest>() != null) {
                val result = function.call(instance)
                if (result == true) {
                    println("✅ PASS - ${function.name}")
                } else {
                    println("❌ FAIL - ${function.name}")
                    isAllTestCasedPassed = false
                }
            }
        }

        println()
    }

    return isAllTestCasedPassed
}