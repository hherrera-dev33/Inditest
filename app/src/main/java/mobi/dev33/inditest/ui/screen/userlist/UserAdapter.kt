package mobi.dev33.inditest.ui.screen.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import mobi.dev33.inditest.databinding.AdapterUserBinding
import mobi.dev33.inditest.model.AppUser
import mobi.dev33.inditest.ui.screen.userlist.viewholder.UserViewholder

class UserAdapter(val onItemClick: (selectedItem : AppUser) -> Unit) :
    PagingDataAdapter<AppUser, UserViewholder>(object : DiffUtil.ItemCallback<AppUser>() {
        override fun areItemsTheSame(oldItem: AppUser, newItem: AppUser) =
            oldItem.email == newItem.email

        override fun areContentsTheSame(oldItem: AppUser, newItem: AppUser) = oldItem == newItem
    }) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewholder(
        AdapterUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            ivUser.clipToOutline = true
        }
    )

    override fun onBindViewHolder(holder: UserViewholder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

}