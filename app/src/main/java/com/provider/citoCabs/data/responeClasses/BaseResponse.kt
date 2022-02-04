package com.provider.citoCabs.data.responeClasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class BaseResponse(
    @field:SerializedName("data")
    var data: Data? = null,

    @field:SerializedName("error")
    var error: Int? = 0,

    @field:SerializedName("Message")
    var message: String? = "",

    @field:SerializedName("Success")
    val Success: Boolean? = false
) : Parcelable

@Parcelize
data class Data(
    @field:SerializedName("loginData")
    var loginData: ResponseLogin? = ResponseLogin(),

    @field:SerializedName("referData")
    var referData: ResponseReferredUsers = ResponseReferredUsers(),
    @field:SerializedName("registerData")
    var registerData: ResponseLogin? = ResponseLogin(),
    @field:SerializedName("subscribeData")
    var subscribeData: ResponseSubscription = ResponseSubscription(),
    @field:SerializedName("orderData")
    var orderData: ResponseOrder? = ResponseOrder(),
    @field:SerializedName("profileData")
    var profileData: ResponseProfileData? = ResponseProfileData(),
    @field:SerializedName("stateData")
    val responseStates: List<ResponseStatesItem> = ArrayList(),

    @field:SerializedName("rideData")
    val rideData: List<ResponseRideItem> = ArrayList(),

    @field:SerializedName("prioritylist")
    val priorityList:  List<ResponseRideAlertsItem?> = ArrayList(),

    @field:SerializedName("priorityData")
    val priorityData:  ResponseRideAlertsItem? = null,


    ) : Parcelable

@Parcelize
data class ResponseRideAlertsItem(
    val updatedAt: String? = null,
    val city: String? = null,
    val createdAt: String? = null,
    val id: String? = null,
    val state: String? = null,
    val userId: Int? = null,
    val status: Int? = null
) : Parcelable


@Parcelize
data class ResponseReferredUsers(

    @field:SerializedName("userRefers")
    val userRefers: List<UserRefersItem> = ArrayList(),

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("mobileNo")
    val mobileNo: String? = null,

    @field:SerializedName("isProfileCompleted")
    val isProfileCompleted: Int? = null,

    @field:SerializedName("walletAmount")
    val walletAmount: String? = null
) : Parcelable

@Parcelize
data class Users(

    @field:SerializedName("userProfiles")
    val userProfiles: UserProfiles? = null,

    @field:SerializedName("mobileNo")
    val mobileNo: String? = null,

    @field:SerializedName("isProfileCompleted")
    val isProfileCompleted: Int? = null
) : Parcelable

@Parcelize
data class UserRefersItem(

    @field:SerializedName("isPaymentDone")
    val isPaymentDone: Int? = null,

    @field:SerializedName("referUserId")
    val referUserId: Int? = null,

    @field:SerializedName("userId")
    val userId: Int? = null,

    @field:SerializedName("users")
    val users: Users? = null
) : Parcelable

@Parcelize
data class ResponseStatesItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("fullImageUrl")
    val fullImageUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable


@Parcelize
data class ResponseRideItem(

    @field:SerializedName("isExpired")
    var isExpired: Boolean? = false,

    @field:SerializedName("dateTime")
    val dateTime: String? = null,


    @field:SerializedName("priceWithToll")
    val priceWithToll: String? = null,

    @field:SerializedName("travelerName")
    val travelerName: String? = null,

    @field:SerializedName("addedId")
    val addedId: String? = null,

    @field:SerializedName("additionNotes")
    val additionNotes: String? = null,

    @field:SerializedName("priceWithStateTax")
    val priceWithStateTax: String? = null,

    @field:SerializedName("cabType")
    val cabType: String? = null,
    @field:SerializedName("rideType")
    val rideType: String? = null,

    @field:SerializedName("package")
    val jsonMemberPackage: String? = null,

    @field:SerializedName("addedBy")
    val addedBy: String? = null,

    @field:SerializedName("addedByType")
    val addedByType: String? = null,

    @field:SerializedName("stateId")
    val stateId: Int? = null,

    @field:SerializedName("pickUpDestination")
    val pickUpDestination: String? = null,


    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("deleted_at")
    val deletedAt: String? = null,

    @field:SerializedName("endDestination")
    val endDestination: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("contactNo")
    var contactNo: String? = null,

    @field:SerializedName("status")
    val status: Int? = null,


    @field:SerializedName("isActive")
    val isActive: String? = null,

    @field:SerializedName("created_at")
    val Created_at: String? = null,

    @field:SerializedName("agentProfiles")
    val agentProfiles: AgentProfile? = null
) : Parcelable

