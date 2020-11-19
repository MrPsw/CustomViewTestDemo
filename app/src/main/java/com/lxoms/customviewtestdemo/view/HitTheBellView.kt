package com.lxoms.customviewtestdemo.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.lxoms.customviewtestdemo.ext.drawTextWithCenterPoint


/**
 * @date      2020/11/17
 * @author    Pengshuwen
 * @describe  不知道叫啥，只是掘金看的Flutter别人发的动画文章，用Android方式实现
 */
class HitTheBellView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var paint_line: Paint? = null
    var paint_round: Paint? = null
    var paint_text: Paint? = null

    /**
     * 线高
     */
    val line_height = 360f

    val circle_size = 180f

    val first = 240f

    val curcle_wdith = 6f

    var cavans_angle = 0f

    var left_margin = 0

    var right_margin = 0


    var isStartAnim = false


    init {
        paint_line = Paint().apply {
            color = Color.parseColor("#ffffff")
            strokeWidth = 4f
            isAntiAlias = true
        }
        paint_round = Paint().apply {
            color = Color.parseColor("#ffffff")
            style = Paint.Style.STROKE
            strokeWidth = curcle_wdith
            isAntiAlias = true
        }
        paint_text = Paint().apply {
            color = Color.parseColor("#ffffff")
            textSize = 34f
            isFakeBoldText = true
            isAntiAlias = true
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        setBackgroundColor(Color.parseColor("#000000"))

        val strs = arrayListOf("A", "B", "C", "D")

        for (index in 0 until 4) {

            if (index == 1) {
                paint_line?.color = Color.parseColor("#FEEF00")
                paint_round?.color = Color.parseColor("#FEEF00")
                paint_round?.style = Paint.Style.FILL
                paint_text?.color = Color.parseColor("#000000")

            } else {
                paint_line?.color = Color.parseColor("#ffffff")
                paint_round?.color = Color.parseColor("#ffffff")
                paint_round?.style = Paint.Style.STROKE
                paint_text?.color = Color.parseColor("#ffffff")
            }

            var x = first + ((index + 1) * circle_size) + (curcle_wdith * index)


            canvas?.save()
            if (index == 0) {
                canvas?.rotate(left_margin.toFloat(), x, 0f)
            } else if (index == 3) {
                canvas?.rotate(right_margin.toFloat(), x, 0f)
            }
            canvas?.drawLine(x, 0f, x, line_height, paint_line!!)
            val left = x - (circle_size / 2)
            val right = x + (circle_size / 2)
            val top = line_height
            val bottom = line_height + circle_size
            val rect = RectF(left, top, right, bottom)
            canvas?.drawArc(rect, 0f, 360f, true, paint_round!!)
            canvas?.drawTextWithCenterPoint(
                x.toInt(),
                (line_height + (circle_size / 2)).toInt(),
                strs[index],
                paint_text!!
            )
            canvas?.restore()
        }

        if (!isStartAnim) {
            startSwing()
            isStartAnim = true
        }

    }

    /**
     * 35
     */
    private fun startSwing() {
        val valueAnimator = ValueAnimator.ofInt(70, 0)
        valueAnimator.duration = 1000
        valueAnimator.repeatCount = 100
        valueAnimator.repeatMode = ValueAnimator.REVERSE
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Int
            if (value > 35) {
                right_margin = ((70 / 2) - value)
                left_margin = 0
            } else {
                right_margin = 0
                left_margin = ((70 / 2) - value)

            }
            println("value:" + value + "  left_margin:" + left_margin + "  right_margin:" + right_margin)
            postInvalidate()
        }
        valueAnimator.start()


    }


}

