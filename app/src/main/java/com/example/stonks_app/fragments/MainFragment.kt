package com.example.stonks_app.fragments

import android.Manifest
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.example.stonks_app.R
import com.example.stonks_app.adaters.Adapter
import com.example.stonks_app.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : Fragment() {
    private val fList = listOf(
        Most_popular_currencyFragment.newInstance(),
        All_currency_fragment.newInstance()
    )
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        init()
    }
    private fun init() = with(binding){
        val adapter = Adapter(activity as FragmentActivity, fList)
        val tList = listOf(requireActivity().resources.getString(R.string.tab_1_name), requireActivity().resources.getString(R.string.tab_2_name))
        vp.adapter= adapter
        TabLayoutMediator(TLMain, vp){
            tab, position -> tab.text = tList[position]

        }.attach()

    }

    private fun permissiomListener(){
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            Toast.makeText(activity, "$it", Toast.LENGTH_LONG).show()
        }

    }
    private fun checkPermission(){
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
            permissiomListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}