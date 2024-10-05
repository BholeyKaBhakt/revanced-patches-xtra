package io.github.bholeykabhakt.patches.utils

import app.revanced.patcher.Match
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions


fun List<Match>.returnEarly(bool: Boolean = false) {
    val const = if (bool) "0x1" else "0x0"
    this.forEach { match ->
        val stringInstructions = when (match.method.returnType.first()) {
            'L' ->
                """
                        const/4 v0, $const
                        return-object v0
                        """

            'V' -> "return-void"
            'I', 'Z' ->
                """
                        const/4 v0, $const
                        return v0
                        """

            else -> throw Exception("This case should never happen.")
        }

        match.mutableMethod.addInstructions(0, stringInstructions)
    }
}
