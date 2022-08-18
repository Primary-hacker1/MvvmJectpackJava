package com.justsafe.just.ui.zjprisonshift

import android.content.DialogInterface
import android.view.MenuItem
import android.view.View
import com.justsafe.just.R
import com.justsafe.just.databinding.ActivityDutyBinding
import com.justsafe.just.ui.dialog.ShiftCustomDialog
import com.justsafe.just.vm.JUSTMeViewModel
import com.justsafe.libview.base.BaseActivity
import javax.inject.Inject

class DutyActivity : BaseActivity<ActivityDutyBinding>() {

    private var TAG: String = "DutyActivity"

    @Inject
    lateinit var justMeViewModel: JUSTMeViewModel

    override fun getLayout(): Int {
        return R.layout.activity_duty
    }

    /*
    * pram mdatabinding 自动注册控件
    * 直接拿ID就好
    * */

    override fun init() {
        mdatabinding?.let {
            it.cancel.setOnClickListener {
                finish()
            }
            it.ok.setOnClickListener {
                val builder: ShiftCustomDialog.Builder =
                    ShiftCustomDialog.Builder(this@DutyActivity)
                builder.setPositiveButton("确定",
                    DialogInterface.OnClickListener { dialog, which -> //...To-do
                        dialog.dismiss()
                        finish()
                    })
                builder.create().show()
            }

            it.checkbox1.setOnCheckedChangeListener{ buttonView, isChecked ->
                when (isChecked) {
                    true -> {
                        it.ll1.visibility = View.GONE
                    }
                    false -> {
                        it.ll1.visibility = View.VISIBLE
                    }
                }
            }

            it.checkbox2.setOnCheckedChangeListener{ buttonView, isChecked ->
                when (isChecked) {
                    true -> {
                        it.ll2.visibility = View.GONE
                    }
                    false -> {
                        it.ll2.visibility = View.VISIBLE
                    }
                }
            }

            it.checkbox3.setOnCheckedChangeListener{ buttonView, isChecked ->
                when (isChecked) {
                    true -> {
                        it.ll3.visibility = View.GONE
                    }
                    false -> {
                        it.ll3.visibility = View.VISIBLE
                    }
                }
            }

            it.checkbox4.setOnCheckedChangeListener{ buttonView, isChecked ->
                when (isChecked) {
                    true -> {
                        it.ll4.visibility = View.GONE
                    }
                    false -> {
                        it.ll4.visibility = View.VISIBLE
                    }
                }
            }

            it.checkbox5.setOnCheckedChangeListener{ buttonView, isChecked ->
                when (isChecked) {
                    true -> {
                        it.ll5.visibility = View.GONE
                    }
                    false -> {
                        it.ll5.visibility = View.VISIBLE
                    }
                }
            }

            it.checkbox6.setOnCheckedChangeListener{ buttonView, isChecked ->
                when (isChecked) {
                    true -> {
                        it.ll6.visibility = View.GONE
                    }
                    false -> {
                        it.ll6.visibility = View.VISIBLE
                    }
                }
            }

            it.checkbox7.setOnCheckedChangeListener{ buttonView, isChecked ->
                when (isChecked) {
                    true -> {
                        it.ll7.visibility = View.GONE
                    }
                    false -> {
                        it.ll7.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish() // back button
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}