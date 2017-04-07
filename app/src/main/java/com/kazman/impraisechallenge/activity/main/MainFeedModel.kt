package com.kazman.impraisechallenge.activity.main

import com.kazman.impraisechallenge.BR
import com.kazman.impraisechallenge.User
import com.kazman.impraisechallenge.UsersList
import com.kazman.impraisechallenge.activity.main.list.UserListAdapter
import com.kazman.impraisechallenge.di.Injector
import com.kazman.impraisechallenge.toast
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
class MainFeedModel {
    val listAdapter = UserListAdapter()
    var changeCallback: ((Int) -> Unit)? = null

    fun loadUsers() {
        getUserListSingle().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listAdapter.addItems(it)
                    changeCallback?.invoke(BR.adapter)
                }, {
                    toast(it.message)
                })
    }

    private fun getUserListSingle(): Single<List<User>> {
        return Single.create { emitter ->
            val inputStream = getInputStream()
            if (inputStream == null) {
                emitter.onError(Throwable("InputStream is null"))
            } else {
                val bufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
                var parsedString = ""
                do {
                    val str = bufferedReader.readLine()
                    parsedString += str ?: ""
                } while (str != null)
                bufferedReader.close()
                val list = parseStringToUserList(parsedString).sortedByDescending{it.getLastInteraction()?.getTimeInMillis() ?: 0L}
                if (list.isNotEmpty())
                    emitter.onSuccess(list)
                else
                    emitter.onError(Throwable("List is empty, parsing JSON went wrong"))
            }
        }
    }

    private fun getInputStream(): InputStream? {
        return Injector.component?.getContext()?.assets?.open("users.json")
    }

    private fun parseStringToUserList(rawString: String): List<User> {
        val list: UsersList? = try {
            getJsonAdapter().fromJson(rawString)
        } catch (e: JsonEncodingException) {
            null
        }
        return list?.users ?: arrayListOf<User>()
    }

    private fun getJsonAdapter(): JsonAdapter<UsersList> {
        return Moshi.Builder().build().adapter(UsersList::class.java)
    }

}
