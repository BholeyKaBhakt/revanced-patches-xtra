package io.github.bholeykabhakt.patches.autosync.temperdetection.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

internal object MainActivityOnResume : MethodFingerprint(
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/ttxapps/autosync/app/MainActivity;" && methodDef.name == "onResume"
    }
)