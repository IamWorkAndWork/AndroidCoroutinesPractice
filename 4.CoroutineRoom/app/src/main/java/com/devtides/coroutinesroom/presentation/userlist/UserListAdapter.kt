package com.devtides.coroutinesroom.presentation.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devtides.coroutinesroom.R
import com.devtides.coroutinesroom.data.model.User
import kotlinx.android.synthetic.main.user_list_view_holder.view.*

class UserListAdapter : ListAdapter<User, RecyclerView.ViewHolder>(diffUser) {

    private var listener: ((User) -> Unit)? = null

    companion object {
        private val diffUser = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setListener(listener: ((User) -> Unit)?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bindItem(getItem(position))
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val userNameTxt = itemView.userNameTxt
        val userInfoTxt = itemView.userInfoTxt
        val cardItem = itemView.cardItem

        var user: User? = null

        init {
            cardItem?.setOnClickListener {
                user?.let {
                    listener?.invoke(it)
                }
            }
        }

        fun bindItem(item: User?) {
            this.user = item
            userNameTxt.text = item?.username
            userInfoTxt.text = item?.info
        }

    }

}