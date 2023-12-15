package com.example.gpa_calculator

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var courseName: EditText
    private lateinit var containerLayout: LinearLayout
    private lateinit var addCourseFloat: FloatingActionButton

    private var editTextCount = 1 // Counter for generated EditText views

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        courseName = findViewById(R.id.courseName)
        containerLayout = findViewById(R.id.containerLayout)
        addCourseFloat = findViewById(R.id.addCourseFloat)

        addCourseFloat.setOnClickListener {
            if (editTextCount < 7) {
                addNewCourse()
                editTextCount++
            } else {
                Toast.makeText(
                    this,
                    "Maximum number of courses reached",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun addNewCourse() {
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        val newEditText = EditText(this)
        val newSpinner = Spinner(this)
        newEditText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        newEditText.hint = "Course Name"
        linearLayout.addView(newEditText)
        linearLayout.addView(newSpinner)
        containerLayout.addView(linearLayout)
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
}
