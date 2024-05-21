rootProject.name = "revanced-patches-xtra"

buildCache {
    local {
        isEnabled = "CI" !in System.getenv()
    }
}
