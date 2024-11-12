package io.github.bholeykabhakt.patches.utils

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.util.proxy.mutableTypes.MutableMethod


fun MutableMethod.returnEarly(retVal: String = "0x0") {
    val stringInstructions = when (this.returnType.first()) {
        'L' ->
            """
                const/4 v0, $retVal
                return-object v0
            """

        'V' -> "return-void"
        'I', 'Z' ->
            """
                const/4 v0, $retVal
                return v0
            """

        else -> throw Exception("This case should never happen.")
    }

    this.addInstructions(0, stringInstructions)
}
