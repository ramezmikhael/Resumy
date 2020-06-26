package project.ramezreda.resumy.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import project.ramezreda.resumy.roomdb.dao.BasicInfoDao
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [BasicInfoEntity::class], version = 1)
abstract class ResumeRoomDatabase : RoomDatabase() {
    abstract fun basicInfoDao(): BasicInfoDao?

    companion object {
        @Volatile
        private var instance: ResumeRoomDatabase? = null
        private const val noOfThreads = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(noOfThreads)

        private var dbCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                val basicInfo = BasicInfoEntity(fullName = "", email = "", phone = "", summary = "", picture =  null)
                GlobalScope.launch {
                    instance?.basicInfoDao()?.insert(basicInfo)
                }
            }
        }

        open fun getDatabase(context: Context): ResumeRoomDatabase? {
            if (instance == null) {
                synchronized(ResumeRoomDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            ResumeRoomDatabase::class.java, "resumy_database")
                            .addCallback(dbCallback)
                            .build()
                    }
                }
            }
            return instance
        }
    }
}