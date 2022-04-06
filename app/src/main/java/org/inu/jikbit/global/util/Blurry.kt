package org.inu.jikbit.global.util

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import jp.wasabeef.blurry.Blurry

object Blurry {

    fun blurryOn(view: View){
        Blurry.with(view.context)
            .color(Color.argb(77, 128, 128, 128))
            .radius(4)
            .sampling(6)
            .postOnto(view as ViewGroup)
    }

    fun blurryOff(view:View){
        Blurry.delete(view as ViewGroup)
    }

    fun Blurry.Composer.postOnto(view: ViewGroup) {
        view.post { onto(view) }        // todo 해석하기
    }
}