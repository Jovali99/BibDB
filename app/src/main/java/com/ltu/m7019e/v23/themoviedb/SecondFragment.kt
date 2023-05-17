package com.ltu.m7019e.v23.themoviedb

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.v23.themoviedb.adapter.GenreListAdapter
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentSecondBinding
import com.ltu.m7019e.v23.themoviedb.viewmodel.SecondFragmentViewModel
import com.ltu.m7019e.v23.themoviedb.viewmodel.SecondFragmentViewModelFactory


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var viewModel: SecondFragmentViewModel
    private lateinit var viewModelFactory: SecondFragmentViewModelFactory

    private var _binding: FragmentSecondBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        viewModelFactory = SecondFragmentViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SecondFragmentViewModel::class.java)

        // Set up the RecyclerView
        val adapter = GenreListAdapter()
        binding.genreListRv.adapter = adapter

        viewModel.genreList.observe(
            viewLifecycleOwner
        ) { genreList ->
            genreList?.let {
                adapter.submitList(genreList)
            }
        }

        /*
        viewModel.navigateToMovieDetail.observe(viewLifecycleOwner) { movie ->
            movie?.let{
                // inflate the popup dialog layout
                val builder = AlertDialog.Builder(requireContext())
                val popupView = LayoutInflater.from(requireContext()).inflate(R.layout.movie_info_popup, null)
                builder.setView(popupView)
                val dialog = builder.create()
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //needed for background

                dialog.show()

                viewModel.on_imdb_click(requireContext(), dialog, movie.imdb_link)


                viewModel.onMovieDetailNavigated()
            }
        }*/

        // Inflate the layout for this fragment
        Log.d("rec_test", "onCreateView: "+ binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

}