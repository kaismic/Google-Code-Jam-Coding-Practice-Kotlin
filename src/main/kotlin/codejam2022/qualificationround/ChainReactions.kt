package codejam2022.qualificationround

import BasePath
import java.io.FileInputStream
import java.util.Scanner

object Answer {
    var totalSum: Long = 0
}

class Node {
    var funFactor: Long = 0
    val children = mutableListOf<Node>()

    fun dfs(): Long {
        if (children.isEmpty()) {
            return funFactor
        }

        val childrenValues = Array<Long>(children.size) {0}
        for (i in childrenValues.indices) {
            childrenValues[i] = children[i].dfs()
        }
        childrenValues.sort()

        for (i in 1 until childrenValues.size) {
            Answer.totalSum += childrenValues[i]
        }
        return maxOf(funFactor, childrenValues[0])
    }
}

fun main() {
//    val sc = Scanner(System.`in`)
    val sc =
        Scanner(FileInputStream(BasePath().basePath + "CodeJam2022/QualificationRound/chain_reactions/test_set_2/ts2_input.txt"))

    val testCaseCount = sc.nextInt()
    for (count in 1..testCaseCount) {
        print("Case #${count}: ")
        Answer.totalSum = 0

        val modules = Array(sc.nextInt()+1) { Node() }
        for (i in 1 until modules.size) {
            modules[i].funFactor = sc.nextLong()
        }
        for (i in 1 until modules.size) {
            modules[sc.nextInt()].children.add(modules[i])
        }
        Answer.totalSum += modules[0].dfs() + Answer.totalSum
        println(Answer.totalSum)
    }
}
