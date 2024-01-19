package mobi.dev33.inditest.data.datasource.api.model.mapper

import mobi.dev33.inditest.data.datasource.api.model.ApiUser
import mobi.dev33.inditest.data.datasource.api.model.ApiUserImages
import mobi.dev33.inditest.data.datasource.api.model.ApiUserLocation
import mobi.dev33.inditest.data.datasource.api.model.ApiUserLocationCoordinates
import mobi.dev33.inditest.data.datasource.api.model.ApiUserLocationStreet
import mobi.dev33.inditest.data.datasource.api.model.ApiUserLocationTimeZone
import mobi.dev33.inditest.data.datasource.api.model.ApiUserName
import mobi.dev33.inditest.domain.model.DomainUser
import mobi.dev33.inditest.domain.model.DomainUserImages
import mobi.dev33.inditest.domain.model.DomainUserLocation
import mobi.dev33.inditest.domain.model.DomainUserLocationCoordinates
import mobi.dev33.inditest.domain.model.DomainUserLocationStreet
import mobi.dev33.inditest.domain.model.DomainUserLocationTimeZone
import mobi.dev33.inditest.domain.model.DomainUserName


fun ApiUser.toDomain() = DomainUser(
    name.toDomain(),
    gender,
    location?.toDomain(),
    email,
    MapperUtils.apiDateToTs(dateOfBirth?.date),
    MapperUtils.apiDateToTs(registrationDate?.date),
    phone,
    cell,
    picture.toDomain()
)

fun ApiUserImages.toDomain() = DomainUserImages(thumbnail, medium, large)

fun ApiUserName.toDomain() = DomainUserName(title, first, last)

fun ApiUserLocation.toDomain() = DomainUserLocation(
    street?.toDomain(),
    city,
    state,
    country,
    postcode,
    coordinates.toDomain(),
    timezone.toDomain()
)

fun ApiUserLocationCoordinates.toDomain() = DomainUserLocationCoordinates(latitude, longitude)
fun ApiUserLocationTimeZone.toDomain() = DomainUserLocationTimeZone(offset, description)
fun ApiUserLocationStreet.toDomain() = DomainUserLocationStreet(number, name)