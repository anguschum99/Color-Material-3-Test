package com.example.bastyle.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.bastyle.R

data class Student (
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val age:Int,
    @StringRes val hobby:Int
)



val students = listOf<Student>(
    Student(R.drawable.haruka__new_year_,R.string.student_name_1,15, R.string.hobby_1),
    Student(R.drawable.himari,R.string.student_name_2,17,R.string.hobby_2),
    Student(R.drawable.mina,R.string.student_name_3,16,R.string.hobby_3),
    Student(R.drawable.toki__bunny_girl_,R.string.student_name_4,16,R.string.hobby_4),
    Student(R.drawable.wakamo,R.string.student_name_5,17,R.string.hobby_5),
    Student(R.drawable.kotama__camping_,R.string.student_name_6,17,R.string.hobby_6),
    Student(R.drawable.hifumi__swimsuit_,R.string.student_name_7,16,R.string.hobby_7),
)