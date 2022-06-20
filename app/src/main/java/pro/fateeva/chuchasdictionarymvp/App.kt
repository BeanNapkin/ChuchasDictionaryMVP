package pro.fateeva.chuchasdictionarymvp

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pro.fateeva.chuchasdictionarymvp.koin.AppModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(AppModule.mainModule)
        }
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }