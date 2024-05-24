package io.github.bholeykabhakt.patches.autosync.temperdetection.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint


internal object WUFingerprint : MethodFingerprint(
    parameters = listOf("Ljava/lang/String;", "Ljava/lang/String;"),
    returnType = "V",
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/ttxapps/autosync/sync/w;" && methodDef.name == "u"
    }
)