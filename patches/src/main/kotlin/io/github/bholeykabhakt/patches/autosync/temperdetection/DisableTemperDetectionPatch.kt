package io.github.bholeykabhakt.patches.autosync.temperdetection

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.bytecodePatch

@Suppress("unused")
val disableTemperDetectionPatch = bytecodePatch(
    name = "Disable Temper Detection",
) {
    compatibleWith("com.ttxapps.autosync")

    val temperDetectionVarHGetterMatch by temperDetectionVarHGetterFingerprint()
    val temperDetectionVarZGetterMatch by temperDetectionVarZGetterFingerprint()

    execute {
        val returnFalseInstructions = """
               const/4 v0, 0x0
               return v0
            """

        // d$a.f() = false (just like old z.g = false but on getter)
        temperDetectionVarHGetterMatch.mutableMethod.addInstructions(
            0,
            returnFalseInstructions
        )

        // SyncState.z() = false (just like old b0.a = false but on getter)
        temperDetectionVarZGetterMatch.mutableMethod.addInstructions(
            0,
            returnFalseInstructions
        )
    }
}