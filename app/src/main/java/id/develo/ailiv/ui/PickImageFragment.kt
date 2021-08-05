package id.develo.ailiv.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
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

    private lateinit var mNutrientPreference: NutrientPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPickImageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNutrientPreference = NutrientPreference(requireContext())

        Glide.with(requireActivity())
            .load(R.drawable.no_data)
            .into(binding.ivNoData)

        // Get Daily Nutrients from Preference
        val user = mNutrientPreference.getUserData()

        val welcomeText =
            getString(R.string.welcome_text, user.username, user.gender, user.age.toString())

        binding.tvWelcome.text = welcomeText

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
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