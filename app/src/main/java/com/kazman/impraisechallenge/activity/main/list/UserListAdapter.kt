package com.kazman.impraisechallenge.activity.main.list

import android.app.ActivityOptions
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kazman.impraisechallenge.R
import com.kazman.impraisechallenge.User
import com.kazman.impraisechallenge.activity.AbstractListAdapter
import com.kazman.impraisechallenge.activity.details.DetailsActivity
import com.kazman.impraisechallenge.databinding.ItemUserBinding
import com.kazman.impraisechallenge.userParcel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
class UserListAdapter : AbstractListAdapter<User, UserListAdapter.UserViewHolder>() {

    private val disposables = mutableListOf<Disposable>()
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserViewHolder {
        val binding = DataBindingUtil.inflate<ItemUserBinding>(LayoutInflater.from(parent?.context), R.layout.item_user, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val showTitlePanel: Boolean
        if (position == 0)
            showTitlePanel = true
        else showTitlePanel = isLongerThanTwoWeeks(position) && !isLongerThanTwoWeeks(position - 1)

        val viewModel = UserItemViewModel(items[position], showTitlePanel, position != 0)
        disposables.add(Observable.create(viewModel).subscribe({ user ->
            handleUserMovement(user)
        }, Throwable::printStackTrace, {

        }))

        holder.binding.viewModel = viewModel
    }

    private fun handleUserMovement(user: User) {
        val list = items.sortedByDescending { it.getLastInteraction()?.getTimeInMillis() ?: 0L }
        val oldIndex = items.indexOf(user)
        val index = list.indexOf(user)
        items.remove(user)
        items.add(index, user)
        notifyItemMoved(oldIndex, index)
        notifyItemChanged(index)
        notifyItemChanged(index + 1)
        if (oldIndex == items.size) {
            notifyItemChanged(oldIndex)
        } else {
            notifyItemChanged(oldIndex)
            notifyItemChanged(oldIndex + 1)
        }
    }

    private fun isLongerThanTwoWeeks(position: Int): Boolean {
        val lastInteractionTimeInMillis = items[position].getLastInteraction()?.getTimeInMillis() ?: 0L
        val currentTimeInMillis = System.currentTimeMillis()
        val result = TimeUnit.MILLISECONDS.toDays(currentTimeInMillis - lastInteractionTimeInMillis)

        if (result in 0..14)
            return false

        return true
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        super.onDetachedFromRecyclerView(recyclerView)
        disposables.forEach { it.dispose() }
    }

    inner class UserViewHolder(itemView: ItemUserBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding: ItemUserBinding = itemView.apply {
            root.setOnClickListener {
                val bundle = ActivityOptions.makeSceneTransitionAnimation(it.context as AppCompatActivity, itemView.civAvatar as View, "profile").toBundle()
                it.context.startActivity(Intent(it.context, DetailsActivity::class.java).putExtra(userParcel, items[adapterPosition]), bundle)
            }
        }
    }
}
