package com.example.samplejson_jan10.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samplejson_jan10.databinding.RowItemUserBinding
import com.example.samplejson_jan10.model.network.models.user.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val usersList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            RowItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun submitList(posts: List<User>) {
        usersList.clear()
        usersList.addAll(posts)
        notifyDataSetChanged()
    }

    class UserViewHolder(
        private val binding: RowItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) = with(binding) {
            idTv.text = user.id.toString()
            nameTv.text = user.name
            usernameTv.text = user.username
            emailTv.text = user.email
            streetTv.text = user.address.street
            suiteTv.text = user.address.suite
            cityTv.text = user.address.city
            zipcodeTv.text = user.address.zipcode
            latTv.text = user.address.geo.lat
            lonTv.text = user.address.geo.lng
            phoneTv.text = user.phone
            companyNameTv.text = user.company.name
            catchPhraseTv.text = user.company.catchPhrase
            bsTv.text = user.company.bs
        }
    }
}