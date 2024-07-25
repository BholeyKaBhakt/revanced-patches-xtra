package io.github.bholeykabhakt.patches.autosync.temperdetection

import app.revanced.patcher.fingerprint

internal val temperDetectionVarHGetterFingerprint = fingerprint {
    returns("Z")
    parameters()
    custom { methodDef, _ ->
        methodDef.definingClass == "Lcom/ttxapps/autosync/sync/d\$a;" && methodDef.name == "f"
    }
}

internal val temperDetectionVarZGetterFingerprint = fingerprint {
    returns("Z")
    parameters()
    custom { methodDef, _ ->
        methodDef.definingClass.startsWith("Lcom/ttxapps/autosync/sync/SyncState") && methodDef.name == "z"
    }
}