package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.adapter.posterRecyclerViewAdapter
import com.wafflestudio.waffleseminar2024.data.database.LikedMovie
import com.wafflestudio.waffleseminar2024.databinding.FragmentLikedMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikedMoviesFragment : Fragment() {
    private lateinit var navController: NavController
    private var _binding: FragmentLikedMoviesBinding? = null
    private val binding get() = _binding!!

    lateinit var posterRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLikedMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        posterRecyclerView = binding.posterRecyclerView

        val data = listOf(LikedMovie(123, "/aosm8NMQ3UyoBVpSxyimorCQykC.jpg"),
            LikedMovie(1234, "/iBVcwOVZNGNCNlOLFmcYJW83tTu.jpg"),
            LikedMovie(444483, "/vyzS55N83jgDtgMeyYJOD7ppYMz.jpg")
        )
        posterRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        posterRecyclerView.adapter = posterRecyclerViewAdapter(data) { likedMovie ->
            Log.d("likedMovieId: ", likedMovie.id.toString())
            val action = LikedMoviesFragmentDirections.actionToLikedMovieDetailFragment(likedMovie.id)
            navController.navigate(action)
        }
    }
}