package io.github.bholeykabhakt.patches.autosync.purchase

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.fingerprint.MethodFingerprint
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.util.exception
import com.android.tools.smali.dexlib2.Opcode

internal object IsAccountTypePurchasedFingerprint : MethodFingerprint(
    "Z",
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/ttxapps/autosync/iab/LicenseManager;"
    },
    strings = listOf("accountType", "prefs"),
    opcodes = listOf(
        Opcode.INVOKE_STATIC,
        Opcode.SGET_OBJECT,
        Opcode.IF_EQZ,
        Opcode.INVOKE_DIRECT,
        Opcode.MOVE_RESULT_OBJECT,
        Opcode.CONST_4,
        Opcode.INVOKE_INTERFACE
    )
)

@Patch(
    name = "Purchase All Items",
    compatiblePackages = [CompatiblePackage("com.ttxapps.autosync", ["0.9.52-beta"])]
)

object PurchaseAllItems : BytecodePatch(
    setOf(IsAccountTypePurchasedFingerprint)
) {
    override fun execute(context: BytecodeContext) {
        IsAccountTypePurchasedFingerprint.result?.mutableMethod?.addInstructions(
            0,
            """
               const/4 v0, 0x1
               return v0
            """
        ) ?: throw IsAccountTypePurchasedFingerprint.exception
    }
}