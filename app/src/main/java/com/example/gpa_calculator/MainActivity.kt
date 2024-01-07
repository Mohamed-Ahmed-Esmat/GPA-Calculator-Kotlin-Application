    package com.example.gpa_calculator

    import android.annotation.SuppressLint
    import android.os.Bundle
    import android.view.View
    import android.widget.*
    import androidx.appcompat.app.AppCompatActivity
    import com.google.android.material.floatingactionbutton.FloatingActionButton

    class MainActivity : AppCompatActivity() {

        private lateinit var courseName: EditText
        private lateinit var containerLayout: LinearLayout
        private lateinit var addCourseFloat: FloatingActionButton
        private lateinit var gradeSpin: Spinner
        private lateinit var creditsSpin: Spinner
        private var editTextCount = 1 // Counter for generated EditText views

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            courseName = findViewById(R.id.courseName)
            containerLayout = findViewById(R.id.containerLayout)
            addCourseFloat = findViewById(R.id.addCourseFloat)
            gradeSpin = findViewById(R.id.gradeSpin)
            creditsSpin = findViewById(R.id.creditsSpin)

            val spinnerData = arrayOf("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F")
            val spinnerData2 = arrayOf("1", "2", "3", "4")
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerData)
            val spinnerAdapter2 = ArrayAdapter(this, R.layout.spinner_item, spinnerData2)
            gradeSpin.adapter = spinnerAdapter
            creditsSpin.adapter = spinnerAdapter2
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
                    addCourseFloat.visibility = View.GONE
                }
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private fun addNewCourse() {
            val linearLayout = LinearLayout(this)
            linearLayout.orientation = LinearLayout.HORIZONTAL

            val newEditText = EditText(this)
            val newSpinner = Spinner(this)
            val creditSpinner = Spinner(this)

            // Set layout parameters for horizontal arrangement
            creditSpinner.layoutParams = LinearLayout.LayoutParams(
                250, // Match width of creditsSpin
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newEditText.layoutParams = LinearLayout.LayoutParams(
                0,  // Width will be determined by weight
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f   // Weight for EditText to take up remaining space
            )

            // Apply design attributes from creditsSpin
            creditSpinner.background = resources.getDrawable(android.R.drawable.btn_dropdown)
            newEditText.hint = "Course Name"

            // Add dummy data for the spinner
            val spinnerData = arrayOf("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F")
            val spinnerData1 = arrayOf(1,2,3,4)
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerData)
            val spinnerAdapter1 = ArrayAdapter(this, R.layout.spinner_item, spinnerData1)
            newSpinner.adapter = spinnerAdapter
            creditSpinner.adapter = spinnerAdapter1
            linearLayout.addView(newSpinner)
            linearLayout.addView(newEditText)
            linearLayout.addView(creditSpinner)
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

        fun calculate(view: View) {
            val courses = Array(editTextCount) { "" }
            val credits = FloatArray(editTextCount)
            val grades = Array(editTextCount) { "" }
            var coursePoints = 0.0f
            var gradePoints = 0.0f

            println("\nEnter course details:\n")

//                for (i in courses.indices) {
//                    println("\nCourse ${i + 1}:\n")
//
//                    print("Name: ")
//                    courses[i] = scanner.nextLine()
//
//                    print("Credit hours: ")
//                    credits[i] = scanner.nextFloat()
//                    scanner.nextLine() // Consume newline character
//
//                    print("Grade: ")
//                    grades[i] = scanner.nextLine()
//
//                    gradePoints += gradeWeightsCourse(grades[i], credits[i])
//                    coursePoints += credits[i]
//                }

        }
    }
