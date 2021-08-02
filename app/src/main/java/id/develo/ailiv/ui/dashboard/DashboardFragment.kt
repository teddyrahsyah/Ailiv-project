package id.develo.ailiv.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import id.develo.ailiv.data.source.local.NutrientPreference
import id.develo.ailiv.databinding.FragmentDashboardBinding
import id.develo.ailiv.viewmodel.ViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private var listDailyNutrient = arrayListOf<Float>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val dataFoodname = DashboardFragmentArgs.fromBundle(arguments as Bundle).foodname

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]

            var filename: String? = null
            if (dataFoodname.contains("nasi_goreng")) {
                filename = dataFoodname.replace("jpeg", "json")
            }

            val nutrition = viewModel.getNutrition(filename ?: "nasi_goreng.json")

            // Get Daily Nutrients from Preference
            getDailyNutrient()

            Log.d("TEST", listDailyNutrient.toString())

            val nutritionAdapter = NutritionAdapter(listDailyNutrient)
            nutritionAdapter.setNutrition(nutrition)

            with(binding.recyclerView) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = nutritionAdapter
            }

            binding.buttonSecond.setOnClickListener {
                val toPickImageFragment =
                    DashboardFragmentDirections.actionDashboardFragmentToPickImageFragment()
                findNavController().navigate(toPickImageFragment)
            }
        }
    }

    private fun getDailyNutrient() {
        val mNutrientPreference = NutrientPreference(requireContext())
        val dailyNutrient = mNutrientPreference.getDailyNutrient()

        listDailyNutrient.add(dailyNutrient.lemak)
        listDailyNutrient.add(dailyNutrient.kalori)
        listDailyNutrient.add(dailyNutrient.kolestrol)
        listDailyNutrient.add(dailyNutrient.protein)
        listDailyNutrient.add(dailyNutrient.karbohidrat)
        listDailyNutrient.add(dailyNutrient.sodium)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}