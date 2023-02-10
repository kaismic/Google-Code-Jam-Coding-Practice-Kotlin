package codejam2022.qualificationround

import BasePath
import java.io.FileInputStream
import java.util.Scanner

fun main() {
//    val sc = Scanner(System.`in`)
    val sc = Scanner(FileInputStream(BasePath().basePath + "CodeJam2022/QualificationRound/3d_printing_sample_ts1_input.txt"))
    sc.nextInt() // skip test case numbers

    val printers = Array(3) { Array(4) { 0 } }
    val minAmounts = Array(4) { Int.MAX_VALUE }

    var count = 1
    val MIN_AMOUNT = 1_000_000
    var currPrinterInkSum: Int
    var minSum: Int
    var diff: Int

    loop1@ while (sc.hasNextInt()) {
        print("Case #${count++}:")

        minSum = 0
        for (i in minAmounts.indices) {
            minAmounts[i] = Int.MAX_VALUE
        }

        for (i in printers.indices) {
            for (j in printers[i].indices) {
                printers[i][j] = sc.nextInt()
            }
        }

        for (i in printers.indices) {
            currPrinterInkSum = 0
            for (j in printers[i].indices) {
                minAmounts[j] = minOf(printers[i][j], minAmounts[j])
                currPrinterInkSum += printers[i][j]
            }
            if (currPrinterInkSum < MIN_AMOUNT) {
                println(" IMPOSSIBLE")
                continue@loop1
            }
        }

        for (i in minAmounts.indices) {
            minSum += minAmounts[i]
        }
        if (minSum < MIN_AMOUNT) {
            println(" IMPOSSIBLE")
            continue@loop1
        }

        diff = minSum - MIN_AMOUNT
        for (i in minAmounts.indices) {
            if (minAmounts[i] <= diff) {
                diff -= minAmounts[i]
                minAmounts[i] = 0
            } else {
                minAmounts[i] -= diff
                break
            }
        }
        for (i in minAmounts.indices) {
            print(" "+minAmounts[i])
        }
        println()
    }
    sc.close()
}