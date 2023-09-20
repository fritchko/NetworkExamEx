package com.example.examtest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.examtest.viewmodel.JokesViewModel
import com.example.examtest.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val jokesViewModel: JokesViewModel by viewModels()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            jokesViewModel.getJokes()
            observeData()
        }


    }

    fun observeData(){
        jokesViewModel.result.observe(viewLifecycleOwner) {jokeData ->
        if(jokeData != null){
            binding.textviewFirst.text = jokeData.setup
            binding.textviewSecond.text = jokeData.punchline
        }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}