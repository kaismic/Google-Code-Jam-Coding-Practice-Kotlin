package codejam2022.qualificationround

import BasePath
import java.io.FileInputStream
import java.util.Scanner

class Node {
    var funFactor: Int = 0
    var next: Node? = null
    var triggered = false
}

fun main() {
//    val sc = Scanner(System.`in`)
    val sc =
        Scanner(FileInputStream(BasePath().basePath + "CodeJam2022/QualificationRound/chain_reactions_sample_ts1_input.txt"))
    sc.nextInt() // skip test case numbers
    var count = 1

    var modules: Array<Node>
    val initiators = mutableListOf<Node>()
    var areInitiators: Array<Boolean>
    var maxPathPair = Pair(mutableListOf<Node>(), mutableListOf<Int>())
    var pointer: Int

    var totalSum: Int


    while (sc.hasNextInt()) {
        print("Case #${count++}: ")

        totalSum = 0
        modules = Array(sc.nextInt()) { Node() }
        areInitiators = Array(modules.size) { true }
        for (node in modules) {
            node.funFactor = sc.nextInt()
        }
        for (node in modules) {
            pointer = sc.nextInt()
            if (pointer != 0) {
                node.next = modules[pointer - 1]
                areInitiators[pointer - 1] = false
            }
        }

        for (i in areInitiators.indices) {
            if (areInitiators[i]) {
                initiators.add(modules[i])
            }
        }

        loop1@ while (initiators.isNotEmpty()) {
            for (i in initiators.indices) {
                var currNode = initiators[i]
                var max = currNode.funFactor

                if (currNode.next == null) {
                    totalSum += max
                    initiators.remove(currNode)
                    maxPathPair = Pair(mutableListOf(), mutableListOf())
                    continue@loop1
                } else {
                    while (currNode.next != null) {
                        if (currNode.next!!.triggered) {
                            break
                        }
                        max = maxOf(currNode.funFactor, max)
                        currNode = currNode.next!!
                    }
                    maxPathPair.first.add(initiators[i])
                    maxPathPair.second.add(max)
                }
            }

            val selectedNode = maxPathPair.first[maxPathPair.second.indexOf(maxPathPair.second.minOrNull())]
            var currNode = selectedNode
            var max = currNode.funFactor
            currNode.triggered = true

            while (currNode.next != null) {
                max = maxOf(currNode.funFactor, max)
                currNode.triggered = true
                if (currNode.next!!.triggered) {
                    break
                }
                currNode = currNode.next!!
            }
            max = maxOf(currNode.funFactor, max)
            currNode.triggered = true

            totalSum += max
            initiators.remove(selectedNode)
            maxPathPair = Pair(mutableListOf(), mutableListOf())
        }
        println(totalSum)
    }
}
