package com.example.gpa_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.HashMap

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

fun gradeWeightsperCourse(grade: String, credits: Float): Float {
    val gradesWeight: Map<String, Float> = mapOf(
        "A+" to 4.0f,
        "A" to 4.0f,
        "A-" to 3.7f,
        "B+" to 3.3f,
        "B" to 3.0f,
        "B-" to 2.7f,
        "C+" to 2.3f,
        "C" to 2.0f,
        "C-" to 1.7f,
        "D+" to 1.3f,
        "D" to 1.0f,
        "F" to 0.0f
    )

    return gradesWeight[grade]?.times(credits) ?: run {
        println("Invalid grade")
        0.0f
    }
}