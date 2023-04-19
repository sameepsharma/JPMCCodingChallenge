package com.example.weatherly.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weatherly.ActivityViewModel;
import com.example.weatherly.databinding.FragmentGalleryBinding;
import com.example.weatherly.rest.FetchService;
import com.example.weatherly.rest.RetrofitProvider;
import com.example.weatherly.rest.model.data_class.CityResponse;
import com.example.weatherly.rest.repo.WeatherRepo;
import com.example.weatherly.ui.search.adapter.SearchListAdapter;
import com.sameep.iiflassignment.db.ArticlesDb;

import kotlin.Unit;

public class SearchFragment extends Fragment {

    SearchViewModel searchViewModel;
    private FragmentGalleryBinding binding;

    private SearchListAdapter searchListAdapter;

    private ActivityViewModel activityViewModel;

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!charSequence.toString().isEmpty())
                searchViewModel.getCity(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(getViewModelStore()
                        , new SearchViewModelFactory((new WeatherRepo(RetrofitProvider.INSTANCE.getRetrofit().create(FetchService.class)
                        , ArticlesDb.Companion.getDatabase(requireContext().getApplicationContext()).articleDao()
                        , requireContext()))))
                        .get(SearchViewModel.class);

        activityViewModel = new ViewModelProvider(requireActivity()).get(ActivityViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.etSearch.addTextChangedListener(watcher);
        binding.rvSearchResults.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        searchListAdapter = new SearchListAdapter(new CityResponse(),   (coordinates, cityName)->{
            activityViewModel.setIsSearched(true);
            activityViewModel.updateLatlong(coordinates.getLat(), coordinates.getLon());
            activityViewModel.updateLoc(cityName);
            return Unit.INSTANCE;
        });
        binding.rvSearchResults.setAdapter(searchListAdapter);
        setupObservers();

        return root;
    }

    private void setupObservers() {

        searchViewModel.getCityResponse().observe(getViewLifecycleOwner(), resp -> {
            Log.e("Got Response :: ", "CityResponse = " + resp);
            if (!resp.isEmpty())
                searchListAdapter.onRefresh(resp);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}