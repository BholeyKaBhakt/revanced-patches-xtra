package io.github.bholeykabhakt.patches.autosync.folderpairlimit.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

internal object SyncSettingsBFingerprint : MethodFingerprint(
    parameters = listOf(),
    returnType = "V",
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/ttxapps/autosync/sync/SyncSettings;" && methodDef.name== "b"
    }
)

internal object SyncSettingsJFingerprint : MethodFingerprint(
    parameters = listOf(),
    returnType = "J",
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/ttxapps/autosync/sync/SyncSettings;" && methodDef.name== "j"
    },
)