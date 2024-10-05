package io.github.bholeykabhakt.patches.automate

import app.revanced.patcher.patch.bytecodePatch
import io.github.bholeykabhakt.patches.utils.returnEarly

@Suppress("unused")
val bypassBlocksLimitPatch = bytecodePatch(
    name = "Bypass Blocks Limit",
) {
    compatibleWith("com.llamalab.automate")

    val isBlockLimitReachedFingerMatch by isBlockLimitReachedFingerPrint()

    execute {

        listOf(isBlockLimitReachedFingerMatch).returnEarly(true)

    }
}