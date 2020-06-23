package project.ramezreda.resumy.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import project.ramezreda.resumy.roomdb.dao.BasicInfoDao
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [BasicInfoEntity::class], version = 1)
abstract class ResumeRoomDatabase : RoomDatabase() {
    abstract fun basicInfoDao(): BasicInfoDao?

    @Volatile
    private var instance: ResumeRoomDatabase? = null
    private val noOfThreads = 4
    val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(noOfThreads)

    open fun getDatabase(context: Context): ResumeRoomDatabase? {
        if (instance == null) {
            synchronized(ResumeRoomDatabase::class.java) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ResumeRoomDatabase::class.java, "resumy_database"
                    ).build()
                }
            }
        }
        return instance
    }
}