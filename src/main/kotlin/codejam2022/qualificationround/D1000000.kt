package codejam2022.qualificationround

import BasePath
import java.io.FileInputStream
import java.util.Scanner

fun main() {
//    val sc = Scanner(System.`in`)
    val sc = Scanner(FileInputStream(BasePath().basePath + "CodeJam2022/QualificationRound/d1000000_sample_ts1_input.txt"))
    sc.nextInt() // skip test case numbers

    var maxStraight: Int
    var dices: Array<Int>
    var count = 1

    while (sc.hasNextInt()) {
        print("Case #${count++}: ")
        maxStraight = 0
        dices = Array(sc.nextInt()) {0}
        for (i in dices.indices) {
            dices[i] = sc.nextInt()
        }
        dices.sort()
        var i = 0
        while (i < dices.size) {
            if (dices[i] > maxStraight) {
                maxStraight++
            }
            i++
        }
        println("$maxStraight")
    }
}