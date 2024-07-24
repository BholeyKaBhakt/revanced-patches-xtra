package io.github.bholeykabhakt.patches.automate.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint
import com.android.tools.smali.dexlib2.Opcode

internal object IsBlockLimitReachedFingerPrint : MethodFingerprint(
    "Z",
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/llamalab/automate/AutomateService;"
    },
    strings = listOf("checkPremiumAllow", "runningStatementCount", "count"),
    opcodes = listOf(
        Opcode.CONST_STRING,
        Opcode.INVOKE_VIRTUAL,
        Opcode.MOVE_RESULT_WIDE,
        Opcode.CONST_WIDE_16,
        Opcode.CMP_LONG,
        Opcode.IF_LTZ,
        Opcode.RETURN
    )
)