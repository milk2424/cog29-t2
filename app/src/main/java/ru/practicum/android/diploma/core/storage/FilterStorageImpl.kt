package ru.practicum.android.diploma.core.storage

import android.content.SharedPreferences
import ru.practicum.android.diploma.domain.model.FilterSettings

class FilterStorageImpl(private val sharedPreferences: SharedPreferences) : FilterStorage {
    override fun saveFilter(filter: FilterSettings) {
        sharedPreferences.edit()
            .putInt("salary", filter.salary ?: -1)
            .putBoolean("hide_without_salary", filter.hideWithoutSalary)
            .putInt("industry_id", filter.industryId ?: -1)
            .putString("industry_name", filter.industryName)
            .putInt("country_id", filter.countryId ?: -1)
            .putString("country_name", filter.countryName)
            .putInt("region_id", filter.regionId ?: -1)
            .putString("region_name", filter.regionName)
            .apply()

    }

    override fun getFilter(): FilterSettings {
        val salary = sharedPreferences.getInt("salary", -1)
        val hideWithoutSalary = sharedPreferences.getBoolean("hide_without_salary", false)

        val industryId = sharedPreferences.getInt("industry_id", -1)
        val industryName = sharedPreferences.getString("industry_name", null)

        val countryId = sharedPreferences.getInt("country_id", -1)
        val countryName = sharedPreferences.getString("country_name", null)

        val regionId = sharedPreferences.getInt("region_id", -1)
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
