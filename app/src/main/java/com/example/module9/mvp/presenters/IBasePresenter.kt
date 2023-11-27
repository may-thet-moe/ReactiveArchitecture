package com.example.module9.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface IBasePresenter {

    fun onUiReady (owner: LifecycleOwner)
}