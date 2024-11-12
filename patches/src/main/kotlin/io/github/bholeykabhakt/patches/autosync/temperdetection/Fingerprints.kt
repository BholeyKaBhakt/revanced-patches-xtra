package io.github.bholeykabhakt.patches.autosync.temperdetection

import app.revanced.patcher.fingerprint
import com.android.tools.smali.dexlib2.Opcode

internal val temperDetectionVarHGetterFingerprint = fingerprint {
    returns("Z")
    parameters()
    opcodes(
        Opcode.INVOKE_STATIC,
        Opcode.MOVE_RESULT,
        Opcode.RETURN
    )
    custom { method, _ ->
        method.definingClass.startsWith("Lcom/ttxapps/autosync/sync/")
                && method.definingClass.endsWith("a;")
                && method.name == "f"
    }
}

internal val temperDetectionVarZGetterFingerprint = fingerprint {
    returns("Z")
    parameters()
    opcodes(
        Opcode.IGET_BOOLEAN,
        Opcode.RETURN
    )
    custom { method, classDef ->
        method.definingClass.startsWith("Lcom/ttxapps/autosync/sync/") && method.name == "z"
                && classDef.fields.any {
            it.name == "a" && it.type == "Z"
        }
    }
}