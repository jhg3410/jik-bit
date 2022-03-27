package org.inu.jikbit.global.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import org.inu.jikbit.R

object GlideUtil {
    private const val default = R.drawable.ic_currency_bitcoin
    private const val defaultUrl = "https://cryptoicon-api.vercel.app/api/icon/"
    fun loadWithUrl(view: ImageView, coinName: String) {
        Glide.with(view.context)
            .load(defaultUrl.plus(coinName))
            .error(default)
            .placeholder(default)
            .into(view)
    }
}