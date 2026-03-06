package ru.practicum.android.diploma.core.storage

import android.content.SharedPreferences
import ru.practicum.android.diploma.domain.model.FilterSettings

class FilterStorageImpl(private val sharedPreferences: SharedPreferences) : FilterStorage {
    override fun saveFilter(filter: FilterSettings) {
        sharedPreferences.edit()
            .putInt("salary", filter.salary ?: -1)
            .putBoolean("hide_without_salary", filter.hideWithoutSalary)
            .putString("industry_id", filter.industryId)
            .putString("industry_name", filter.industryName)
            .putString("country_id", filter.countryId)
            .putString("country_name", filter.countryName)
            .putString("region_id", filter.regionId)
            .putString("region_name", filter.regionName)
            .apply()

    }

    override fun getFilter(): FilterSettings? {
        val salary = sharedPreferences.getInt("salary", -1)
        val hideWithoutSalary = sharedPreferences.getBoolean("hide_without_salary", false)

        val industryId = sharedPreferences.getString("industry_id", null)
        val industryName = sharedPreferences.getString("industry_name", null)

        val countryId = sharedPreferences.getString("country_id", null)
        val countryName = sharedPreferences.getString("country_name", null)

        val regionId = sharedPreferences.getString("region_id", null)
        val regionName = sharedPreferences.getString("region_name", null)


        return FilterSettings(
            salary = if (salary == -1) null else salary,
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
}
