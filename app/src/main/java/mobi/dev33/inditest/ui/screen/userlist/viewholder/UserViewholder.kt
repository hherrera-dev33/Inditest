package mobi.dev33.inditest.ui.screen.userlist.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mobi.dev33.inditest.R
import mobi.dev33.inditest.databinding.AdapterUserBinding
import mobi.dev33.inditest.model.AppUser

class UserViewholder(private val binding: AdapterUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AppUser?, onItemClick: (selectedItem: AppUser) -> Unit) {
        item?.picture?.thumbnail?.let {
            Glide.with(binding.root).load(it).placeholder(R.drawable.user_picture_placeholder)
                .into(binding.ivUser)
        }
        binding.tvUserEmail.text = item?.email
        binding.tvUserName.text = item?.name
        item?.let { appUser -> binding.root.setOnClickListener { onItemClick(appUser) } }
    }
}