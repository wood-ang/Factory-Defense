package com.wood.FactoryDefense.StaticTools

fun toStringTool (variable: Any, variableName: String, indent: Int = 0): String {
    return "    ".repeat(indent)+"[$variableName] $variable\n"
}
