package org.inu.jikbit.global.util

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.inu.jikbit.R
import java.text.DecimalFormat

object Span {
    fun plusKRW(view: TextView,div:String,text:String) {
        val df = DecimalFormat("#,###.##")
        val builder = SpannableStringBuilder(div+"\n"+df.format(text.toDouble())+" KRW")

        val boldSpan = StyleSpan(Typeface.BOLD)
        val sizeSpan = RelativeSizeSpan(1.1f)
        val colorSpan = ForegroundColorSpan(ContextCompat.getColor(view.context,R.color.gray10))

        builder.run {
            setSpan(boldSpan, lastIndex - 2, lastIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(sizeSpan, lastIndex - 2, lastIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(colorSpan, lastIndex - 2, lastIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        view.text = builder
    }

    fun lastTextEmp(view: TextView,div:String,text:String) {
        val df = DecimalFormat("#,###.##")
        val builder = SpannableStringBuilder(div+"\n"+df.format(text.toDouble()))

        val boldSpan = StyleSpan(Typeface.BOLD)
        val sizeSpan = RelativeSizeSpan(1.5f)
        val colorSpan = ForegroundColorSpan(ContextCompat.getColor(view.context,R.color.gray20))

        builder.run {
            setSpan(boldSpan, div.lastIndex+1 , lastIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(sizeSpan, div.lastIndex+1 , lastIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(colorSpan,div.lastIndex+1 , lastIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        view.text = builder
    }

    @SuppressLint("SetTextI18n")
    fun textDecimalFormat(view:TextView, text:String?){
        val df = DecimalFormat("#,###.##")
        if (text?.contains("%") == true){
            val textNoPercent = text.substring(0,text.lastIndex)
            view.text = df.format(textNoPercent.toDouble()) + "%"
        }
        else{
        view.text = df.format(text?.toDouble())
        }
    }


    fun decimalFormat(text:String?): String{
        val df = DecimalFormat("#,###.##")
        return if (text?.contains("%") == true){
            val textNoPercent = text.substring(0,text.lastIndex)
            df.format(textNoPercent.toDouble()) + "%"
        } else{
            df.format(text?.toDouble())
        }
    }

}