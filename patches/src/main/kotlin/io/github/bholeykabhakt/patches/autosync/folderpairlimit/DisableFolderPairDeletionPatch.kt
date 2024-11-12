package io.github.bholeykabhakt.patches.autosync.folderpairlimit

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.bytecodePatch
import io.github.bholeykabhakt.patches.utils.returnEarly

@Suppress("unused")
val disableFolderPairDeletionPatch = bytecodePatch(
    name = "Disable Folder Pair Deletion",
) {
    compatibleWith("com.ttxapps.autosync")

    execute {
        // don't run the method that removes all sync pair but one
        syncSettingsBFingerprint.method.returnEarly()

        // return PREF_LAST_UPDATED_AT time in near future
        // stops sync pair list from being cleared
        syncSettingsGetLastUpdatedAtFingerprint.method.addInstructions(
            0,
            """
               invoke-static {}, Ljava/lang/System;->currentTimeMillis()J
               move-result-wide v0
               return-wide v0
            """
        )
    }
}