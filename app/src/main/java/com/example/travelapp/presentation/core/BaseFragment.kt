package com.example.travelapp.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.travelapp.presentation.ProgressDialog
import com.example.travelapp.presentation.util.extension.observe

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    protected abstract fun getLayoutRes(): Int
    protected lateinit var binding: DB
    protected abstract fun getViewModelClass(): Class<VM>

    protected val viewModel: VM by lazy { ViewModelProvider(this).get(getViewModelClass()) }
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initValue()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        observe(viewModel.isLoadingRequest) {
            if (it)
                startProgress()
            else
                dismissProgress()
        }
        observe(viewModel.isErrorRequest){
            showToastMessage(it)
        }
    }

    private fun initValue() {
        progressDialog = ProgressDialog(requireActivity())
    }

    fun startProgress() {
        if (progressDialog.dialog?.isShowing != true)
            progressDialog.startDialog()
    }

    fun dismissProgress() {
        progressDialog.dismissDialog()
    }

    fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}