package com.slaytertv.psicofire.util

object FireStoreCollection{
    val PELICULA = "Pelicula"
    val SERIE = "Serie"
    val SEASON = "Season"
    val USER = "user"
    val CAPTOP = "capitulostop"
}
//shred preferences para los datos de usuario login
object SharedPrefConstants {
    //indicar que es local
    val LOCAL_SHARED_PREF = "local_shared_pref"
    //la sesion del usuario
    val USER_SESSION = "user_session"
}
//
object FireDatabase {
    val TV = "cablepropio"
}
//enumerar nombre e index del menu
enum class HomeTabs(val index: Int, val key: String) {
    PRINCIPAL(0, "Principal"),
    TV(1,"TvIp")
}
//
object Version{
    val VERSION = "hb8og780*mhtyty"
}