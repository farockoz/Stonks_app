package com.example.stonks_app.fragments

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stonks_app.CurrencyModel
import com.example.stonks_app.R
import com.example.stonks_app.adaters.Currency_adapter
import com.example.stonks_app.databinding.FragmentMostPopularCurrencyBinding


class Most_popular_currencyFragment : Fragment() {
    private lateinit var binding: FragmentMostPopularCurrencyBinding
    private lateinit var adapter: Currency_adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMostPopularCurrencyBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }
    private fun initRcView() = with(binding){
        RVMPC.layoutManager = LinearLayoutManager(activity)
        adapter = Currency_adapter()
        RVMPC.adapter= adapter
        val list = listOf(
            CurrencyModel("USD"),
            CurrencyModel("EUR"),
            CurrencyModel("CAD"),
            CurrencyModel("CHF"),
            CurrencyModel("GBP")
        )
        adapter.submitList(list)

    }

    companion object {

        @JvmStatic
        fun newInstance() = Most_popular_currencyFragment()
    }
}