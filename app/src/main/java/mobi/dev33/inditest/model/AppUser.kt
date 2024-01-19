package mobi.dev33.inditest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppUser(
    val name: String,
    val gender: AppUserGender,
    val email: String,
    val phone: String?,
    val dateOfBirth : Long,
    val registrationDate : Long,
    val picture: AppUserImages?,
    val appUserLocation: AppUserLocation?
) : Parcelable

@Parcelize
data class AppUserImages(val thumbnail: String?, val medium: String?, val large: String?) : Parcelable

@Parcelize
data class AppUserLocation(val latitude: String, val longitude: String) : Parcelable

enum class AppUserGender(val value: String) {
    MALE("male"), FEMALE("female"), UNKNOWN("");

    companion object {
        fun getByValue(value: String?) = entries.firstOrNull { it.value == value } ?: UNKNOWN
    }
}