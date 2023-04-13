package com.tuan88291.mvvmpattern.data.local.model.user

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR
import androidx.room.Ignore
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SubCategory : Observable {
    @Transient
    private var mCallbacks: PropertyChangeRegistry? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("lastValue")
    @Expose
    var lastValue = ""

    @SerializedName("status")
    @Expose
    var status = 0

    @SerializedName("dataTypeNo")
    @Expose
    var dataTypeNo = ""

    @SerializedName("curator")
    @Expose
    var curator: String? = null

    @Ignore
    var nextNumber: String? = null

    @Ignore
    var math = ""
        set(math) {
            if (math != null) {
                field = math
            }
        }

    @Ignore
    var gender = 0

    @Ignore
    var isChecked = false

    @Ignore
    var codeTube = "" //Id tube từ database

    @Ignore
    var color = ""

    @Ignore
    var formatDisplay = ""
        set(formatDisplay) {
            if (formatDisplay != null) {
                field = formatDisplay
            }
        }

    @Ignore
    var regexValidate = ""
        set(regexValidate) {
            if (regexValidate != null) {
                field = regexValidate
            }
        }

    @Ignore
    var rangeInput = ""
        set(rangeInput) {
            if (rangeInput != null) {
                field = rangeInput
            }
        }

    @Ignore
    var valueDefault: String? = "" //Giá trị tiêu chuẩn
        get() = if (field == null || field == "") {
            ""
        } else field
        set(valueDefault) {
            if (valueDefault != null) {
                field = valueDefault
            }
        }

    @Ignore
    var isLastDifference = false

    @Ignore
    var keyboardType: Int? = null

    @Ignore
    var lastDifferenceMinus //phạm vi so sánh -
            = 0

    @Ignore
    var lastDifferencePlus //phạm vi so sánh +
            = 0

    @Ignore
    var initValue = "" //Giá trị khởi tạo

    @Ignore
    var standardValueType //công thức so sánh giá trị tiêu chuẩn
            : String? = null
        set(standardValueType) {
            if (standardValueType != null) {
                field = standardValueType
            }
        }

    @Ignore
    var lastDifferenceType: String? = null
        set(lastDifferenceType) {
            if (lastDifferenceType != null) {
                field = lastDifferenceType
            }
        }

    @Ignore
    var lastDifferenceValue: String? = null
        set(lastDifferenceValue) {
            if (lastDifferenceValue != null) {
                field = lastDifferenceValue
            }
        }

    @Ignore
    var inputErrMsg: String? = null

    @Ignore
    var inputConventionErrMsg: String? = null

    @Ignore
    var isShowInputConventionErrMsg = false

    @Ignore
    var keyboardTypeId: Int? = null
    private val isChangeValue = false

    @Ignore
    var labelAllValue: String? = null

    @Ignore
    var inputConvention: String? = null

    //For specimen
    @get:Bindable
    val valueString: String
        get() =//For specimen
            ""

    //            SharedPrefs.getInstance().put(Constant.KEYBOARD_S020_IS_ALL_ITEMS, false);
    @get:Bindable
    val secondValueLabel: String?
        get() = if (checkAllValues()) {
//            SharedPrefs.getInstance().put(Constant.KEYBOARD_S020_IS_ALL_ITEMS, false);
            labelAllValue
        } else labelAllValue

    private fun checkAllValues(): Boolean {
        return true
    }

    @get:Bindable
    val secondHealth: Int
        get() = 0

    fun setFirstOldValue(value: String?): Boolean {
        notifyPropertyChanged(BR.user)
        return false
    }

    override fun addOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                mCallbacks = PropertyChangeRegistry()
            }
        }
        mCallbacks!!.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.remove(callback)
    }

    fun notifyChange() {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.notifyCallbacks(this, fieldId, null)
    }
}