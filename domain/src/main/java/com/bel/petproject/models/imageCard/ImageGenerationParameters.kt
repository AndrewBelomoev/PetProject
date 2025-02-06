package com.bel.petproject.models.imageCard

data class ImageGenerationParameters(
    val model: String? = null,
    val aspectRatio: String? = null,
    val highResolution: Boolean? = null,
    val images: Long? = null,
    val steps: Long? = null,
    val prompt: String? = null,
    val negativePrompt: String? = null,
    val seed: Long? = null
)

enum class ImageModel(val displayName: String) {
    LYRA("lyra"),
    HYDRA("hydra"),
    FANTASY("fantasy"),
    PORTRAIT("portrait"),
    INPUNK("inpunk"),
    ABSTRACT_WORLD("abstractWorld"),
    ANIME("anime"),
    ARGO("argo"),
    CINEMATIC("cinematic"),
    PHOTOGRAPHY("photography"),
    SCIFI("scifi"),
    DETAILED_ILLUSTRATION("detailedIllustration"),
    ILLUSTRATION_3D("3dIllustration"),
    FLAY_ILLUSTRATION("flayIllustration"),
    REALVIS_XL("realvisxl"),
    STYLEVISION_XL("stylevisionxl"),
    ANIMAGINE_XL("animaginexl"),
    ANIM_2("anim_2"),
    ANIME_STYLIZED("anime_stylized"),
    ANIME_VINTAGE("anime_vintage"),
    PIXELART("pixelart")
}

enum class AspectRatio(val displayName: String) {
    SQUARE("square"),
    LANDSCAPE("landscape"),
    SMALL_PORTRAIT("smallPortrait"),
    PORTRAIT("portrait"),
    WIDE("wide")
}