@Parcelize
data class AgentProfile(
    @field:SerializedName("userProfiles")
    val userProfiles: UserProfiles? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable

@Parcelize
data class ResponseLogin(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("mobileNo")
    val mobileNo: String? = null,

    @field:SerializedName("userType")
    val userType: String? = null,

    @field:SerializedName("accessToken")
    val accessToken: String? = null,

    @field:SerializedName("isProfileCompleted")
    val isProfileCompleted: Int? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null,
    @field:SerializedName("userAadharDetails")
    val userAadharDetails: UserAadharDetails? = null,

    @field:SerializedName("userDriverLicenseDetails")
    val userDriverLicenseDetails: UserDriverLicenseDetails? = null,

    @field:SerializedName("userProfiles")
    val userProfiles: UserProfiles? = null,
) : Parcelable


@Parcelize
data class ResponseProfileData(

    @field:SerializedName("userAadharDetails")
    val userAadharDetails: UserAadharDetails? = null,

    @field:SerializedName("userDriverLicenseDetails")
    val userDriverLicenseDetails: UserDriverLicenseDetails? = null,

    @field:SerializedName("userRcDetails")
    val userRcDetails: UserRCDetails? = null,
    @field:SerializedName("userCarDetails")
    val userCarDetails: UserCarDetails? = null,


    @field:SerializedName("userProfiles")
    val userProfiles: UserProfiles? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("mobileNo")
    val mobileNo: String? = null,

    @field:SerializedName("userType")
    val userType: String? = null
) : Parcelable


@Parcelize
data class ResponseOrder(

    @field:SerializedName("amount")
    val amount: Int? = null,

    @field:SerializedName("amount_paid")
    val amountPaid: Int? = null,


    @field:SerializedName("created_at")
    val createdAt: Int? = null,

    @field:SerializedName("amount_due")
    val amountDue: Int? = null,

    @field:SerializedName("currency")
    val currency: String? = null,

    @field:SerializedName("receipt")
    val receipt: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("entity")
    val entity: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("attempts")
    val attempts: Int? = null
) : Parcelable


@Parcelize
data class UserProfiles(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("fullProfileImageUrl")
    val profileImage: String? = null,

    @field:SerializedName("email")
    val email: String? = null
) : Parcelable


@Parcelize
data class ResponseSubscription(

    @field:SerializedName("amount")
    val amount: String? = null,

    @field:SerializedName("purchasedAt")
    val purchasedAt: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("isActive")
    val isActive: String? = null,

    @field:SerializedName("userId")
    val userId: Int? = null,

    @field:SerializedName("transactionId")
    val transactionId: String? = null
) : Parcelable

@Parcelize
data class PaymentErrorResponse(

    @field:SerializedName("error")
    val error: PaymentError? = null
) : Parcelable

@Parcelize
data class PaymentError(

    @field:SerializedName("reason")
    val reason: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("step")
    val step: String? = null,

    @field:SerializedName("source")
    val source: String? = null
) : Parcelable

@Parcelize
data class UserDriverLicenseDetails(

    @field:SerializedName("licenseNo")
    val licenseNo: String? = null,

    @field:SerializedName("fullLicenseBackImage")
    val licenseBackImage: String? = null,

    @field:SerializedName("fullLicenseFrontImage")
    val licenseFrontImage: String? = null
) : Parcelable

@Parcelize
data class UserVehicleDetails(

    @field:SerializedName("rcNo")
    val rcNo: String? = null,

    @field:SerializedName("rcImage")
    val rcImage: String? = null,

    @field:SerializedName("carImage")
    val carImage: String? = null
) : Parcelable

@Parcelize
data class UserCarDetails(

    @field:SerializedName("fullCarImage")
    val fullCarImage: String? = null,


    ) : Parcelable

@Parcelize
data class UserRCDetails(

    @field:SerializedName("rcNo")
    val rcNo: String? = null,

    @field:SerializedName("fullRcImage")
    val fullRcImage: String? = null,


    ) : Parcelable


@Parcelize
data class UserAadharDetails(

    @field:SerializedName("fullAadharFrontImage")
    val fullAadharFrontImage: String? = null,

    @field:SerializedName("aadharNumber")
    val aadharNumber: String? = null,

    @field:SerializedName("fullAadharBackImage")
    val aadharBackImage: String? = null
) : Parcelable
