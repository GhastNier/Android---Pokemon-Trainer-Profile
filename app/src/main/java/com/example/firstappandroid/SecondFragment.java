package com.example.firstappandroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstappandroid.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    Spinner spin;

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        setSpinner();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onSpinSelect();
        binding.confirmBtn.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });

    }
    private void onSpinSelect(){
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(@NonNull AdapterView<?> adapterView, View view, int pos, long id) {
                if(pos != 0) {
                    MainActivity main = new MainActivity();
                    SharedPreferences.Editor themeSetter = MainActivity.theme.edit();
                    String season = String.valueOf(adapterView.getItemAtPosition(pos));
                    themeSetter.putString(MainActivity.themeKey, season).apply();
                    main.setThemeValues(FirstFragment.mainBg);
                    Toast.makeText(adapterView.getContext(), "The " + season + " theme was selected.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(adapterView.getContext(),"Please select a Theme",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(adapterView.getContext(),"Please select a Theme",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setSpinner() {
        spin = (Spinner) binding.spinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.seasons, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
    }
}