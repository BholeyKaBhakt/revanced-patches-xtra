package io.github.bholeykabhakt.patches.autosync.folderpairlimit.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

internal object SyncSettingsBFingerprint : MethodFingerprint(
    parameters = listOf(),
    returnType = "V",
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass.startsWith("Lcom/ttxapps/autosync/sync/SyncSettings") && methodDef.name == "b"
    },
    strings = listOf("PREF_UPGRADED_AT")
)

internal object SyncSettingsGetLastUpdatedAtFingerprint : MethodFingerprint(
    parameters = listOf(),
    returnType = "J",
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/ttxapps/autosync/sync/SyncSettings;"
    },
    strings = listOf("PREF_LAST_UPDATED_AT"),
//    opcodes = listOf(
//        Opcode.IGET_OBJECT,
//        Opcode.CONST_STRING,
//        Opcode.CONST_WIDE_16,
//        Opcode.INVOKE_INTERFACE,
//        Opcode.MOVE_RESULT_WIDE,
//        Opcode.RETURN_WIDE
//    )
)
