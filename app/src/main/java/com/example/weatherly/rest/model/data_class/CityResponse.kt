package com.example.weatherly.rest.model.data_class
import com.google.gson.annotations.SerializedName


class CityResponse : ArrayList<CityResponse.CityResponseItem>(){
    data class CityResponseItem(
        @SerializedName("name")
        val name: String, // Innsbruck
        @SerializedName("local_names")
        val localNames: LocalNames,
        @SerializedName("lat")
        val lat: Double, // 47.2654296
        @SerializedName("lon")
        val lon: Double, // 11.3927685
        @SerializedName("country")
        val country: String, // AT
        @SerializedName("state")
        val state: String // Tyrol
    ) {
        data class LocalNames(
            @SerializedName("bg")
            val bg: String, // Инсбрук
            @SerializedName("ja")
            val ja: String, // インスブルック
            @SerializedName("he")
            val he: String, // אינסברוק
            @SerializedName("uk")
            val uk: String, // Інсбрук
            @SerializedName("ka")
            val ka: String, // ინსბრუკი
            @SerializedName("de")
            val de: String, // Innsbruck
            @SerializedName("lt")
            val lt: String, // Insbrukas
            @SerializedName("kk")
            val kk: String, // Инсбрук
            @SerializedName("be")
            val be: String, // Інсбрук
            @SerializedName("ar")
            val ar: String, // إنسبروك
            @SerializedName("zh")
            val zh: String, // 因斯布鲁克
            @SerializedName("az")
            val az: String, // İnsbruk
            @SerializedName("fr")
            val fr: String, // Innsbruck
            @SerializedName("en")
            val en: String, // Innsbruck
            @SerializedName("os")
            val os: String, // Инсбрук
            @SerializedName("fa")
            val fa: String, // اینسبروک
            @SerializedName("cv")
            val cv: String, // Инсбрук
            @SerializedName("hu")
            val hu: String, // Innsbruck
            @SerializedName("ru")
            val ru: String, // Инсбрук
            @SerializedName("ko")
            val ko: String, // 인스브루크
            @SerializedName("mk")
            val mk: String, // Инсбрук
            @SerializedName("mr")
            val mr: String, // इन्सब्रुक
            @SerializedName("rm")
            val rm: String, // Puntina
            @SerializedName("th")
            val th: String, // อินส์บรุค
            @SerializedName("tr")
            val tr: String, // İnnsbruck
            @SerializedName("bn")
            val bn: String, // ইন্সব্রুক
            @SerializedName("la")
            val la: String, // Pons Aeni
            @SerializedName("el")
            val el: String, // Ίνσμπρουκ
            @SerializedName("it")
            val `it`: String, // Innsbruck
            @SerializedName("sr")
            val sr: String, // Инзбрук
            @SerializedName("cs")
            val cs: String, // Innsbruck
            @SerializedName("lv")
            val lv: String, // Insbruka
            @SerializedName("et")
            val et: String, // Iona
            @SerializedName("cy")
            val cy: String, // Iona
            @SerializedName("sh")
            val sh: String, // Iona
            @SerializedName("br")
            val br: String, // Ì Chaluim Chille
            @SerializedName("da")
            val da: String, // Iona
            @SerializedName("no")
            val no: String, // Iona
            @SerializedName("fi")
            val fi: String, // Iona
            @SerializedName("gl")
            val gl: String, // Iona
            @SerializedName("nl")
            val nl: String, // Iona
            @SerializedName("ca")
            val ca: String, // Iona
            @SerializedName("gd")
            val gd: String, // Ì
            @SerializedName("vi")
            val vi: String, // Iona
            @SerializedName("nn")
            val nn: String, // Iona
            @SerializedName("ur")
            val ur: String, // آئونا
            @SerializedName("ga")
            val ga: String, // Í Cholm Cille
            @SerializedName("es")
            val es: String, // Iona
            @SerializedName("pt")
            val pt: String, // Iona
            @SerializedName("sv")
            val sv: String, // Iona
            @SerializedName("pl")
            val pl: String, // Iona
            @SerializedName("eu")
            val eu: String // Iona
        )
    }
}