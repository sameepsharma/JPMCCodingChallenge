package com.example.weatherly.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.weatherly.ActivityViewModel;
import com.example.weatherly.R;
import com.example.weatherly.databinding.FragmentHomeBinding;
import com.example.weatherly.rest.FetchService;
import com.example.weatherly.rest.RetrofitProvider;
import com.example.weatherly.rest.repo.WeatherRepo;
import com.sameep.iiflassignment.db.ArticlesDb;

import kotlin.jvm.JvmStatic;

public class HomeFragment extends Fragment {

    private ActivityViewModel activityViewModel;

    private HomeViewModel homeViewModel;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        WeatherRepo weatherRepo = new WeatherRepo(
                RetrofitProvider.INSTANCE.getRetrofit().create(FetchService.class)
                , ArticlesDb.Companion.getDatabase(getContext().getApplicationContext()).articleDao()
                , requireContext());

        homeViewModel =
                new ViewModelProvider(getViewModelStore(), new HomeViewModelFactory(weatherRepo)).get(HomeViewModel.class);

        activityViewModel = new ViewModelProvider(getActivity()).get(ActivityViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.setHomeViewModel(homeViewModel);
        binding.setActivityViewModel(activityViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        //binding.setVariable(BR.homeViewModel, homeViewModel);

        activityViewModel.observeCoordinates().observe(getViewLifecycleOwner(), coordinates -> {
            homeViewModel.fetchWeather(coordinates);
        });

        homeViewModel.getWeatherData().observe(getViewLifecycleOwner(), weatherResponse -> {
           /* binding.setVariable(BR.weatherData, weatherResponse);
            binding.setWeatherData(weatherResponse);*/
            activityViewModel.updateLatlong(weatherResponse.getCoord().getLat(), weatherResponse.getCoord().getLon());
            activityViewModel.updateLoc(weatherResponse.getName()+", "+ weatherResponse.getSys().getCountry());
            binding.invalidateAll();
            Log.e("WeatherDataReceived::", "ResponseData = " + weatherResponse);
        });

        activityViewModel.getLocGeoCode().observe(getViewLifecycleOwner(), cityData -> {
            binding.tvCityCountry.setText(cityData.get(0).getName() + ", " + cityData.get(0).getCountry());
        });

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @JvmStatic
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if (!imageUrl.isEmpty())
            Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_weather)
                .into(view);
    }

}