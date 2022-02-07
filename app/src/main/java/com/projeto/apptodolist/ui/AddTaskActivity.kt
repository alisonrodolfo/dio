package com.projeto.apptodolist.ui

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.projeto.apptodolist.databinding.ActivityAddTaskBinding
import com.projeto.apptodolist.extensions.datasource.TaskDataSource
import com.projeto.apptodolist.extensions.format
import com.projeto.apptodolist.extensions.text
import com.projeto.apptodolist.model.Task
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(TASK_ID)){
            val taskId = intent.getIntExtra(TASK_ID,0)
            TaskDataSource.findById(taskId)?.let {
                binding.tilTitle.text = it.title
                binding.tilData.text = it.date
                binding.tilHora.text = it.hour
            }
        }

        insertListners()
    }

    private fun insertListners(){
        binding.tilData.editText?.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilData.text = (Date(it + offset).format())
            }
            datePicker.show(supportFragmentManager,"DATE_PICKER_TAG")
        }
        binding.tilHora.editText?.setOnClickListener {
            val timePicket = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicket.addOnPositiveButtonClickListener {
                val minute = if(timePicket.minute in 0..9) "0${timePicket.minute}" else timePicket.minute
                val hour = if(timePicket.hour in 0..9) "0${timePicket.hour}" else timePicket.hour
                binding.tilHora.text = "$hour:$minute"
            }
            timePicket.show(supportFragmentManager,null)
        }
        binding.btlCancel.setOnClickListener {
            finish()

        }

        binding.btnNewTask.setOnClickListener {
            val task = Task(
                title = binding.tilTitle.text,
                hour = binding.tilHora.text,
                date = binding.tilData.text,
                id = intent.getIntExtra(TASK_ID, 0)
            )
            TaskDataSource.insertTask(task)
            setResult(Activity.RESULT_OK)
            finish()
        }

    }

    companion object{
      const val TASK_ID  = "TASK_ID"
    }
}