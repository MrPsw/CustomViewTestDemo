package com.lxoms.customviewtestdemo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 * @date      2020/11/18
 * @author    Pengshuwen
 * @describe  自绘鲤鱼
 */
class CarpView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    /**
     * 鱼头大小
     */
    val fishHeadSize = 80F

    /**
     * 起点绘制 坐标
     */
    val startX = 80f
    val startY = 80f


    //鱼尾和身躯关联处
    var fishTailToRightX = 0
    var fishTailToRightY = 0
    var fishTailToLeftX = 0
    var fishTailToLeftY = 0


    val paint = Paint()

    init {
        paint.color = Color.parseColor("#A6F99F89")
        paint.isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {
            drawTop(canvas)
            drawBody(canvas)
            drawFin(canvas)
            drawTail(canvas)
        }


    }


    private fun drawTop(canvas: Canvas) {
        val rectF = RectF(startX, startX, startX + fishHeadSize, startY + fishHeadSize)
        canvas.drawArc(rectF, 0f, 360f, true, paint)
    }

    private fun drawBody(canvas: Canvas) {

        //左侧身躯 和头部关联点
        val bodyTopLeftX = startX
        val bodyTopLeftY = startY + (fishHeadSize / 2)

        val bodyTopRightX = startX + fishHeadSize
        val bodyTopRightY = startY + (fishHeadSize / 2)

        //fishTailToRightX = bodyTopRightX - 20

        val path = Path()
        //从左侧开始绘制移动左侧鱼头中心
        path.moveTo(bodyTopLeftX, bodyTopLeftY)
        //绘制鱼头部位左右关联线条
        path.lineTo(bodyTopRightX, bodyTopRightY)
        //绘制鱼右边身躯线条 二阶贝塞尔
        path.quadTo(
            bodyTopRightX + 20F,
            bodyTopRightY + 60,
            bodyTopRightX - 20f,
            bodyTopRightY + 140
        )
        //绘制鱼尾部分左右关联的线
        path.lineTo(bodyTopLeftX + 20f, bodyTopLeftY + 140)
        //绘制鱼左边身躯线条 二阶贝塞尔
        path.quadTo(
            bodyTopLeftX - 20f,
            bodyTopLeftY + 60,
            bodyTopLeftX,
            bodyTopLeftY
        )
        path.close()
        canvas.drawPath(path, paint)
    }

    private fun drawFin(canvas: Canvas) {
        val path = Path()
        path.moveTo(80f, 120f)
        path.quadTo(40f, 150f, 80f, 150f)
        path.close()
        canvas.drawPath(path, paint)

        val path2 = Path()
        path2.moveTo(160f, 120f)
        path2.quadTo(200f, 150f, 160f, 150f)
        path2.close()
        canvas.drawPath(path2, paint)
    }

    private fun drawTail(canvas: Canvas) {
        val path = Path()

        path.moveTo(140f, 260f)
        //鱼尾right
        path.quadTo(120f, 280f, 150f, 290f)
        //鱼尾弯曲部分
        path.quadTo(120f, 285f, 90f, 290f)
        //鱼尾left
        path.quadTo(120f, 280f, 100f, 260f)
        //path.close()
        canvas.drawPath(path, paint)
    }


}