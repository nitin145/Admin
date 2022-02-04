package com.provider.citoCabs.base

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.provider.citoCabs.data.responeClasses.ResponseProfileData


class Prefs {

    private val PREF_NAME_GLOBAL = "GLOBAL"
    private val PREF_USER_DATA = "PREF_USER_DATA"
    private val IS_LOG_IN = "IS_LOG_IN"
    private val REFERRAL_UID = "REFERRAL_UID"
    private val USER_TYPE = "USER_TYPE"
    private val USER_ID = "USER_ID"
    private val IS_PROFILE_COMPLETED = "IS_PROFILE_COMPLETED"
    private val ACCESS_TOKEN = "ACCESS_TOKEN"
    private val ELAPSED_TIME_REFER = "ELAPSED_TIME_REFER"
    private val DEVICE_TOKEN = "DEVICE_TOKEN"
    private val IS_SUBSCRIBED = "IS_SUBSCRIBED"
    private val IS_SUBSCRIBED_DATE = "IS_SUBSCRIBED_DATE"
    private val USER_IMAGE = "USER_IMAGE"
    private val USER_MOBILE = "USER_MOBILE"


    private var sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(MainApplication.get().applicationContext)

    init {
        instance = this
    }

    val gson = Gson()


    companion object {
        private var instance: Prefs? = null
        fun init(): Prefs {
            if (instance == null) {
                instance = Prefs()
            }
            return instance!!
        }
    }


    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    var accessToken: String
        get() {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(PREF_NAME_GLOBAL, Context.MODE_PRIVATE)
            return sF.getString(ACCESS_TOKEN, "") ?: ""
        }
        set(value) {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(PREF_NAME_GLOBAL, Context.MODE_PRIVATE)
            sF.edit().putString(ACCESS_TOKEN, value).apply()
        }


    var elapsedTimeRefer: String
        get() {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(PREF_NAME_GLOBAL, Context.MODE_PRIVATE)
            return sF.getString(ELAPSED_TIME_REFER, "") ?: ""
        }
        set(value) {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(PREF_NAME_GLOBAL, Context.MODE_PRIVATE)
            sF.edit().putString(ELAPSED_TIME_REFER, value).apply()
        }


    var deviceToken: String
        get() {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(PREF_NAME_GLOBAL, Context.MODE_PRIVATE)
            return sF.getString(DEVICE_TOKEN, "") ?: ""
        }
        set(value) {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(PREF_NAME_GLOBAL, Context.MODE_PRIVATE)
            sF.edit().putString(DEVICE_TOKEN, value).apply()
        }

    var userId: String
        get() {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(USER_ID, Context.MODE_PRIVATE)
            return sF.getString(USER_ID, "") ?: ""
        }
        set(value) {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(USER_ID, Context.MODE_PRIVATE)
            sF.edit().putString(USER_ID, value).apply()
        }

    var isSubscribed: String
        get() {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(IS_SUBSCRIBED, Context.MODE_PRIVATE)
            return sF.getString(IS_SUBSCRIBED, "") ?: ""
        }
        set(value) {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(IS_SUBSCRIBED, Context.MODE_PRIVATE)
            sF.edit().putString(IS_SUBSCRIBED, value).apply()
        }

    var isSubscribedDate: String
        get() {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(IS_SUBSCRIBED_DATE, Context.MODE_PRIVATE)
            return sF.getString(IS_SUBSCRIBED_DATE, "") ?: ""
        }
        set(value) {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(IS_SUBSCRIBED_DATE, Context.MODE_PRIVATE)
            sF.edit().putString(IS_SUBSCRIBED_DATE, value).apply()
        }

    var isLogIn: String
        get() {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(IS_LOG_IN, Context.MODE_PRIVATE)
            return sF.getString(IS_LOG_IN, "") ?: ""
        }
        set(value) {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(IS_LOG_IN, Context.MODE_PRIVATE)
            sF.edit().putString(IS_LOG_IN, value).apply()
        }

    var referralUID: String
        get() {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(REFERRAL_UID, Context.MODE_PRIVATE)
            return sF.getString(REFERRAL_UID, "") ?: ""
        }
        set(value) {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(REFERRAL_UID, Context.MODE_PRIVATE)
            sF.edit().putString(REFERRAL_UID, value).apply()
        }

    var isProfileCompleted: String
        get() {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(IS_PROFILE_COMPLETED, Context.MODE_PRIVATE)
            return sF.getString(IS_PROFILE_COMPLETED, "") ?: ""
        }
        set(value) {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(IS_PROFILE_COMPLETED, Context.MODE_PRIVATE)
            sF.edit().putString(IS_PROFILE_COMPLETED, value).apply()
        }


    var profileData: ResponseProfileData?
        get() {
            val str = sharedPreferences.getString(PREF_USER_DATA, "") ?: ""
            if (!str.isBlank()) return gson.fromJson(str, ResponseProfileData::class.java)
            return null
        }
        set(value) {
            sharedPreferences.edit().putString(PREF_USER_DATA, gson.toJson(value)).apply()
        }



    var userImage: String
        get() {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(PREF_NAME_GLOBAL, MODE_PRIVATE)
            return sF.getString(USER_IMAGE, "0.0") ?: ""
        }
        set(value) {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(PREF_NAME_GLOBAL, MODE_PRIVATE)
            sF.edit().putString(USER_IMAGE, value).apply()
        }

    var userMobile: String
        get() {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(PREF_NAME_GLOBAL, MODE_PRIVATE)
            return sF.getString(USER_MOBILE, "") ?: ""
        }
        set(value) {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(PREF_NAME_GLOBAL, MODE_PRIVATE)
            sF.edit().putString(USER_MOBILE, value).apply()
        }

    var userType: String
        get() {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(USER_TYPE, MODE_PRIVATE)
            return sF.getString(USER_TYPE, "0.0") ?: ""
        }
        set(value) {
            val sF = MainApplication.get().applicationContext
                .getSharedPreferences(USER_TYPE, MODE_PRIVATE)
            sF.edit().putString(USER_TYPE, value).apply()
        }


}