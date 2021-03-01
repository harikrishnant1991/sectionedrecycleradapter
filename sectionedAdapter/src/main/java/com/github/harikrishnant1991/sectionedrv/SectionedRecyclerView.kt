package com.github.harikrishnant1991.sectionedrv

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SectionedRecyclerView : RecyclerView {

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {}

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context): this(context, null, 0) {}

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        validateAndSpan()
    }

    override fun setLayoutManager(layout: LayoutManager?) {
        super.setLayoutManager(layout)
        validateAndSpan()
    }

    private fun validateAndSpan() {
        val sectionedAdapter = adapter as? SectionedRecyclerAdapter<*, *> ?: return
        val gridLayoutManager = layoutManager as? GridLayoutManager ?: return
        setGridSpan(sectionedAdapter, gridLayoutManager)
    }

    private fun setGridSpan(sectionedAdapter: SectionedRecyclerAdapter<*, *>,
                            gridLayoutManager: GridLayoutManager) {
        val spanCount = gridLayoutManager.spanCount
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                adapter?.let {
                    if (sectionedAdapter.headerTypes.contains(sectionedAdapter.getItemViewType(position))) {
                        return spanCount
                    }
                    return 1
                } ?: kotlin.run {
                    return 1
                }
            }
        }
    }
}