package com.ooftf.operation

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout


/**
 *  删除按钮的大小为40dp
 *  隐藏转换按钮的大小为38dp
 * Created by master on 2017/10/17 0017.
 */
class OperationEditTextLayout : RelativeLayout {
    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
        obtainAttrs(attrs)
    }


    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        obtainAttrs(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        obtainAttrs(attrs)
    }

    private var drawableShowId = R.drawable.vector_drawable_attention_fill
    private var drawableHideId = R.drawable.vector_drawable_attention_forbid_fill
    private var drawableDelId = R.drawable.vector_icon_del
    private var maskOperationEnabled = false
    private var delOperationEnabled = false
    private var editTextId = -1;
    private var oprationPaddingRight = 0f;
    private lateinit var editText: EditText
    private fun obtainAttrs(attrs: AttributeSet) {
        val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.OperationEditTextLayout)
        drawableShowId = attrsArray.getResourceId(R.styleable.OperationEditTextLayout_oetl_drawableShow, drawableShowId)
        drawableHideId = attrsArray.getResourceId(R.styleable.OperationEditTextLayout_oetl_drawableHide, drawableHideId)
        drawableDelId = attrsArray.getResourceId(R.styleable.OperationEditTextLayout_oetl_drawableDel, drawableDelId)
        maskOperationEnabled = attrsArray.getBoolean(R.styleable.OperationEditTextLayout_oetl_maskEnabled, maskOperationEnabled)
        delOperationEnabled = attrsArray.getBoolean(R.styleable.OperationEditTextLayout_oetl_delEnabled, delOperationEnabled)
        editTextId = attrsArray.getResourceId(R.styleable.OperationEditTextLayout_oetl_editTextId, editTextId)
        oprationPaddingRight = attrsArray.getDimension(R.styleable.OperationEditTextLayout_oetl_paddingRight, oprationPaddingRight)
        attrsArray.recycle()

    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (editTextId != -1) {
            editText = findViewById(editTextId)
        } else if (getChildAt(0) is EditText) {
            editText = getChildAt(0) as EditText
        } else {
            throw IllegalAccessException("OperationEditTextLayout 未找到EditText节点")
        }
        initViews()
        addListener()
    }

    private fun addListener() {
        delView.setOnClickListener {
            editText.text.clear()
        }
        maskView.setOnClickListener {
            toggleMaskState()
        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.isNotEmpty()) {
                    showMaskOperation()
                    showDelOperation()
                } else {
                    hideMaskOperation()
                    hideDelOperation()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    private fun toggleMaskState() {
        if (isMaskPassword()) {
            unmaskPassword()
        } else {
            maskPassword()
        }
    }

    fun showMaskOperation() {
        if (maskOperationEnabled) {
            maskView.visibility = View.VISIBLE
        } else {
            maskView.visibility = View.GONE
        }
    }


    fun hideMaskOperation() {
        maskView.visibility = View.GONE
    }

    fun showDelOperation() {
        if (delOperationEnabled) {
            delView.visibility = View.VISIBLE
        } else {
            delView.visibility = View.GONE
        }
    }

    fun hideDelOperation() {
        delView.visibility = View.GONE
    }


    lateinit var delView: ImageView
    lateinit var maskView: ImageView
    private fun initViews() {
        LayoutInflater.from(context).inflate(R.layout.layout_edit_operation, this)
        var container = findViewById<ViewGroup>(R.id.container)
        container.setPadding(container.left, container.top, oprationPaddingRight.toInt(), container.bottom);
        delView = findViewById(R.id.del)
        maskView = findViewById(R.id.mask)
        delView.setImageResource(drawableDelId)
        maskView.setImageResource(drawableHideId)
        visibleControl()
        maskPassword()
    }

    fun isMaskPassword() = editText.transformationMethod != null
    private fun visibleControl() {
        if (editText.text.isNotEmpty()) {
            showMaskOperation()
            showDelOperation()
        } else {
            hideMaskOperation()
            hideDelOperation()
        }
    }

    private fun maskPassword() {
        if (!maskOperationEnabled) return
        maskView.setImageResource(drawableHideId)
        var selection = editText.selectionStart
        editText.transformationMethod = PasswordTransformationMethod.getInstance()
        editText.setSelection(selection)
    }

    private fun unmaskPassword() {
        if (!maskOperationEnabled) return
        maskView.setImageResource(drawableShowId)
        var selection = editText.selectionStart
        editText.transformationMethod = null
        editText.setSelection(selection)
    }
}