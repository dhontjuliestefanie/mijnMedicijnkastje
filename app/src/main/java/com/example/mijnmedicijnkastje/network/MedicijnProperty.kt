package com.example.mijnmedicijnkastje.network

//data class MedicijnProperty(
//    @Json(name = "id") val id: Int,
//    @Json(name = "registratienummer") val registratienummer: String,
//    @Json(name = "productnaam") val productnaam: String,
//    @Json(name = "productnaam_link") val productnaam_link: String,
//    @Json(name = "ATC") val atc: String,
//    @Json(name = "werkzame_stof") val werkzame_stof: String
//)


data class MedicijnProperty(
    val help: String,
    val succes: Boolean,
    val result: Map<String, Any>,
    val records_format: String,
    val records: List<MedicijnInApi>,
    val _links: Map<String, String>,
    val total: Int
) {
    @JvmName("getRecords1")
    fun getRecords(): List<MedicijnInApi> {
        return records
    }
}

data class MedicijnInApi(
    val id: Int,
    val registratienummer: String,
    val productnaam: String,
    val productnaam_link: String,
    val atc: String,
    val werkzame_stof: String
)





