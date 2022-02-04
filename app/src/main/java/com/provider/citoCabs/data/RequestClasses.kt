package com.provider.citoCabs.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.provider.citoCabs.base.Prefs
import com.provider.citoCabs.data.responeClasses.ResponseRideAlertsItem
import kotlinx.android.parcel.Parcelize
import okhttp3.MultipartBody

data class StateData(val image: Int, val stateName: String)
data class RideMessage(val message: String)


data class PaymentIssue(val issue:String)
data class LoginParams(
    @SerializedName("mobileNo")
    var mobileNo: String? = null,
    @SerializedName("deviceType")
    var deviceType: String? = "Android",
    @SerializedName("deviceToken")
    var deviceToken: String? = Prefs.init().deviceToken,
    @SerializedName("deviceId")
    var deviceId: String? = null,
    @SerializedName("deviceModel")
    var deviceModel: String? =null,
    @SerializedName("referUserId")
    var referralUid: String? = null,

    )


data class RideParams(
    @SerializedName("stateId")
    var stateId: String? = null,
    @SerializedName("limit")
    var limit: Int? = 20,
    @SerializedName("offset")
    var offset: Int? = null
)


data class FinishRideParams(
    @SerializedName("rideId")
    var rideId: String? = null,
)


data class AddRideParams(


    @SerializedName("stateId")
    var stateId: String? = null,

    @SerializedName("rideType")
    var rideType: String? = null,

    @SerializedName("dateTime")
    var dateTime: String? = null,

    @SerializedName("pickUpDestination")
    var pickUpDestination: String? = null,

    @SerializedName("endDestination")
    var endDestination: String? = null,


    @SerializedName("cabType")
    var cabType: String? = null,

    @SerializedName("price")
    var price: String? = null,

    @SerializedName("package")
    var packageType: String? = null,

    @SerializedName("priceWithToll")
    var priceWithToll: Boolean? = null,

    @SerializedName("priceWithStateTax")
    var priceWithStateTax: Boolean? = null,

    @SerializedName("travelerName")
    var travelerName: String? = null,

    @SerializedName("contactNo")
    var contactNo: String? = null,

    @SerializedName("additionNotes")
    var additionNotes: String? = null,
)

data class OrderParams(
    @SerializedName("amount")
    var amount: String? = null,
    @SerializedName("currency")
    var currency: String? = null

)



data class RideAlertItem(
    @SerializedName("state")
    var state: String? = null,

    @SerializedName("city")
    var city: String? = null,
)




data class QueryParams(
    @SerializedName("priority_id")
    var priority_id: String? = null,
)



data class RaisePaymentParams(
    @SerializedName("image")
    var image: MultipartBody.Part? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("title")
    var title: String? = null

)


data class SubscriptionParams(

    @SerializedName("transactionId")
    var transactionId: String? = null,

    @SerializedName("amount")
    var amount: String? = null,

    @SerializedName("purchasedAt")
    var purchasedAt: String? = null,

    @SerializedName("isActive")
    var isActive: String? = "1"

)

@Parcelize
class ProfileParams : Parcelable {
    var id: String? = null
    var deviceType: String? = "Android"
    var deviceToken: String? = Prefs.init().deviceToken
    var userType: String? = null
    var profileImage: MultipartBody.Part? = null
    var name: String? = null
    var email: String? = null
    var aadharNumber: String? = null
    var aadharFrontImage: MultipartBody.Part? = null
    var aadharBackImage: MultipartBody.Part? = null
    var licenseNo: String? = null
    var licenseFrontImage: MultipartBody.Part? = null
    var licenseBackImage: MultipartBody.Part? = null
    var rcImage: MultipartBody.Part? = null
    var carImage: MultipartBody.Part? = null
    var rcNo: String? = null
}
