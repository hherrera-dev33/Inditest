package mobi.dev33.inditest.ui.screen.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import mobi.dev33.inditest.R
import mobi.dev33.inditest.databinding.FragmentUserListBinding
import mobi.dev33.inditest.ui.screen.MainActivity

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var binding: FragmentUserListBinding
    private val viewModel: UserListFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserListBinding.inflate(inflater, container, false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        loadObservers()
    }

    private fun initLayout() {
        initToolbar()
        binding.swipeRefreshUser.setOnRefreshListener { loadData() }
        binding.recyclerUsers.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = UserAdapter(onItemClick = {
                findNavController().navigate(
                    UserListFragmentDirections.userListToUserDetail(it)
                )
            }).apply {
                stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }.also { this@UserListFragment.userAdapter = it }
                .withLoadStateFooter(UserLoadStateAdapter(userAdapter::retry))

        }
    }

    private fun initToolbar() {
        (activity as MainActivity?)?.setTransparentStatusBar(false)
        binding.toolbar.title = getString(R.string.screen_user_list)
        binding.toolbar.inflateMenu(R.menu.menu_searchbar)
        (binding.toolbar.menu.findItem(R.id.searchbar).actionView as SearchView).setOnQueryTextListener(object :
            OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = true
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchTerm = newText
                return true
            }
        })
    }

    private fun loadObservers() {
        lifecycleScope.launch {
            viewModel.userListUiStatusFlow.collectLatest { userAdapter.submitData(it) }
        }

        lifecycleScope.launch {
            userAdapter.loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.Loading -> {
                        binding.swipeRefreshUser.isRefreshing = true
                        showRecyclerView()
                    }

                    is LoadState.NotLoading -> {
                        binding.swipeRefreshUser.isRefreshing = false
                        if (userAdapter.itemCount > 0) showRecyclerView()
                        else showEmptyView()
                    }

                    is LoadState.Error -> {
                        binding.swipeRefreshUser.isRefreshing = false
                        showErrorView()
                    }
                }
            }
        }
    }

    private fun showErrorView() {
        binding.errorView.isVisible = true
        binding.recyclerUsers.isVisible = false
        binding.emptyView.isVisible = false
    }

    private fun showEmptyView() {
        binding.emptyView.isVisible = true
        binding.errorView.isVisible = false
        binding.recyclerUsers.isVisible = false
    }

    private fun showRecyclerView() {
        binding.recyclerUsers.isVisible = true
        binding.errorView.isVisible = false
        binding.emptyView.isVisible = false
    }

    private fun loadData() {
        viewModel.requestLoadUsers()
    }
}