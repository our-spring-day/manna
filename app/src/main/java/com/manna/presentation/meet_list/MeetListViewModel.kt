package com.manna.presentation.meet_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manna.common.Logger
import com.manna.common.plusAssign
import com.manna.data.source.repo.MeetRepository
import com.manna.network.model.meet.MeetResponseItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MeetListViewModel @ViewModelInject constructor(private val repository: MeetRepository) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _meetList = MutableLiveData<List<MeetResponseItem>>()
    val meetList: LiveData<List<MeetResponseItem>> get() = _meetList


    fun getMeetList(deviceId: String) {

        compositeDisposable += repository.getMeetList(deviceId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _meetList.value = it
            }, {
                Logger.d("$it")
            })
    }
}
