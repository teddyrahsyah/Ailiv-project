package id.develo.ailiv.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import id.develo.ailiv.R
import id.develo.ailiv.data.source.local.NutrientPreference
import id.develo.ailiv.databinding.FragmentPickImageBinding
import java.io.File

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PickImageFragment : Fragment() {

    private var _binding: FragmentPickImageBinding? = null

//    private var listDailyNutrient = arrayListOf<Float>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPickImageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireActivity())
            .load(R.drawable.no_data)
            .into(binding.ivNoData)

        // Get Daily Nutrients from Preference
//        getDailyNutrient()

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Uri? = result.data?.data
                val fileName = File(data?.path).name
                intentToDashboardResult(fileName)
            }
        }

        binding.fabPickImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            resultLauncher.launch(intent)

        }
    }

//    private fun getDailyNutrient() {
//        val mNutrientPreference = NutrientPreference(requireContext())
//        val dailyNutrient = mNutrientPreference.getDailyNutrient()
//
//        listDailyNutrient.add(dailyNutrient.lemak)
//        listDailyNutrient.add(dailyNutrient.kalori)
//        listDailyNutrient.add(dailyNutrient.kolestrol)
//        listDailyNutrient.add(dailyNutrient.protein)
//        listDailyNutrient.add(dailyNutrient.karbohidrat)
//        listDailyNutrient.add(dailyNutrient.sodium)
//
//        Log.d("TEST", listDailyNutrient.toString())
//    }

    private fun intentToDashboardResult(filename: String) {
        val toDashboardFragment =
            PickImageFragmentDirections.actionPickImageFragmentToDashboardFragment()
        toDashboardFragment.foodname = filename
        findNavController().navigate(toDashboardFragment)
        Log.d("TEST", filename)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}