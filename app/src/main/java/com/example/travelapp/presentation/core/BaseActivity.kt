package com.example.travelapp.presentation.core

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.travelapp.presentation.ProgressDialog

abstract class BaseActivity<DB: ViewDataBinding, VM: BaseViewModel>: AppCompatActivity() {
    protected abstract fun getLayoutRes(): Int
    protected lateinit var binding: DB
    protected abstract fun getViewModelClass(): Class<VM>

    protected val viewModel: VM by lazy { ViewModelProvider(this).get(getViewModelClass()) }

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        binding.lifecycleOwner = this
        initValue()
    }

    private fun initValue() {
        progressDialog = ProgressDialog(this)
    }

    fun startProgress(){
        if(progressDialog.dialog?.isShowing != true)
            progressDialog.startDialog()
    }
    fun dismissProgress() {
        progressDialog.dismissDialog()
    }

    fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}