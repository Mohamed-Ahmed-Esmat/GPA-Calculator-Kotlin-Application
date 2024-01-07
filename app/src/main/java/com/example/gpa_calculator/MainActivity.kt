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
        private lateinit var calcBtn: Button
        private lateinit var gpaText: TextView
        private var editTextCount = 1 // Counter for generated EditText views

        override fun onCreate(savedInstanceState: Bundle?) {
           // gpaText.text = "GPA: 0.00"
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            courseName = findViewById(R.id.courseName)
            containerLayout = findViewById(R.id.containerLayout)
            addCourseFloat = findViewById(R.id.addCourseFloat)
            gradeSpin = findViewById(R.id.gradeSpin)
            creditsSpin = findViewById(R.id.creditsSpin)
            calcBtn = findViewById(R.id.calcBtn)
            gpaText = findViewById(R.id.gpaText)
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




        fun calculate(view: View) {
            val courses = mutableListOf<String>()
            val credits = mutableListOf<Float>()
            val grades = mutableListOf<String>()
            var cummulative = 0.0f
            var coursePoints = 0.0f
            var gradePoints = 0.0f
            println("\nEnter course details:\n")

                for (i in 0 until containerLayout.childCount) {
                    val linearLayout = containerLayout.getChildAt(i) as LinearLayout

                    // Access the EditText within the linearLayout
                    val courseNameEditText = linearLayout.getChildAt(1) as EditText
                    val courseName = courseNameEditText.text.toString().trim()

                    // Add course name to the list if it's not empty
                    val gradeSpinner = linearLayout.getChildAt(0) as Spinner
                    val grade = gradeSpinner.selectedItem.toString()

                    val creditSpinner = linearLayout.getChildAt(2) as Spinner
                    val creditStr = creditSpinner.selectedItem.toString()
                    val credit = creditStr.toFloat()  // Assuming credit values are stored as strings

                    if (courseName.isNotEmpty()) {
                        courses.add(courseName)
                        credits.add(credit)
                        grades.add(grade)
                    }else{
                        Toast.makeText(
                            this,
                            "Please enter course name",
                            Toast.LENGTH_SHORT
                        ).show()
                    }



                    gradePoints += gradeWeightsperCourse(grades[i], credits[i])
                    coursePoints += credits[i]
                    cummulative = gradePoints / coursePoints
                    gpaText.text = "GPA: %.2f".format(cummulative)
                }

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
