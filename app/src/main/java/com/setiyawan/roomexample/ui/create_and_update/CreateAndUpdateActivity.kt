package com.setiyawan.roomexample.ui.create_and_update

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.setiyawan.roomexample.R
import com.setiyawan.roomexample.data.model.Person
import com.setiyawan.roomexample.databinding.ActivityCreateAndUpdateBinding
import com.setiyawan.roomexample.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateAndUpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAndUpdateBinding
    private val createUpdateViewModel: CreateUpdateViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAndUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupToolbar()
        onSubmit()
        needToGetData()
    }

    private fun setupToolbar(){
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun needToGetData(){
        if (!intent.getBooleanExtra("is_new", true)){
            val person : Person? = createUpdateViewModel.findById(intent.getIntExtra("id", 0))
            if(person != null){
                binding.content.deleteButton.visibility = View.VISIBLE
                binding.content.deleteButton.setOnClickListener {
                    createUpdateViewModel.delete(person)
                    finish()
                }
                binding.content.nameEditText.setText(person.name)
                binding.content.emailEditText.setText(person.email)
                binding.content.nimEditText.setText(person.nim)
            }
        }
    }
    private fun setNameError(err: String?){ binding.content.nameInputText.error = err }
    private fun setEmailError(err: String?) { binding.content.emailEditText.error = err }
    private fun setNimError(err: String?) { binding.content.nimEditText.error = err }

    private fun onSubmit(){
        binding.content.saveButton.setOnClickListener {
            val name = binding.content.nameEditText.text.toString().trim()
            val email = binding.content.emailEditText.text.toString().trim()
            val nim = binding.content.nimEditText.text.toString().trim()
            if(validate()){
                if (intent.getBooleanExtra("is_new", true)){
                    createUpdateViewModel.insert(name, email, nim)
                }else{
                    val passedId = intent.getIntExtra("id", 0)
                    createUpdateViewModel.update(passedId, name, email, nim)
                }
                finish()
            }
        }
    }


    private fun validate() : Boolean {
        setNimError(null)
        setNameError(null)
        setEmailError(null)
        if(binding.content.nameEditText.text.toString().trim().isEmpty()){
            setNameError("Name cannot be blank")
            return false
        }

        if(!Utils.isValidEmail(binding.content.emailEditText.text.toString().trim())){
            setEmailError("Email tidak valid")
            return false
        }

        if(binding.content.nimEditText.text.toString().trim().isEmpty()){
            setNimError("NIM cannot be empty")
            return false
        }
        return true
    }

}