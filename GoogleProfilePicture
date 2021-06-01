class CustomImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {
 private var rnd = Random()
 private val shape = GradientDrawable()
 private var letter = "A"
 var noPic = false

 companion object {
 private val paint = Paint()
 init {
 paint.textAlign = Paint.Align.CENTER
 paint.color = Color.WHITE
 paint.textSize = 66F
 paint.isAntiAlias = true
 paint.isDither = true
 }
 }

 init {
 letter = DatabaseHelper.firstName?.first().toString()
 shape.shape = GradientDrawable.OVAL
 shape.setColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
 }

 override fun onDraw(canvas: Canvas?) {
 if (!noPic) {
 super.onDraw(canvas)
 return
 }
 background = shape
 val x = (canvas!!.width / 2)
 val y = ((canvas!!.height / 2) - ((paint.descent() + paint.ascent()) / 2))
 canvas!!.drawText(letter, x.toFloat(), y, paint)
 }

 fun clear() {
 paint.color = Color.TRANSPARENT
 shape.setColor(Color.TRANSPARENT)
 invalidate()
 }

 fun updateText(firstLetter: String) {
 letter = firstLetter
 noPic = true
 invalidate()
 }

}
