package com.aa.fakestore.ui.base

import androidx.lifecycle.ViewModel
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.utils.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.reflect.KFunction1


abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun <T> bindStateUpdates(
        stateObservable: Observable<State<T>>,
        onError: (Throwable) -> Unit,
        onNext: (State<T>) -> Unit
    ) {
        try {
            stateObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = onError,
                    onNext = onNext,
                )
                .addTo(compositeDisposable)
        } catch (e: Exception) {
            onError(e)
        }
    }
}
