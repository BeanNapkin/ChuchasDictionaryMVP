package pro.fateeva.chuchasdictionarymvp

import android.app.Application
import android.content.Context

class App : Application() {

    lateinit var appDependenciesComponent: AppDependenciesComponent

    override fun onCreate() {
        super.onCreate()
        appDependenciesComponent = DaggerAppDependenciesComponent
            .builder()
            .build()
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }