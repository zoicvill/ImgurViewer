import com.google.gson.annotations.SerializedName

data class ImagesItem(

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("section")
    val section: Any? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("id")
    val id: String? = null,
)