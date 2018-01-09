package com.ooftf.operation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText

/**
 * Created by 99474 on 2018/1/5 0005.
 */
class OperationEditText : EditText {
    private var drawableShowId = R.drawable.vector_drawable_attention_fill
    private var drawableHideId = R.drawable.vector_drawable_attention_forbid_fill
    private var drawableDelId = R.drawable.vector_icon_del
    private var maskEnabled = false
    private var delEnabled = false
    private var drawableMaskSize = dip2px(context, 20f)
    private var drawableDelSize = dip2px(context, 17f)
    private var drawableMargin = dip2px(context, 8f)

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
        obtainAttrs(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        obtainAttrs(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        obtainAttrs(attrs)
    }

    private fun obtainAttrs(attrs: AttributeSet) {
        val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.OperationEditText)
        drawableShowId = attrsArray.getResourceId(R.styleable.OperationEditText_oet_drawableShow, drawableShowId)
        drawableHideId = attrsArray.getResourceId(R.styleable.OperationEditText_oet_drawableHide, drawableHideId)
        drawableDelId = attrsArray.getResourceId(R.styleable.OperationEditText_oet_drawableDel, drawableDelId)
        maskEnabled = attrsArray.getBoolean(R.styleable.OperationEditText_oet_maskEnabled, maskEnabled)
        delEnabled = attrsArray.getBoolean(R.styleable.OperationEditText_oet_delEnabled, delEnabled)
        drawableMaskSize = attrsArray.getDimension(R.styleable.OperationEditText_oet_maskDrawableSize, drawableMaskSize)
        drawableDelSize = attrsArray.getDimension(R.styleable.OperationEditText_oet_delDrawableSize, drawableDelSize)
        drawableMargin = attrsArray.getDimension(R.styleable.OperationEditText_oet_drawableMargin, drawableMargin)
        attrsArray.recycle()
        maskPassword()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawDel(canvas)
        drawMask(canvas)
    }

    private fun drawDel(canvas: Canvas) {
        if (!delEnabled) return
        if (length() == 0) return
        val drawable = context.resources.getDrawable(drawableDelId)
        drawable.setBounds(getDelDrawRect())
        drawable.draw(canvas)
    }

    private fun drawMask(canvas: Canvas) {
        if (!maskEnabled) return
        if (length() == 0) return
        val drawable = if (isMaskPssword()) {
            context.resources.getDrawable(drawableHideId)
        } else {
            context.resources.getDrawable(drawableShowId)
        }
        drawable.setBounds(getMaskDrawRect())
        drawable.draw(canvas)
    }

    fun getDelDrawRect(): Rect {
        val left = width - paddingRight - drawableDelSize
        val top = height / 2 - drawableDelSize / 2
        val right = width - paddingRight
        val bottom = height / 2 + drawableDelSize / 2
        return Rect(left.toInt(), top.toInt(), right, bottom.toInt())
    }

    fun getMaskDrawRect() =
            if (delEnabled) {
                val left = width - paddingRight - drawableDelSize - drawableMargin - drawableMaskSize
                val top = height / 2 - drawableMaskSize / 2
                val right = width - paddingRight - drawableDelSize - drawableMargin
                val bottom = height / 2 + drawableMaskSize / 2
                Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
            } else {
                val left = width - paddingRight - drawableMaskSize
                val top = height / 2 - drawableMaskSize / 2
                val right = width - paddingRight
                val bottom = height / 2 + drawableMaskSize / 2
                Rect(left.toInt(), top.toInt(), right, bottom.toInt())
            }

    fun getDelTouchRect(): Rect {
        val left = width - paddingRight - drawableDelSize - drawableMargin / 2
        val top = height / 2 - drawableDelSize / 2 - drawableMargin / 2
        val right = width - paddingRight + drawableMargin / 2
        val bottom = height / 2 + drawableDelSize / 2 + drawableMargin / 2
        return Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
    }

    fun getMaskTouchRect() =
            if (delEnabled) {
                val left = width - paddingRight - drawableDelSize - drawableMargin - drawableMaskSize - drawableMargin / 2
                val top = height / 2 - drawableMaskSize / 2 - drawableMargin / 2
                val right = width - paddingRight - drawableDelSize - drawableMargin + drawableMargin / 2
                val bottom = height / 2 + drawableMaskSize / 2 + drawableMargin / 2
                Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
            } else {
                val left = width - paddingRight - drawableMaskSize - drawableMargin / 2
                val top = height / 2 - drawableMaskSize / 2 - drawableMargin / 2
                val right = width - paddingRight + drawableMargin / 2
                val bottom = height / 2 + drawableMaskSize / 2 + drawableMargin / 2
                Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
            }

    fun isMaskPssword() = transformationMethod != null
    private fun maskPassword() {
        if (!maskEnabled) return
        transformationMethod = PasswordTransformationMethod.getInstance()
    }

    private fun unmaskPassword() {
        if (!maskEnabled) return
        transformationMethod = null
    }

    var clickMask = false
    var clickDel = false
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                clickMask = maskEnabled && getMaskTouchRect().contains(event.getX().toInt(), event.getY().toInt())
                clickDel = delEnabled && getDelTouchRect().contains(event.getX().toInt(), event.getY().toInt())
            }
            MotionEvent.ACTION_MOVE -> {
                clickMask = clickMask && getMaskTouchRect().contains(event.getX().toInt(), event.getY().toInt())
                clickDel = clickDel && getDelTouchRect().contains(event.getX().toInt(), event.getY().toInt())
            }
            MotionEvent.ACTION_UP -> {
                if (clickMask && getMaskTouchRect().contains(event.getX().toInt(), event.getY().toInt())) {
                    performMaskClick()
                }
                if (clickDel && getDelTouchRect().contains(event.getX().toInt(), event.getY().toInt())) {
                    performDelClick()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun performDelClick() {
        text.clear()
    }

    private fun performMaskClick() {
        if (isMaskPssword()) {
            unmaskPassword()
        } else {
            maskPassword()
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale
    }
}