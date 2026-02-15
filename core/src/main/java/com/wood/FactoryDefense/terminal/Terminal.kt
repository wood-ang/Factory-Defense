package com.wood.FactoryDefense.terminal

class Terminal {
    val logs = mutableListOf<Log>()
    val lines = mutableListOf<String>()

    fun debugOut(){
        logs.forEach { it.debugOutput() }
    }
    fun output(){
        debugOut()
        clear()
    }


}

fun clear() {
    print("\u001b[H\u001b[2J")
    System.out.flush()
}

