package ir.mjahanbazi.edittextdrawonbackground

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged

open class CustomEditText : AppCompatEditText {
    companion object {

        private var pxStrokeWidth: Float = 6f
        private var pxRadius: Float = 15f
        private var pxInsideRadius: Float = 12f

        private var outsideRect: RectF = RectF()
        private var insideRect: RectF = RectF()

        private val paintOutside: Paint = object : Paint() {
            init {
                isAntiAlias = true
                style = Style.FILL
                color = Color.BLUE
            }
        }
        private val paintInside: Paint = object : Paint() {
            init {
                isAntiAlias = true
                style = Style.FILL
                color = Color.YELLOW
            }
        }
    }

    init {
        background = null
        setWillNotDraw(false)
        doAfterTextChanged {
            prepareDraw()
        }
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        prepareDraw()
    }

    private fun prepareDraw() {
        outsideRect = RectF(
            0.0f,
            0.0f,
            width.toFloat(),
            height.toFloat()
        )
        insideRect = RectF(
            pxStrokeWidth,
            pxStrokeWidth,
            (width - pxStrokeWidth),
            (height - pxStrokeWidth)
        )
        val drawable = backGroundDrawable()
        background = drawable
        invalidate()
    }


    inner class backGroundDrawable : Drawable() {
        override fun draw(canvas: Canvas) {
            canvas.drawRoundRect(
                outsideRect,
                pxRadius,
                pxRadius,
                paintOutside
            )
            canvas.drawRoundRect(
                insideRect,
                pxInsideRadius,
                pxInsideRadius,
                paintInside
            )
        }

        override fun setAlpha(alpha: Int) {
        }

        override fun setColorFilter(colorFilter: ColorFilter?) {
        }

        override fun getOpacity(): Int {
            return PixelFormat.TRANSPARENT
        }
    }

}
