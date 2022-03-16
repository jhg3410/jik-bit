package org.inu.jikbit.util

import android.graphics.Color
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
        val df = DecimalFormat("##00.00")
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
}