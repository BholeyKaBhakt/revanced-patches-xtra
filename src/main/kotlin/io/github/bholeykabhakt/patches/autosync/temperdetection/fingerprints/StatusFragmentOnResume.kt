package io.github.bholeykabhakt.patches.autosync.temperdetection.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

internal object StatusFragmentOnResume : MethodFingerprint(
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/ttxapps/autosync/status/StatusFragment;" && methodDef.name == "onResume"
    }
)