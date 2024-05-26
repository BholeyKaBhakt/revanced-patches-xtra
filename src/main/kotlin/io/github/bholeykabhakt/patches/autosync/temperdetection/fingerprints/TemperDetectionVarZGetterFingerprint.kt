package io.github.bholeykabhakt.patches.autosync.temperdetection.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

internal object TemperDetectionVarZGetterFingerprint : MethodFingerprint(
    parameters = listOf(),
    returnType = "Z",
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass.startsWith("Lcom/ttxapps/autosync/sync/SyncState") && methodDef.name == "z"
    }
)
