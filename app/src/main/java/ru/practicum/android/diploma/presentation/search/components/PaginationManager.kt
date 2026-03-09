package ru.practicum.android.diploma.presentation.search.components

class PaginationManager {
    var currentPage = 0
    var maxPages = 0
    var isLoadingNextPage = false

    fun reset() {
        currentPage = 0
        maxPages = 0
        isLoadingNextPage = false
    }

    fun canLoadNextPage(hasItems: Boolean): Boolean {
        return !isLoadingNextPage && hasItems && currentPage < maxPages - 1
    }
}
