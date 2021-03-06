package edu.illinois.cs465.parkingpterodactyl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Set the title in the action bar
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Parking Pterodactyl");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Add button handler so that pressing the search button stores the search term,
        // hides the keyboard, and navigates to the next screen (map)
        ImageButton searchButton = (ImageButton) getActivity().findViewById(R.id.main_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hide the keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                // Save search term
                ((MainActivity)getActivity()).lastSearch = ((EditText)getActivity().findViewById(R.id.search_text)).getText().toString();

                // Navigate to map page
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container,  new GMapFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Add a button handler to the done button on the overlay
        Button homeOverlayDoneButton = (Button) getActivity().findViewById(R.id.homeOverlayDone);
        homeOverlayDoneButton.setVisibility(View.VISIBLE);
        homeOverlayDoneButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                RelativeLayout homeOverlay = (RelativeLayout) getActivity().findViewById(R.id.top_layout);

                homeOverlay.setVisibility(View.INVISIBLE);
                ((MainActivity)getActivity()).homeOverlaySeen = true;
                return false;
            }

        });

        // Hide the overlay if it has already been seen
        if (((MainActivity)getActivity()).homeOverlaySeen) {
            RelativeLayout homeOverlay = (RelativeLayout) getActivity().findViewById(R.id.top_layout);
            homeOverlay.setVisibility(View.INVISIBLE);
        }


    }


}
