package com.example.demo.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.demo.databinding.FragmentWebSiteBinding

class WebSiteFragment(val url: String) : Fragment() {

    private lateinit var binding: FragmentWebSiteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebSiteBinding.inflate(LayoutInflater.from(context))
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(url)
        return binding.root
    }

}