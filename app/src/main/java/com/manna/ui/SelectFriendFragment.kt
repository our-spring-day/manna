package com.manna.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.manna.R
import com.manna.base.BaseFragment
import com.manna.databinding.FragmentSelectFriendBinding
import com.manna.model.Friend

class SelectFriendFragment :
    BaseFragment<FragmentSelectFriendBinding>(R.layout.fragment_select_friend) {
    private val friendList = arrayListOf(
        Friend("yeon_berry22", R.drawable.test_1),
        Friend("heimish_08", R.drawable.test_2),
        Friend("이연재", R.drawable.test_1),
        Friend("최우식", R.drawable.test_1),
        Friend("유승호", R.drawable.test_1),
        Friend("박서준", R.drawable.test_1),
        Friend("임보라", R.drawable.test_1),
        Friend("이지은", R.drawable.test_1),
        Friend("이지은", R.drawable.test_2)
    )
    private val selectFriendAdapter = SelectFriendAdapter(friendList)
    private val selectedFriendAdapter = SelectedFriendAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()

        selectFriendAdapter.setOnClickListener(object : SelectFriendAdapter.OnClickListener {
            override fun onClick(friend: Friend, isChecked: Boolean) {
                if (isChecked) {
                    binding.rvSelectList.visibility = View.VISIBLE
                    selectedFriendAdapter.addData(friend)
                } else {
                    selectedFriendAdapter.removeData(friend)
                }
            }
        })

        binding.btnClear.setOnClickListener {
            binding.edtId.text.clear()
        }

        binding.edtId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.edtId.text.isNotEmpty()) {
                    binding.btnClear.visibility = View.VISIBLE
                } else {
                    binding.btnClear.visibility = View.GONE
                }
                selectFriendAdapter.updateList(binding.edtId.text.toString())
            }

        })
    }

    private fun setAdapter() {
        binding.rvFriendList.layoutManager = LinearLayoutManager(context)
        binding.rvFriendList.adapter = selectFriendAdapter
        binding.rvSelectList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSelectList.adapter = selectedFriendAdapter
    }

    companion object {
        fun newInstance() = SelectFriendFragment()
    }
}
