package ru.practicum.android.diploma.core.storage

import android.content.SharedPreferences
import ru.practicum.android.diploma.domain.model.FilterSettings

class FilterStorageImpl(private val sharedPreferences: SharedPreferences) : FilterStorage {
    override fun saveFilter(filter: FilterSettings) {
        sharedPreferences.edit()
            .putInt(KEY_SALARY, filter.salary ?: DEFAULT_INT)
            .putBoolean(KEY_HIDE_WITHOUT_SALARY, filter.hideWithoutSalary)
            .putInt(KEY_INDUSTRY_ID, filter.industryId ?: DEFAULT_INT)
            .putString(KEY_INDUSTRY_NAME, filter.industryName)
            .putInt(KEY_COUNTRY_ID, filter.countryId ?: DEFAULT_INT)
            .putString(KEY_COUNTRY_NAME, filter.countryName)
            .putInt(KEY_REGION_ID, filter.regionId ?: DEFAULT_INT)
            .putString(KEY_REGION_NAME, filter.regionName)
            .apply()
    }

    override fun getFilter(): FilterSettings {
        val salary = sharedPreferences.getInt(KEY_SALARY, DEFAULT_INT)
        val hideWithoutSalary = sharedPreferences.getBoolean(KEY_HIDE_WITHOUT_SALARY, false)
        val industryId = sharedPreferences.getInt(KEY_INDUSTRY_ID, DEFAULT_INT)
        val industryName = sharedPreferences.getString(KEY_INDUSTRY_NAME, null)
        val countryId = sharedPreferences.getInt(KEY_COUNTRY_ID, DEFAULT_INT)
        val countryName = sharedPreferences.getString(KEY_COUNTRY_NAME, null)
        val regionId = sharedPreferences.getInt(KEY_REGION_ID, DEFAULT_INT)
        val regionName = sharedPreferences.getString(KEY_REGION_NAME, null)

        return FilterSettings(
            salary = if (salary == DEFAULT_INT) null else salary,
            hideWithoutSalary = hideWithoutSalary,
            industryId = industryId,
            industryName = industryName,
            countryId = countryId,
            countryName = countryName,
            regionId = regionId,
            regionName = regionName
        )
    }

    override fun clearFilter() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val KEY_SALARY = "salary"
        private const val KEY_INDUSTRY_ID = "industry_id"
        private const val KEY_HIDE_WITHOUT_SALARY = "hide_without_salary"
        private const val KEY_INDUSTRY_NAME = "industry_name"
        private const val KEY_COUNTRY_ID = "country_id"
        private const val KEY_COUNTRY_NAME = "country_name"
        private const val KEY_REGION_ID = "region_id"
        private const val KEY_REGION_NAME = "region_name"
        private const val DEFAULT_INT = -1
    }
}
