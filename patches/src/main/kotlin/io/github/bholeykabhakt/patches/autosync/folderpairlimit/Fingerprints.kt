package io.github.bholeykabhakt.patches.autosync.folderpairlimit

import app.revanced.patcher.fingerprint

internal val syncSettingsBFingerprint = fingerprint {
    returns("V")
    parameters()
    strings("PREF_UPGRADED_AT")
    custom { methodDef, _ ->
        methodDef.definingClass.startsWith("Lcom/ttxapps/autosync/sync/SyncSettings") && methodDef.name == "b"
    }
}

internal val syncSettingsGetLastUpdatedAtFingerprint = fingerprint {
    returns("J")
    parameters()
    custom { methodDef, _ ->
        methodDef.definingClass == "Lcom/ttxapps/autosync/sync/SyncSettings;"
    }
    strings("PREF_LAST_UPDATED_AT")
//    opcodes(
//        Opcode.IGET_OBJECT,
//        Opcode.CONST_STRING,
//        Opcode.CONST_WIDE_16,
//        Opcode.INVOKE_INTERFACE,
//        Opcode.MOVE_RESULT_WIDE,
//        Opcode.RETURN_WIDE
//    )
}
