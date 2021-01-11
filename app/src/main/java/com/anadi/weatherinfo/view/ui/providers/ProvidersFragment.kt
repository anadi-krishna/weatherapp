package com.anadi.weatherinfo.view.ui.providers

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.anadi.weatherinfo.R
import com.anadi.weatherinfo.data.IconMap
import com.anadi.weatherinfo.data.db.location.LocationWithWeathers
import com.anadi.weatherinfo.data.db.weather.Weather
import com.anadi.weatherinfo.data.network.WeatherProvider
import com.anadi.weatherinfo.databinding.ProvidersFragmentBinding
import com.anadi.weatherinfo.view.ui.BaseFragment
import com.anadi.weatherinfo.utils.Resource
import com.anadi.weatherinfo.utils.Status
import com.anadi.weatherinfo.view.ui.details.DetailsFragmentArgs
import es.dmoral.toasty.Toasty
import timber.log.Timber
import javax.inject.Inject

class ProvidersFragment : BaseFragment(R.layout.providers_fragment), ProvidersAdapter.Listener {

    private val binding: ProvidersFragmentBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ProvidersViewModel

    private lateinit var adapter: ProvidersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.i("onViewCreated")

        viewModel = ViewModelProvider(this, viewModelFactory).get(ProvidersViewModel::class.java)
        viewModel.id = DetailsFragmentArgs.fromBundle(requireArguments()).locationId

        adapter = ProvidersAdapter(this)
        binding.recyclerView.adapter = adapter

        viewModel.details.observe(viewLifecycleOwner, Observer { update(it) })
        viewModel.mergedWeather.observe(viewLifecycleOwner, Observer { updateMerged(it) })

        viewModel.fetch()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.i("onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy")
    }

    private fun loading() {
        binding.progressBar.root.visibility = View.VISIBLE
    }

    private fun error(message: String?) {
        binding.progressBar.root.visibility = View.GONE
        message?.let { Toasty.error(requireContext(), it, Toast.LENGTH_LONG).show() }
    }

    private fun update(resource: Resource<LocationWithWeathers>) {
        when (resource.status) {
            Status.SUCCESS -> update(resource.data)
            Status.LOADING -> loading()
            Status.ERROR -> error(resource.message(requireContext()))
        }
    }

    private fun update(data: LocationWithWeathers?) {
        binding.progressBar.root.visibility = View.GONE
        binding.noData.visibility = View.GONE

        if (data == null || data.weathers.isEmpty()) {
            if (data == null) Timber.d("LocationWithWeathers is null!")

            binding.noData.visibility = View.VISIBLE
            return
        }

        adapter.dataset = data.weathers
        binding.locationName.text = data.location.name
    }

    private fun updateMerged(weather: Weather) {

        with(binding.merged) {
            providerName.text = WeatherProvider.fromCode(weather.providerId).providerName
            lastUpdateTime.text = weather.dataCalcTimestamp.toString() // TODO timestamp convert to date

            icon.setImageResource(IconMap.getIconId("01d"))
            description.text = weather.code.toString() // TODO convert weather code to string

            temp.text = requireContext().getString(R.string.temp_short_celsium, weather.temp)
            windSpeed.text = requireContext().getString(R.string.wind_speed_short_ms, weather.windSpeed)
            windDir.rotation = weather.windDegree.toFloat()

            pressure.text = requireContext().getString(R.string.pressure_short, weather.pressure)
            humidity.text = requireContext().getString(R.string.humidity_short, weather.humidity)
        }
    }

    override fun onSelected(weather: Weather) {
        val action = ProvidersFragmentDirections.actionProvidersToDetails()
        action.locationId = weather.locationId
        action.providerId = weather.providerId
        findNavController().navigate(action)
    }
}