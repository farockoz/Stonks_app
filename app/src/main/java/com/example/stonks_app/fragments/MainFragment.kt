package com.example.stonks_app.fragments

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.stonks_app.CurrencyModel
import com.example.stonks_app.MainViewModel
import com.example.stonks_app.R
import com.example.stonks_app.adaters.Adapter
import com.example.stonks_app.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONArray
import org.json.JSONObject

const val API_KEY = "P3MO6TiVOppzktqOzMz0QpoVZ2koxq7idlxCkSAo"
class MainFragment : Fragment() {
    private val fList = listOf(
        Most_popular_currencyFragment.newInstance(),
        All_currency_fragment.newInstance()
    )
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentMainBinding
    private val model:MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        init()
        updateCard()
        requestCurrency("latest")
    }
    private fun init() = with(binding){
        val adapter = Adapter(activity as FragmentActivity, fList)
        val tList = listOf(requireActivity().resources.getString(R.string.tab_1_name), requireActivity().resources.getString(R.string.tab_2_name))
        vp.adapter= adapter
        TabLayoutMediator(TLMain, vp){
            tab, position -> tab.text = tList[position]

        }.attach()

    }
    private fun updateCard() = with(binding){
        model.LiveDataCurrent.observe(viewLifecycleOwner){
        }

    }

    private fun permissionListener(){
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            Toast.makeText(activity, "$it", Toast.LENGTH_LONG).show()
        }
    }
    private fun checkPermission(){
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
    private fun requestCurrency(method:String){
        val url = "https://api.currencyapi.com/v3/" +
                "$method" +
                "?apikey=$API_KEY" +
                "&currencies="
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(Request.Method.GET,
            url,
            {
                result -> parsecurrency(result)
            },
            {
                error -> Log.d("Mylog","Error: $error")
            }
        )
        queue.add(request)
    }
    private fun parsecurrency(result:String){
        val mainObject = JSONObject(result)
        val set = JSONArray(result)
        val item = CurrencyModel(
            mainObject.getJSONObject("data").
            getJSONObject("HKD").
            getString("value")
        )
        Log.d("Mylog", "${item.value}")


    }
    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}