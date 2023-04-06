package com.example.myapplication.roomDb.db

//Msql database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//list of entities i.e the tables , version no od Db used for migrating from one db to another for fut rate updated version of the app ,export scheme which tell roomdb to able to export database schema information as json file at compiler time
@Database(entities = [StudentDataClass::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    //      need instance of the database -> for that we will use applicationContext as it will be in lifecycle for the whole app
//    best practice : single instance of db class through single execution of application as it is very expensive instance i.e resource consuming

    //    companion : singleton just like static in java
//    so whenever we call the class it will create and use only one instance of the StudentDatabase
    companion object {
        @Volatile //marked as volatile -> marks jvm backing field of property as well volatile -> means rights to the filed will immediately visible to other other threads
//        the @Volatile annotation is used to indicate that a variable's value might be modified by multiple threads simultaneously, and therefore, any changes to its value should be immediately visible to other threads.
//        Without the @Volatile annotation, a race condition could occur when multiple threads try to access the instance variable at the same time. In this case, one thread might see a stale value of the instance variable, leading to the creation of multiple instances of the Singleton class. However, by using the @Volatile annotation, we ensure that any changes made to the instance variable are immediately visible to other threads, avoiding this potential issue.
        private var INSTANCE: StudentDatabase? =
            null //capital variable name for constant singleton instance

        fun getInstance(context: Context): StudentDatabase {
//read from synchronized info.txt
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,//why we need and why to access using context while in MainAActivity we don't require context ,can access directly -> context in singleton class.txt
                        StudentDatabase::class.java,//name of database class -> this only
                        "student_data_database"//name of database
                    ).build()
                }
                return instance
            }
        }
    }
}