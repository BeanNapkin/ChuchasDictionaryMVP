package pro.fateeva.chuchasdictionarymvp.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pro.fateeva.chuchasdictionarymvp.rx.ISchedulerProvider

class SchedulerProvider : ISchedulerProvider {
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun io(): Scheduler = Schedulers.io()
}