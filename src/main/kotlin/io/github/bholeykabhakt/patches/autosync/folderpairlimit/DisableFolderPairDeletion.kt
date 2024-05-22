package io.github.bholeykabhakt.patches.autosync.folderpairlimit

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.util.exception
import io.github.bholeykabhakt.patches.autosync.folderpairlimit.fingerprints.SyncSettingsBFingerprint
import io.github.bholeykabhakt.patches.autosync.folderpairlimit.fingerprints.SyncSettingsJFingerprint
import io.github.bholeykabhakt.patches.autosync.folderpairlimit.fingerprints.WUFingerprint

@Patch(
    name = "Disable Folder Pair Deletion",
    compatiblePackages = [CompatiblePackage("com.ttxapps.autosync", ["0.9.52-beta"])]
)

object DisableFolderPairDeletion : BytecodePatch(
    setOf(WUFingerprint, SyncSettingsBFingerprint, SyncSettingsJFingerprint)
) {
    override fun execute(context: BytecodeContext) {

        // z.g = false;
        // this.g.a = false;
        WUFingerprint.result?.mutableMethod?.addInstructions(
            0,
            """
               const/4 v0, 0x0
               sput-boolean v0, Lcom/ttxapps/autosync/sync/z;->g:Z
               const/4 v1, 0x0
               iget-object v0, p0, Lcom/ttxapps/autosync/sync/w;->g:Lcom/ttxapps/autosync/sync/b0;
               iput-boolean v1, v0, Lcom/ttxapps/autosync/sync/b0;->a:Z
            """
        ) ?: throw WUFingerprint.exception

        // don't run the method that removes all sync pair but one
        SyncSettingsBFingerprint.result?.mutableMethod?.addInstructions(
            0,
            """
               return-void
            """
        ) ?: throw SyncSettingsBFingerprint.exception

        // return pref_last_updated time in near future
        // stops sync pair list from being cleared
        SyncSettingsJFingerprint.result?.mutableMethod?.addInstructions(
            0,
            """
               invoke-static {}, Ljava/lang/System;->currentTimeMillis()J
               move-result-wide v0
               return-wide v0
            """
        ) ?: throw SyncSettingsJFingerprint.exception
    }
}