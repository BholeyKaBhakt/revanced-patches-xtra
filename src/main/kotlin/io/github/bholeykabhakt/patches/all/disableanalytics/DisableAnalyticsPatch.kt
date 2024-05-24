package io.github.bholeykabhakt.patches.all.disableanalytics

import app.revanced.patcher.data.ResourceContext
import app.revanced.patcher.patch.ResourcePatch
import app.revanced.patcher.patch.annotation.Patch
import org.w3c.dom.Element

@Patch(
    name = "Disable Firebase(Google) Analytics",
    use = false,
)
@Suppress("unused")
object DisableAnalyticsPatch : ResourcePatch() {
    override fun execute(context: ResourceContext) {
        context.xmlEditor["AndroidManifest.xml"].use { editor ->
            val document = editor.file

            val applicationNode =
                document
                    .getElementsByTagName("application")
                    .item(0) as Element

            val metadataNodes = applicationNode.getElementsByTagName("meta-data")

            val metadataSequence = (0 until metadataNodes.length).asSequence()
                .map { metadataNodes.item(it) as Element }

            val filteredMetadata = metadataSequence.filter {
                it.getAttribute("android:name")
                    .startsWith("firebase_") || it.getAttribute("android:name")
                    .startsWith("google_analytics_")
            }

            filteredMetadata.forEach { element ->
                when (element.getAttribute("android:name")) {
                    "firebase_analytics_collection_deactivated" -> element.setAttribute(
                        "android:value",
                        "true"
                    )

                    else -> element.setAttribute("android:value", "false")
                }
            }
        }
    }
}