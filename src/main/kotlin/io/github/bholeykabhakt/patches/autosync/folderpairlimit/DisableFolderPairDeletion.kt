package io.github.bholeykabhakt.patches.autosync.folderpairlimit

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.util.exception
import io.github.bholeykabhakt.patches.autosync.folderpairlimit.fingerprints.SyncSettingsBFingerprint
import io.github.bholeykabhakt.patches.autosync.folderpairlimit.fingerprints.SyncSettingsGetLastUpdatedAtFingerprint

@Patch(
    name = "Disable Folder Pair Deletion",
    compatiblePackages = [CompatiblePackage("com.ttxapps.autosync")]
)

object DisableFolderPairDeletion : BytecodePatch(
    setOf(SyncSettingsBFingerprint, SyncSettingsGetLastUpdatedAtFingerprint)
) {
    override fun execute(context: BytecodeContext) {

        // don't run the method that removes all sync pair but one
        SyncSettingsBFingerprint.result?.mutableMethod?.addInstructions(
            0,
            """
               return-void
            """
        ) ?: throw SyncSettingsBFingerprint.exception

        // return PREF_LAST_UPDATED_AT time in near future
        // stops sync pair list from being cleared
        SyncSettingsGetLastUpdatedAtFingerprint.result?.mutableMethod?.addInstructions(
            0,
            """
               invoke-static {}, Ljava/lang/System;->currentTimeMillis()J
               move-result-wide v0
               return-wide v0
            """
        ) ?: throw SyncSettingsGetLastUpdatedAtFingerprint.exception
    }
}