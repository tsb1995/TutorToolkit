/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taymath.tutortoolkit.studentdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDatabaseDao {

    // Student Table Dao
    @Insert
    fun insertStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Query("DELETE FROM student_table WHERE studentId = :studentId")
    fun deleteStudent(studentId: Long)

    @Query("DELETE FROM student_table")
    fun clearStudentTable()

    @Query("SELECT * FROM student_table ORDER BY studentId DESC LIMIT 1")
    fun getStudent(): Student?

    @Query("SELECT * FROM student_table ORDER BY studentId DESC")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * from student_table WHERE studentId = :key")
    fun getStudentWithId(key: Long): LiveData<Student>

    @Query("SELECT * FROM student_table WHERE studentId = :studentId")
    fun getStudentClassWithId(studentId: Long): Student

    @Query("SELECT * from student_table WHERE student_name = :studentName")
    fun getStudentWithName(studentName: String): LiveData<Student>



    // Grade Table Dao
    @Insert
    fun insertGrade(grade: Grade)

    @Update
    fun updateGrade(grade: Grade)

    @Query("DELETE FROM grade_table WHERE student_id_long = :studentId")
    fun deleteGradeTable(studentId: Long)

    @Query("DELETE FROM grade_table")
    fun clearGradeTable()

    @Query("SELECT * FROM grade_table ORDER BY gradeId DESC LIMIT 1")
    fun getGrade(): Grade?

    @Query("SELECT * FROM grade_table ORDER BY student_name_string DESC")
    fun getAllGrades(): LiveData<List<Grade>>

    @Query("SELECT * FROM grade_table WHERE student_name_string = :studentName")
    fun getGradesForStudent(studentName: String): LiveData<List<Grade>>

    @Query("SELECT * from grade_table WHERE gradeId = :key")
    fun getGradeWithId(key: Long): LiveData<Grade>

    @Query("SELECT * from grade_table WHERE student_id_long = :studentId")
    fun getGradesWithStudentId(studentId: Long): LiveData<List<Grade>>

}

