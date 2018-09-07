package org.kevin.magnial.view

import android.content.Context

class AppTools {

    companion object {
        fun dp2px(context: Context, value: Int): Int {
            return (context.resources.displayMetrics.density * value).toInt()
        }
    }


}