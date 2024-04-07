package com.example.movieturn.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movieturn.R
import com.example.movieturn.databinding.FragmentProfileBinding
import com.example.movieturn.model.account.AccDetailsRes
import com.example.movieturn.ui.viewBinding
import com.example.movieturn.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val profileViewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.fetchAccountDetails()
        profileViewModel.profileLiveData.observe(viewLifecycleOwner) {
            it?.run { initViews(it) } ?: toast("Unable to fetch info")
        }
    }

    private fun initViews(accDetailsRes: AccDetailsRes) = with(binding) {
        bar.visibility = View.GONE
        nameTv.text = accDetailsRes.name
        uNameTv.text = accDetailsRes.username
    }
}