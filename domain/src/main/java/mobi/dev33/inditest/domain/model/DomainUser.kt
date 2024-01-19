package mobi.dev33.inditest.domain.model

data class DomainUser(
    val name: DomainUserName,
    val gender: String?,
    val location: DomainUserLocation?,
    val email: String,
    val dateOfBirth: Long,
    val registrationDate: Long,
    val phone: String?,
    val cell: String?,
    val picture: DomainUserImages
)

data class DomainUserImages(val thumbnail: String?, val medium: String?, val large: String?)

data class DomainUserName(val title: String?, val first: String, val last: String)

data class DomainUserLocation(
    val street: DomainUserLocationStreet?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: String?,
    val coordinates: DomainUserLocationCoordinates,
    val timezone: DomainUserLocationTimeZone
)

data class DomainUserLocationStreet(val number: Int?, val name: String?)
data class DomainUserLocationCoordinates(val latitude: String, val longitude: String)
data class DomainUserLocationTimeZone(val offset: String?, val description: String?)
