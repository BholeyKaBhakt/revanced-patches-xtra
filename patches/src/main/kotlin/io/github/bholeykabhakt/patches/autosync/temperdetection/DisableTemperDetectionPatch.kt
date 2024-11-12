package io.github.bholeykabhakt.patches.autosync.temperdetection

import app.revanced.patcher.patch.bytecodePatch
import io.github.bholeykabhakt.patches.utils.returnEarly

@Suppress("unused")
val disableTemperDetectionPatch = bytecodePatch(
    name = "Disable Temper Detection",
) {
    compatibleWith("com.ttxapps.autosync")

    execute {

        // VarH => d$a.f() = false (just like old z.g = false but on getter)
        // VarZ => SyncState.z() = false (just like old b0.a = false but on getter)

        temperDetectionVarHGetterFingerprint.method.returnEarly("0x0")
        temperDetectionVarZGetterFingerprint.method.returnEarly("0x0")
    }
}