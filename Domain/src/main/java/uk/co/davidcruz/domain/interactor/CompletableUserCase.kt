package uk.co.davidcruz.domain.interactor

import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import uk.co.davidcruz.domain.executor.PostExecutionThread

abstract class CompletableUserCase<in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {
    private val disposable = CompositeDisposable()

    protected abstract fun buildUserCaseCompletable(params: Params? = null): Completable

    open fun execute(observer: DisposableCompletableObserver, params: Params? = null) {
        val completable = this.buildUserCaseCompletable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
            .subscribeWith(observer)

        addDisposable(completable)
    }

    private fun addDisposable(disposable: Disposable) {
        this.disposable.add(disposable)
    }

    private fun dispose() {
        this.disposable.dispose()
    }
}