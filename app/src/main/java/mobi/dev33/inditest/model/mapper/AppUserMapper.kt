package mobi.dev33.inditest.model.mapper

import mobi.dev33.inditest.domain.model.DomainUser
import mobi.dev33.inditest.domain.model.DomainUserImages
import mobi.dev33.inditest.domain.model.DomainUserLocation
import mobi.dev33.inditest.model.AppUser
import mobi.dev33.inditest.model.AppUserGender
import mobi.dev33.inditest.model.AppUserImages
import mobi.dev33.inditest.model.AppUserLocation

fun DomainUser.toApp() = AppUser(
    "${name.first} ${name.last}",
    AppUserGender.getByValue(gender),
    email,
    cell ?: phone,
    dateOfBirth,
    registrationDate,
    picture.toApp(),
    location?.toApp()
)

fun DomainUserImages.toApp() = AppUserImages(thumbnail, medium, large)
fun DomainUserLocation.toApp() = AppUserLocation(coordinates.latitude, coordinates.longitude)