package org.inu.jikbit.global.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.inu.jikbit.global.util.Event

abstract class BaseViewModel : ViewModel() {

    val viewEvent = MutableLiveData<Event<Any>>()

    fun viewEvent(content: Any) {
        viewEvent.value = Event(content)
    }
}