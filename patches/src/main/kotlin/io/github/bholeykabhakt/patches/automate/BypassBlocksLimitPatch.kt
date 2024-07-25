package io.github.bholeykabhakt.patches.automate

import app.revanced.patcher.extensions.InstructionExtensions.addInstruction
import app.revanced.patcher.patch.bytecodePatch

@Suppress("unused")
val bypassBlocksLimitPatch = bytecodePatch(
    name = "Bypass Blocks Limit",
) {
    compatibleWith("com.llamalab.automate")

    val isBlockLimitReachedFingerMatch by isBlockLimitReachedFingerPrint()

    execute {
        isBlockLimitReachedFingerMatch.mutableMethod.addInstruction(
            0,
            """
               const/4 v0, 0x1
               return v0
            """
        )
    }
}