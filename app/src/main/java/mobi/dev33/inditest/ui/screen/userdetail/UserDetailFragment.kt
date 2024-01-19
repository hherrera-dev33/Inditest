package mobi.dev33.inditest.ui.screen.userdetail

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import mobi.dev33.inditest.R
import mobi.dev33.inditest.databinding.FragmentUserDetailBinding
import mobi.dev33.inditest.model.AppUserGender
import mobi.dev33.inditest.ui.screen.MainActivity
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private lateinit var pickerIntentResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding: FragmentUserDetailBinding
    private val args: UserDetailFragmentArgs by navArgs()
    private val viewModel: UserDetailFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserDetailBinding.inflate(inflater, container, false)
            .also { binding = it }.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initLayout()
        initPickerActivictyResultCallback()
    }

    private fun initPickerActivictyResultCallback() {
        pickerIntentResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                showUnavailableFeatureSnackbar()
            }
    }

    private fun initToolbar() {
        binding.toolbar.title = args.selectedUser.name.uppercase()
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        binding.toolbar.setNavigationIcon(R.drawable.ic_nav_arrow)
        (activity as MainActivity?)?.setTransparentStatusBar(true)
    }

    private fun initLayout() {
        args.selectedUser.also {
            binding.userProfilePic.clipToOutline = true
            Glide.with(this).load(it.picture?.large)
                .placeholder(R.drawable.user_picture_placeholder)
                .into(binding.userProfilePic)

            binding.btnCamera.setOnClickListener {
                pickerIntentResultLauncher.launch(Intent(Intent.ACTION_PICK).apply { type = "image/*" })
            }
            binding.btnEdit.setOnClickListener { showUnavailableFeatureSnackbar() }

            binding.itemName.setSubtitle(it.name)
            binding.itemEmail.setSubtitle(it.email)
            binding.itemPhone.setSubtitle(it.phone)
            binding.itemRegistry.setSubtitle(
                SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.getDefault()
                ).format(it.registrationDate)
            )
            binding.itemGender.setSubtitle(
                getText(
                    when (it.gender) {
                        AppUserGender.MALE -> R.string.gender_male
                        AppUserGender.FEMALE -> R.string.gender_female
                        AppUserGender.UNKNOWN -> R.string.gender_other
                    }
                )
            )

            (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync { gmap ->
                gmap.uiSettings.apply {
                    setAllGesturesEnabled(false)
                    isMapToolbarEnabled = false
                    isZoomControlsEnabled = false
                    isMyLocationButtonEnabled = false
                }
                val latLng = LatLng(
                    (it.appUserLocation?.latitude?.toDoubleOrNull() ?: 0).toDouble(),
                    (it.appUserLocation?.longitude?.toDoubleOrNull() ?: 0).toDouble()
                )
                gmap.addMarker(
                    MarkerOptions().position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
                )
                gmap.animateCamera(
                    CameraUpdateFactory.newCameraPosition(
                        CameraPosition.fromLatLngZoom(
                            latLng, 13f
                        )
                    )
                )
            }
        }
    }

    private fun showUnavailableFeatureSnackbar() {
        Snackbar.make(binding.mainContent, R.string.feature_unavailable, Snackbar.LENGTH_LONG)
            .show()
    }

}
