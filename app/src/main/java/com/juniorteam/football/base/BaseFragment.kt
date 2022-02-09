package com.juniorteam.football.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.juniorteam.football.util.view.hideKeyboard
import com.juniorteam.football.util.view.showAlertDialog
import com.juniorteam.football.util.view.toast


abstract class BaseFragment(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    val requestPermissionLauncher =
        this.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.forEach { permissionResult ->
                if (!permissionResult.value) {
                    Toast.makeText(activity, "${permissionResult.key} DENIED", Toast.LENGTH_LONG).show()
                }
            }
        }

    open fun init() {}


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    fun navigate(actionId: Int) {
        view?.let { currentView ->
            Navigation.findNavController(currentView).navigate(actionId)
        }
    }

    fun isAllPermissionsGranted(permissions: Array<String>): Boolean {
        return permissions.all {
            ContextCompat.checkSelfPermission(
                requireContext(),
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun toast(message: Int) {
        requireContext().toast(message)
    }

    fun showAlertDialog(title: String, message: String){
        requireContext().showAlertDialog(title, message)
    }

    fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    fun backPressed () {
        var backPressedTime: Long = 0
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val backToast: Toast =
                Toast.makeText(activity, "Press back again to exit", Toast.LENGTH_SHORT)
            if (backPressedTime + 1000 > System.currentTimeMillis()) {
                backToast.cancel()
                activity?.finish()
            } else {
                backToast.show()
            }
            backPressedTime = System.currentTimeMillis()
        }
    }

    fun hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
}
