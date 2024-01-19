package mobi.dev33.inditest.ui.screen.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import mobi.dev33.inditest.domain.usecase.GetUsersUsecase
import mobi.dev33.inditest.model.AppUser
import mobi.dev33.inditest.model.mapper.toApp
import javax.inject.Inject

@HiltViewModel
class UserListFragmentViewModel @Inject constructor(
    private val getUsersUsecase: GetUsersUsecase
) : ViewModel() {

    var searchTerm: String? = null
        set(value) {
            field = value
            searchTermFlow.tryEmit(value)
        }

    private val searchTermFlow = MutableStateFlow(searchTerm)

    private val mUserListUiStatusFlow =
        MutableSharedFlow<PagingData<AppUser>>(replay = 1)
    val userListUiStatusFlow = mUserListUiStatusFlow.asSharedFlow()

    init {
        requestLoadUsers()
    }

    fun requestLoadUsers() {
        viewModelScope.launch((Dispatchers.IO)) {
            mUserListUiStatusFlow.emitAll(
                getUsersUsecase().map { it.map { user -> user.toApp() } }.cachedIn(viewModelScope)
                    .combine(searchTermFlow) { f1, f2 ->
                        if (f2.isNullOrBlank()) f1
                        else f1.filter { it.email.contains(f2) or it.name.contains(f2) }
                    }
            )
        }
    }

}