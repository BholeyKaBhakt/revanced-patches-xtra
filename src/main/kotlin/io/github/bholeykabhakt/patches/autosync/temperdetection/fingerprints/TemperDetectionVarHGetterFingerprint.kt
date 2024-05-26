package io.github.bholeykabhakt.patches.autosync.temperdetection.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

internal object TemperDetectionVarHGetterFingerprint : MethodFingerprint(
    parameters = listOf(),
    returnType = "Z",
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/ttxapps/autosync/sync/d\$a;" && methodDef.name == "f"
    }
)
