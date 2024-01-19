package mobi.dev33.inditest.data.datasource.api.model

import com.google.gson.annotations.SerializedName

data class ApiUser(
    val name: ApiUserName,
    val gender: String?,
    val location: ApiUserLocation?,
    val email: String,
    @SerializedName("dob") val dateOfBirth: ApiUserDateFormat?,
    @SerializedName("registered") val registrationDate: ApiUserDateFormat?,
    val phone: String?,
    val cell: String?,
    val picture: ApiUserImages
)

data class ApiUserImages(val thumbnail: String?, val medium: String?, val large: String?)

data class ApiUserName(val title: String?, val first: String, val last: String)

data class ApiUserLocation(
    val street: ApiUserLocationStreet?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: String?,
    val coordinates: ApiUserLocationCoordinates,
    val timezone: ApiUserLocationTimeZone
)

data class ApiUserLocationStreet(val number: Int?, val name: String?)
data class ApiUserLocationCoordinates(val latitude: String, val longitude: String)
data class ApiUserLocationTimeZone(val offset: String?, val description: String?)
data class ApiUserDateFormat(val date: String?, val age: Int?)