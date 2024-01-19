package mobi.dev33.inditest.ui.view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import mobi.dev33.inditest.R
import mobi.dev33.inditest.databinding.CustomIconifiedItemBinding

class IconifiedItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs) {

    private val binding: CustomIconifiedItemBinding

    init {
        binding = CustomIconifiedItemBinding.inflate(LayoutInflater.from(context), this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.IconifiedItemView)
        val src = attributes.getDrawable(R.styleable.IconifiedItemView_iconLeft)
        val title = attributes.getText(R.styleable.IconifiedItemView_title)
        val subTitle = attributes.getText(R.styleable.IconifiedItemView_subtitle)
        val titleTextSize = attributes.getDimensionPixelSize(R.styleable.IconifiedItemView_titleTextSize, -1)
        val subtitleTextSize =
            attributes.getDimensionPixelSize(R.styleable.IconifiedItemView_subtitleTextSize, -1)

        val titleTextColor = attributes.getColor(R.styleable.IconifiedItemView_titleTextColor, -1)
        val subtitleTextColor =
            attributes.getColor(R.styleable.IconifiedItemView_subtitleTextColor, -1)

        binding.apply {
            if (src != null) itemIcon.setImageDrawable(src)
            else itemIcon.visibility = View.GONE

            if (titleTextColor != -1) itemTitle.setTextColor(titleTextColor)
            if (subtitleTextColor != -1) itemSubtitle.setTextColor(subtitleTextColor)

            itemTitle.text = title
            itemSubtitle.text = subTitle
            if (titleTextSize > 0) itemTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize.toFloat())
            if (subtitleTextSize > 0) itemSubtitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,  subtitleTextSize.toFloat())

            attributes.recycle()
        }
    }

    fun setTitleTextSize(titleTextSize: Float) {
        binding.itemTitle.textSize = titleTextSize
    }

    fun setSubtitleTextSize(subtitleTextSize: Float) {
        binding.itemSubtitle.textSize = subtitleTextSize
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        alpha = if (enabled) 1f else 0.4f
    }

    fun setTitle(text: CharSequence) {
        binding.itemTitle.text = text
    }

    fun setTitleTextColor(@ColorInt color: Int) {
        binding.itemTitle.setTextColor(color)
    }

    fun setSubtitleTextColor(@ColorInt color: Int) {
        binding.itemSubtitle.setTextColor(color)
    }

    fun setSubtitle(text: CharSequence?) {
        binding.itemSubtitle.text = text

    }

    fun showIcon(show: Boolean) {
        binding.itemIcon.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun setIcon(@DrawableRes drawable: Int, @ColorInt colorTint: Int? = null) {
        binding.itemIcon.setImageResource(drawable)
        if (colorTint != null && colorTint > -1) binding.itemIcon.setColorFilter(colorTint)
        showIcon(true)
    }


}