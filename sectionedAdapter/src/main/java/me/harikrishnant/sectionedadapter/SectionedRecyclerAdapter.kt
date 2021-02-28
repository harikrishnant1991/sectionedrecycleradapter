package me.harikrishnant.sectionedadapter

import androidx.recyclerview.widget.RecyclerView

abstract class SectionedRecyclerAdapter<H: RecyclerView.ViewHolder, C: RecyclerView.ViewHolder>: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val sectionMap = HashMap<Int, Int>()
    internal val headerTypes = HashSet<Int>()

    final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (position == 0) {
            if (sectionMap.keys.isNotEmpty()) {
                onBindHeader(holder as H, 0)
                return
            } else {
                throw RuntimeException("Section list empty")
            }
        }
        var secSum = 0
        var prevSecSum = 0
        for (key in sectionMap.keys) {
            secSum += sectionMap[key]!!
            secSum++
            if (position < secSum) {
                val scopePos = position - prevSecSum
                if (scopePos == 0) {
                    print(scopePos)
                }
                if (scopePos == 0) onBindHeader(holder as H, key)
                else onBindChild(holder as C, key, scopePos - 1)
                return
            }
            prevSecSum = secSum
        }
    }

    final override fun getItemCount(): Int {
        val sectionCount = getSectionCount()
        var totalCount = sectionCount
        for (i in 0 until sectionCount) {
            val childCount = getChildCount(i)
            sectionMap[i] = childCount
            totalCount += childCount
        }
        return totalCount
    }

    final override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            if (sectionMap.keys.isNotEmpty()) {
                val headerViewType = getHeaderViewType(0)
                headerTypes.add(headerViewType)
                return headerViewType
            } else {
                throw RuntimeException("Sectionlist empty")
            }
        }
        var secSum = 0
        var prevSecSum = 0
        for (key in sectionMap.keys) {
            secSum += sectionMap[key]!!
            secSum++
            if (position < secSum) {
                val scopePos = position - prevSecSum
                return if (scopePos == 0) {
                    val headerViewType = getHeaderViewType(key)
                    headerTypes.add(headerViewType)
                    headerViewType
                }
                else getChildViewType(key, scopePos - 1)
            }
            prevSecSum = secSum
        }
        throw RuntimeException("Position out of bounds")
    }

    abstract fun getSectionCount(): Int

    abstract fun getChildCount(section: Int): Int

    abstract fun getHeaderViewType(section: Int): Int

    abstract fun getChildViewType(section: Int, index: Int): Int

    abstract fun onBindHeader(holder: H, section: Int)

    abstract fun onBindChild(holder: C, section: Int, index: Int)
}