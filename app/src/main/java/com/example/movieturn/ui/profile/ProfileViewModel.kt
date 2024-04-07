package com.example.movieturn.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieturn.model.account.AccDetailsRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepo: dagger.Lazy<ProfileRepo>
) : ViewModel() {

    private var _profileLiveData = MutableLiveData<AccDetailsRes?>()
    var profileLiveData: LiveData<AccDetailsRes?> = _profileLiveData
    fun fetchAccountDetails() {
        viewModelScope.launch {
            val res = profileRepo.get().getAccountDetails()
            _profileLiveData.postValue(res)
        }
    }
}