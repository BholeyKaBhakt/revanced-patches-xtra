package io.github.bholeykabhakt.patches.autosync.folderpairlimit

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.bytecodePatch

@Suppress("unused")
val disableFolderPairDeletionPatch = bytecodePatch(
    name = "Disable Folder Pair Deletion",
) {
    compatibleWith("com.ttxapps.autosync")

    val syncSettingsBMatch by syncSettingsBFingerprint()
    val syncSettingsGetLastUpdatedAtMatch by syncSettingsGetLastUpdatedAtFingerprint()

    execute {
        // don't run the method that removes all sync pair but one
        syncSettingsBMatch.mutableMethod.addInstructions(
            0,
            """
               return-void
            """
        )

        // return PREF_LAST_UPDATED_AT time in near future
        // stops sync pair list from being cleared
        syncSettingsGetLastUpdatedAtMatch.mutableMethod.addInstructions(
            0,
            """
               invoke-static {}, Ljava/lang/System;->currentTimeMillis()J
               move-result-wide v0
               return-wide v0
            """
        )
    }
}