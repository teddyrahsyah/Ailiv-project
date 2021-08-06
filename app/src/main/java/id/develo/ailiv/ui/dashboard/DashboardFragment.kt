package id.develo.ailiv.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import id.develo.ailiv.data.source.local.NutrientPreference
import id.develo.ailiv.data.source.local.entity.AccumulatedNutrient
import id.develo.ailiv.data.source.local.entity.UserModelEntity
import id.develo.ailiv.databinding.FragmentDashboardBinding
import id.develo.ailiv.viewmodel.ViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var userModel: UserModelEntity

    private lateinit var mNutrientPreference: NutrientPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            mNutrientPreference = NutrientPreference(requireContext())

            val dataFoodname = DashboardFragmentArgs.fromBundle(arguments as Bundle).foodname

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]

            var filename: String? = null
            var foodname: String? = null

            when {
                dataFoodname.contains("Nasi_Goreng") -> {
                    filename = dataFoodname.replace("jpeg", "json")
                    foodname = filename.dropLast(5).replace("_", " ")
                }
                dataFoodname.contains("Rendang") -> {
                    filename = dataFoodname.replace("jpeg", "json")
                    foodname = filename.dropLast(5)
                }
                dataFoodname.contains("Oatmeal") -> {
                    filename = dataFoodname.replace("jpeg", "json")
                    foodname = filename.dropLast(5)
                }
                dataFoodname.contains("Batagor") -> {
                    filename = dataFoodname.replace("jpeg", "json")
                    foodname = filename.dropLast(5)
                }
                dataFoodname.contains("Spaghetti") -> {
                    filename = dataFoodname.replace("jpeg", "json")
                    foodname = filename.dropLast(5)
                }
            }

            binding.tvFoodname.text = foodname?.replaceFirstChar { it.uppercase() }
            val nutrition = viewModel.getNutrition(filename?.lowercase() ?: "nasi_goreng.json")

            // Get User Threshold from Age and Gender data
            val userThreshold = mNutrientPreference.getUserThreshold()
            val batasLemak = userThreshold.lemak
            val batasKalori = userThreshold.kalori
            val batasKolestrol = userThreshold.kolestrol
            val batasProtein = userThreshold.protein
            val batasKarbohidrat = userThreshold.karbohidrat
            val batasSodium = userThreshold.sodium

            // Calculation
            val calcLemak = (nutrition[0].value / batasLemak) * 100
            val calcKalori = (nutrition[1].value / batasKalori) * 100
            val calcKolestrol = (nutrition[2].value / batasKolestrol) * 100
            val calcProtein = (nutrition[3].value / batasProtein) * 100
            val calcKarbohidrat = (nutrition[4].value / batasKarbohidrat) * 100
            val calcSodium = (nutrition[5].value / batasSodium) * 100


            userModel = UserModelEntity()
            // Accumulate the value for user
            var accLemak = mNutrientPreference.getAccumulateNutrient().lemak
            accLemak += calcLemak.toFloat()
            userModel.lemak = accLemak

            var accKalori = mNutrientPreference.getAccumulateNutrient().kalori
            accKalori += calcKalori.toFloat()
            userModel.kalori = accKalori

            var accKolestrol = mNutrientPreference.getAccumulateNutrient().kolestrol
            accKolestrol += calcKolestrol.toFloat()
            userModel.kolestrol = accKolestrol

            var accProtein = mNutrientPreference.getAccumulateNutrient().protein
            accProtein += calcProtein.toFloat()
            userModel.protein = accProtein

            var accKarbohidrat = mNutrientPreference.getAccumulateNutrient().karbohidrat
            accKarbohidrat += calcKarbohidrat.toFloat()
            userModel.karbohidrat = accKarbohidrat

            var accSodium = mNutrientPreference.getAccumulateNutrient().sodium
            accSodium += calcSodium.toFloat()
            userModel.sodium = accSodium

            sendWarningData(
                accLemak,
                accKalori,
                accKolestrol,
                accProtein,
                accKarbohidrat,
                accSodium
            )

            // save accumulated value to Constant
            mNutrientPreference.setAccumulateNutrients(userModel)

            // save accumulated value to Accumulated Entity for adapter
            val listAccValue = ArrayList<AccumulatedNutrient>()
            val dataLemak = AccumulatedNutrient("Lemak", accLemak.toDouble())
            val dataKalori = AccumulatedNutrient("Kalori", accKalori.toDouble())
            val dataKolestrol = AccumulatedNutrient("Kolestrol", accKolestrol.toDouble())
            val dataProtein = AccumulatedNutrient("Protein", accProtein.toDouble())
            val dataKarbohidrat = AccumulatedNutrient("Karbohidrat", accKarbohidrat.toDouble())
            val dataSodium = AccumulatedNutrient("Sodium", accSodium.toDouble())

            listAccValue.add(dataLemak)
            listAccValue.add(dataKalori)
            listAccValue.add(dataKolestrol)
            listAccValue.add(dataProtein)
            listAccValue.add(dataKarbohidrat)
            listAccValue.add(dataSodium)

            val nutritionAdapter = NutritionAdapter()
            nutritionAdapter.setNutrition(listAccValue)

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

    private fun sendWarningData(
        accLemak: Float,
        accKalori: Float,
        accKolestrol: Float,
        accProtein: Float,
        accKarbohidrat: Float,
        accSodium: Float
    ) {
        val listWarning = ArrayList<String>()
        if (accLemak > 100 || accKalori > 100 || accKolestrol > 100 || accProtein > 100 || accKarbohidrat > 100 || accSodium > 100) {
            binding.btnWarning.visibility = View.VISIBLE
        }
        if (accLemak > 100) listWarning.add("lemak")
        if (accKalori > 100) listWarning.add("kalori")
        if (accKolestrol > 100) listWarning.add("kolestrol")
        if (accProtein > 100) listWarning.add("protein")
        if (accKarbohidrat > 100) listWarning.add("karbohidrat")
        if (accSodium > 100) listWarning.add("sodium")

        binding.btnWarning.setOnClickListener {
            val toWarningActivity =
                DashboardFragmentDirections.actionDashboardFragmentToWarningActivity(listWarning.toTypedArray())
            findNavController().navigate(toWarningActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}