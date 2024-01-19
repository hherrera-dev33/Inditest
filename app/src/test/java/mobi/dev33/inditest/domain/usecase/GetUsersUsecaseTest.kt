package mobi.dev33.inditest.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSourceFactory
import androidx.paging.testing.asPagingSourceFactory
import androidx.paging.testing.asSnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import mobi.dev33.inditest.domain.model.DomainUser
import mobi.dev33.inditest.domain.model.DomainUserImages
import mobi.dev33.inditest.domain.model.DomainUserLocation
import mobi.dev33.inditest.domain.model.DomainUserLocationCoordinates
import mobi.dev33.inditest.domain.model.DomainUserLocationStreet
import mobi.dev33.inditest.domain.model.DomainUserLocationTimeZone
import mobi.dev33.inditest.domain.model.DomainUserName
import mobi.dev33.inditest.domain.repository.IUserRepository
import org.junit.Before
import org.junit.Test

class GetUsersUsecaseTest {
    private lateinit var emptyUserListPageSource: PagingSourceFactory<Int, DomainUser>
    private lateinit var oneUserListPageSource: PagingSourceFactory<Int, DomainUser>

    @Before
    fun setUp() {
        emptyUserListPageSource = listOf<DomainUser>().asPagingSourceFactory()
        oneUserListPageSource = listOf(
            DomainUser(
                DomainUserName("Miss", "Samantha", "Carlson"),
                "Female",
                DomainUserLocation(
                    DomainUserLocationStreet(7971, "Fairview Road"),
                    "Dundee",
                    "Durham",
                    "United Kingdom",
                    "QM4 8EQ",
                    DomainUserLocationCoordinates("-12.2061", "125.7375"),
                    DomainUserLocationTimeZone("-10:00", "Hawaii")
                ),
                "samantha.carlson@example.com",
                -736585339630000,
                1253521979901,
                "016973 97589",
                "07550 795971",
                DomainUserImages(
                    "https://randomuser.me/api/portraits/thumb/women/58.jpg",
                    "https://randomuser.me/api/portraits/med/women/58.jpg",
                    "https://randomuser.me/api/portraits/women/58.jpg"
                )
            )
        ).asPagingSourceFactory()
    }

    @Test
    fun `get list of contacts, expect empty`(): Unit = runBlocking {
        val contacts = GetUsersUsecase(MockUserRepository(emptyUserListPageSource))()
        val snapshot = contacts.asSnapshot()
        assert(snapshot.isEmpty())
    }

    @Test
    fun `get list of contacts, expect only Samanta`(): Unit = runBlocking {
        val contacts = GetUsersUsecase(MockUserRepository(oneUserListPageSource))()
        val snapshot = contacts.asSnapshot()
        assert(snapshot.isNotEmpty() && snapshot.first().email == "samantha.carlson@example.com")
    }
}

class MockUserRepository(private val pagingSourceFactory: PagingSourceFactory<Int, DomainUser>) :
    IUserRepository {
    override suspend fun getUserList(): Flow<PagingData<DomainUser>> {
        return Pager(config = PagingConfig(20), pagingSourceFactory = pagingSourceFactory).flow
    }
}
