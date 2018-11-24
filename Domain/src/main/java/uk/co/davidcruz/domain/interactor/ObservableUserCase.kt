package uk.co.davidcruz.domain.interactor

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import uk.co.davidcruz.domain.executor.PostExecutionThread

abstract class ObservableUserCase<T, in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {
    private val disposable = CompositeDisposable()

    protected abstract fun buildUserCaseObservable(params: Params? = null): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        val observable = this.buildUserCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
            .subscribeWith(observer)

        addDisposable(observable)
    }

    private fun addDisposable(disposable: Disposable) {
        this.disposable.add(disposable)
    }

    private fun dispose() {
        this.disposable.dispose()
    }
}