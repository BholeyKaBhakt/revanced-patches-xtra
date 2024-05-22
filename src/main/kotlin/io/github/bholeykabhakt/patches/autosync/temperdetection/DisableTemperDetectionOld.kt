package io.github.bholeykabhakt.patches.autosync.temperdetection

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.util.exception
import io.github.bholeykabhakt.patches.autosync.temperdetection.fingerprints.MainActivityOnResume
import io.github.bholeykabhakt.patches.autosync.temperdetection.fingerprints.StatusFragmentOnResume
import io.github.bholeykabhakt.patches.autosync.temperdetection.fingerprints.WUFingerprint

@Patch(
    name = "Disable Temper Detection (Old)",
    compatiblePackages = [CompatiblePackage("com.ttxapps.autosync", ["0.9.52-beta"])]
)

object DisableTemperDetectionOld : BytecodePatch(
    setOf(StatusFragmentOnResume, MainActivityOnResume, WUFingerprint)
) {
    override fun execute(context: BytecodeContext) {

        // z.g = false;
        StatusFragmentOnResume.result?.mutableMethod?.addInstructions(
            1,
            """
               const/4 v0, 0x0
               sput-boolean v0, Lcom/ttxapps/autosync/sync/z;->g:Z
            """
        ) ?: throw StatusFragmentOnResume.exception

        // this.syncState.a = false; {b0.a}
        MainActivityOnResume.result?.mutableMethod?.addInstructions(
            1,
            """
               const/4 v1, 0x0
               iget-object v0, p0, Lcom/ttxapps/autosync/app/MainActivity;->syncState:Lcom/ttxapps/autosync/sync/b0;
               iput-boolean v1, v0, Lcom/ttxapps/autosync/sync/b0;->a:Z
            """
        ) ?: throw StatusFragmentOnResume.exception

        // z.g = false;
        // this.g.a = false; {b0.a}
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
    }
}