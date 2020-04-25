package com.taymath.tutortoolkit

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.taymath.tutortoolkit.database.Grade
import com.taymath.tutortoolkit.database.StudentDatabase
import com.taymath.tutortoolkit.database.StudentDatabaseDao
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

@RunWith(JUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var userDao: StudentDatabaseDao
    private lateinit var db: StudentDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().getTargetContext()
        db = Room.inMemoryDatabaseBuilder(
            context, StudentDatabase::class.java
        ).build()
        userDao = db.studentDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val grade: Grade = Grade()
        userDao.insertGrade(grade)
        val byName = userDao.getGrade()
        assertThat(byName, equalTo(grade))
    }
}