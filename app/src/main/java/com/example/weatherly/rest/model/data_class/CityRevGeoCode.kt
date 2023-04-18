package com.example.weatherly.rest.model.data_class
import com.google.gson.annotations.SerializedName


class CityRevGeoCode : ArrayList<CityRevGeoCodeItem>()

data class CityRevGeoCodeItem(
    @SerializedName("name")
    val name: String, // City of London
    @SerializedName("local_names")
    val localNames: LocalNames,
    @SerializedName("lat")
    val lat: Double, // 51.5128
    @SerializedName("lon")
    val lon: Double, // -0.0918
    @SerializedName("country")
    val country: String // GB
)

data class LocalNames(
    @SerializedName("ar")
    val ar: String, // مدينة لندن
    @SerializedName("ascii")
    val ascii: String, // City of London
    @SerializedName("bg")
    val bg: String, // Сити
    @SerializedName("ca")
    val ca: String, // La City
    @SerializedName("de")
    val de: String, // London City
    @SerializedName("el")
    val el: String, // Σίτι του Λονδίνου
    @SerializedName("en")
    val en: String, // City of London
    @SerializedName("fa")
    val fa: String, // سیتی لندن
    @SerializedName("feature_name")
    val featureName: String, // City of London
    @SerializedName("fi")
    val fi: String, // Lontoon City
    @SerializedName("fr")
    val fr: String, // Cité de Londres
    @SerializedName("gl")
    val gl: String, // Cidade de Londres
    @SerializedName("he")
    val he: String, // הסיטי של לונדון
    @SerializedName("hi")
    val hi: String, // सिटी ऑफ़ लंदन
    @SerializedName("id")
    val id: String, // Kota London
    @SerializedName("it")
    val `it`: String, // Londra
    @SerializedName("ja")
    val ja: String, // シティ・オブ・ロンドン
    @SerializedName("la")
    val la: String, // Civitas Londinium
    @SerializedName("lt")
    val lt: String, // Londono Sitis
    @SerializedName("pt")
    val pt: String, // Cidade de Londres
    @SerializedName("ru")
    val ru: String, // Сити
    @SerializedName("sr")
    val sr: String, // Сити
    @SerializedName("th")
    val th: String, // นครลอนดอน
    @SerializedName("tr")
    val tr: String, // Londra Şehri
    @SerializedName("vi")
    val vi: String, // Thành phố Luân Đôn
    @SerializedName("zu")
    val zu: String, // Idolobha weLondon
    @SerializedName("af")
    val af: String, // Londen
    @SerializedName("az")
    val az: String, // London
    @SerializedName("da")
    val da: String, // London
    @SerializedName("eu")
    val eu: String, // Londres
    @SerializedName("hr")
    val hr: String, // London
    @SerializedName("hu")
    val hu: String, // London
    @SerializedName("mk")
    val mk: String, // Лондон
    @SerializedName("nl")
    val nl: String, // Londen
    @SerializedName("no")
    val no: String, // London
    @SerializedName("pl")
    val pl: String, // Londyn
    @SerializedName("ro")
    val ro: String, // Londra
    @SerializedName("sk")
    val sk: String, // Londýn
    @SerializedName("sl")
    val sl: String // London
)//