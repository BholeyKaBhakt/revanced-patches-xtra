package io.github.bholeykabhakt.patches.automate

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.util.exception
import io.github.bholeykabhakt.patches.automate.fingerprints.IsBlockLimitReachedFingerPrint

@Patch(
    name = "Bypass Blocks Limit",
    compatiblePackages = [CompatiblePackage("com.llamalab.automate")]
)

object BypassBlocksLimitPatch : BytecodePatch(
    setOf(IsBlockLimitReachedFingerPrint)
) {
    override fun execute(context: BytecodeContext) {
        IsBlockLimitReachedFingerPrint.result?.mutableMethod?.addInstructions(
            0,
            """
               const/4 v0, 0x1
               return v0
            """
        ) ?: throw IsBlockLimitReachedFingerPrint.exception
    }
}