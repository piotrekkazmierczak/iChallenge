package com.kazman.impraisechallenge.view

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.kazman.impraisechallenge.R
import de.hdodenhof.circleimageview.CircleImageView


/**
 * Created by piotrkazmierczak on 05.04.2017.
 */

@BindingAdapter("bind:listAdapter")
fun setListAdapter(view: RecyclerView, listAdapter: RecyclerView.Adapter<*>?) {
    view.layoutManager = LinearLayoutManager(view.context)
    if (listAdapter != null) {
        view.adapter = listAdapter
    }
}

@BindingAdapter("android:src")
fun setUrl(view: CircleImageView, url: String?) {
    Glide.with(view.context).load(url ?: R.drawable.ic_person_grey_800_24dp).asBitmap().into(view)
}

@BindingAdapter("android:onClick")
fun setOnClick(view: View, onClick: () -> Unit) {
    view.setOnClickListener { onClick() }
}
