package io.github.bholeykabhakt.patches.autosync.temperdetection

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.util.exception
import io.github.bholeykabhakt.patches.autosync.temperdetection.fingerprints.TemperDetectionVarHGetterFingerprint
import io.github.bholeykabhakt.patches.autosync.temperdetection.fingerprints.TemperDetectionVarZGetterFingerprint


@Patch(
    name = "Disable Temper Detection",
    compatiblePackages = [CompatiblePackage("com.ttxapps.autosync")]
)

object DisableTemperDetectionPatch : BytecodePatch(
    setOf(TemperDetectionVarHGetterFingerprint, TemperDetectionVarZGetterFingerprint)
) {
    override fun execute(context: BytecodeContext) {

        val returnFalseInstructions = """
               const/4 v0, 0x0
               return v0
            """

        // d$a.f() = false (just like old z.g = false but on getter)
        TemperDetectionVarHGetterFingerprint.result?.mutableMethod?.addInstructions(
            0,
            returnFalseInstructions
        ) ?: throw TemperDetectionVarHGetterFingerprint.exception

        // SyncState.z() = false (just like old b0.a = false but on getter)
        TemperDetectionVarZGetterFingerprint.result?.mutableMethod?.addInstructions(
            0,
            returnFalseInstructions
        ) ?: throw TemperDetectionVarZGetterFingerprint.exception
    }
}