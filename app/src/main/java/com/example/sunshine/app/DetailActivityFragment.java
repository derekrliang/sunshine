package com.example.sunshine.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.*;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private View rootView;
    private ShareActionProvider mShareActionProvider;
    private String forecastText = "";

    private static final String TAG = DetailActivityFragment.class.getSimpleName();
    private static final String FORECAST_SHARE_HASHTAG = "#SunshineApp";

    public DetailActivityFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        Log.d(TAG, "onCreateOptionsMenu");

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        //mShareActionProvider = (ShareActionProvider) item.getActionProvider();

        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

    }

    private void shareWeather() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, forecastText + FORECAST_SHARE_HASHTAG);
        shareIntent.setType("text/plain");

        Log.d(TAG, "Sending share?");
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        } else {
            Log.d(TAG, "Share action provider is null!?");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Log.d(TAG, "onOptionsItemSelected");

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_item_share) {
            shareWeather();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_detail, container, false);


        Intent intent = getActivity().getIntent();

        if (intent != null) {
            forecastText = intent.getDataString();
        }

        TextView textView = (TextView) rootView.findViewById(R.id.detail_text);
        textView.setText(forecastText);

        return rootView;
    }
}
