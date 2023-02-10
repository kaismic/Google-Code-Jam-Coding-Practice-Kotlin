package codejam2022.qualificationround

import BasePath
import java.io.FileInputStream
import java.util.Scanner

fun main() {
//    val sc = Scanner(System.`in`)
    val sc = Scanner(FileInputStream(BasePath().basePath + "CodeJam2022/QualificationRound/punched_cards_sample_ts1_input.txt"))
    sc.nextInt() // skip test case numbers
    var count = 1
    var row: Int
    var col: Int
    while (sc.hasNextInt()) {
        println("Case #${count++}:")

        row = sc.nextInt()
        col = sc.nextInt()

        print("..+")
        for (i in 1 until col) {
            print("-+")
        }
        println()
        print("..|")
        for (i in 1 until col) {
            print(".|")
        }
        println()
        print("+")
        for (i in 0 until col) {
            print("-+")
        }
        println()

        for (i in 2 until row*2) {
            if (i % 2 == 0) {
                print("|")
                for (j in 0 until col) {
                    print(".|")
                }
            } else {
                print("+")
                for (j in 0 until col) {
                    print("-+")
                }
            }
            println()
        }
    }
    sc.close()
}
