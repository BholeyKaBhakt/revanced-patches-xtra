package io.github.bholeykabhakt.patches.automate

import app.revanced.patcher.fingerprint
import com.android.tools.smali.dexlib2.Opcode

internal val isBlockLimitReachedFingerPrint = fingerprint {
    returns("Z")
    strings("checkPremiumAllow", "runningStatementCount", "count")
    opcodes(
        Opcode.CONST_STRING,
        Opcode.INVOKE_VIRTUAL,
        Opcode.MOVE_RESULT_WIDE,
        Opcode.CONST_WIDE_16,
        Opcode.CMP_LONG,
        Opcode.IF_LTZ,
        Opcode.RETURN
    )
    custom { methodDef, _ ->
        methodDef.definingClass == "Lcom/llamalab/automate/AutomateService;"
    }
}