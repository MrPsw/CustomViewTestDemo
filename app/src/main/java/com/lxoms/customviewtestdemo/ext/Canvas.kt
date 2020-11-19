package com.lxoms.customviewtestdemo.ext

import android.graphics.Canvas
import android.graphics.Paint


/**
 * @date      2020/11/18
 * @author    Pengshuwen
 * @describe
 */


/**
 * 以中心点绘制文字
 *
 * @param canvas
 * @param centerX
 * @param centerY
 * @param text
 * @param paint
 */
fun Canvas.drawTextWithCenterPoint(centerX: Int, centerY: Int, text: String, paint: Paint) {
    //获取文本的宽度，但是是一个比较粗略的结果
    val textWidth = paint.measureText(text)
    //文字度量
    val fontMetrics = paint.fontMetrics
    //得到基线的位置
    val baselineY = centerY + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
    //绘制
    this.drawText(text, centerX - textWidth / 2, baselineY, paint)
    println("X坐标：${centerX - textWidth / 2}Y坐标：${baselineY}")
}