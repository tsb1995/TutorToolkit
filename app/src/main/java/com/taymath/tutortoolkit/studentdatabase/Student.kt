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

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student  (

        @PrimaryKey(autoGenerate = true)
        var studentId: Long = 0L,

        @ColumnInfo(name="student_name")
        var studentNameString: String = "BLANK STUDENT",

        @ColumnInfo(name = "todo_string")
        var todoString: String = "CHECK_Student.KT",

        @ColumnInfo(name = "subject")
        var subjectString: String = "CHECK_Student.KT",

        @ColumnInfo(name = "grade_level")
        var gradeLevelString: String = "CHECK_Student.KT",

        @ColumnInfo(name = "address")
        var addressString: String = "CHECK_Student.KT",

        @ColumnInfo(name = "email")
        var emailString: String = "CHECK_Student.KT"

)
