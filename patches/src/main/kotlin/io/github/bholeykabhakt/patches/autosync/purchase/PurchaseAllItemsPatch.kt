package io.github.bholeykabhakt.patches.autosync.purchase

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.fingerprint
import app.revanced.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.Opcode

internal val isAccountTypePurchasedFingerprint = fingerprint {
    returns("Z")
    parameters("Ljava/lang/String;")
    strings("accountType")
    opcodes(
        Opcode.INVOKE_DIRECT,
        Opcode.MOVE_RESULT_OBJECT,
        Opcode.CONST_4,
        Opcode.INVOKE_INTERFACE,
        Opcode.MOVE_RESULT
    )
    custom { methodDef, _ ->
        methodDef.definingClass == "Lcom/ttxapps/autosync/iab/LicenseManager;"
    }
}

@Suppress("unused")
val purchaseAllItemsPatch = bytecodePatch(
    name = "Purchase All Items",
) {
    compatibleWith("com.ttxapps.autosync")

    val isAccountTypePurchasedMatch by isAccountTypePurchasedFingerprint()

    execute {
        isAccountTypePurchasedMatch.mutableMethod.addInstructions(
            0,
            """
               const/4 v0, 0x1
               return v0
            """
        )
    }
}