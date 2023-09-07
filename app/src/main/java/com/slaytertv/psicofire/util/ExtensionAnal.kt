package com.slaytertv.psicofire.util

import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent


fun Fragment.analregister(emai:String, name:String){
    FirebaseAnalytics.getInstance(requireContext())
        .logEvent(FirebaseAnalytics.Event.SIGN_UP) {
            param(FirebaseAnalytics.Param.METHOD,"UserPass")
            param("FullName", name)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "email")
            param(FirebaseAnalytics.Param.CONTENT, emai)
        }
}
//
fun Fragment.anallogin(emai:String){
    FirebaseAnalytics.getInstance(requireContext())
        .logEvent(FirebaseAnalytics.Event.LOGIN) {
            param(FirebaseAnalytics.Param.METHOD,"UserPass")
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "email")
            param(FirebaseAnalytics.Param.CONTENT, emai)
        }
}
fun Fragment.itemselected(id:String,name:String,contenttype:String,user:String){
    FirebaseAnalytics.getInstance(requireContext())
        .logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, id)
            param(FirebaseAnalytics.Param.ITEM_NAME, name)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, contenttype)
            param(FirebaseAnalytics.Param.VIRTUAL_CURRENCY_NAME, user)
    }
}