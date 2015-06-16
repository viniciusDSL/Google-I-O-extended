/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vinidsl.googleioextended.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vinidsl.googleioextended.R;
import com.vinidsl.googleioextended.helper.PreferencesHelper;

/**
 * Created by Vinicius on 11-06-15.
 */
public class MapFragment extends Fragment {

    private GoogleMap googlemap;
    private TextView piso1;
    private TextView piso2;
    private GroundOverlay overlay;
    private int type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        piso1 = (TextView) rootView.findViewById(R.id.textViewFloorOne);
        piso2 = (TextView) rootView.findViewById(R.id.textViewFloorTwo);




        MapsInitializer.initialize(getActivity());

        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) )
        {
            case ConnectionResult.SUCCESS:

                android.support.v4.app.FragmentManager myFM = getChildFragmentManager();
                final SupportMapFragment myMAPF = (SupportMapFragment) myFM.findFragmentById(R.id.map);
                googlemap = myMAPF.getMap();
                googlemap.setMyLocationEnabled(true);
                final View mapView = myFM
                        .findFragmentById(R.id.map).getView();
                if (mapView.getViewTreeObserver().isAlive()) {
                    mapView.getViewTreeObserver().addOnGlobalLayoutListener(
                            new ViewTreeObserver.OnGlobalLayoutListener() {

                                @Override
                                public void onGlobalLayout() {
                                    // TODO now can work with the map
                                    mapView.getViewTreeObserver()
                                            .removeGlobalOnLayoutListener(this);
                                    googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                            new LatLng(-16.500484, -68.134246), 25));
                                    piso1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            piso1.setBackgroundColor(getResources().getColor(R.color.accent));
                                            piso2.setBackgroundColor(getResources().getColor(R.color.white));
                                            showFloorOne();
                                        }
                                    });

                                    piso2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            piso2.setBackgroundColor(getResources().getColor(R.color.accent));
                                            piso1.setBackgroundColor(getResources().getColor(R.color.white));
                                            showFloorTwo();
                                        }
                                    });
                                    type = PreferencesHelper.getInt(PreferencesHelper.MAP,getActivity());
                                    PreferencesHelper.putInt(PreferencesHelper.MAP,0,getActivity());
                                    switch (type){
                                        case 1:
                                            piso1.setBackgroundColor(getResources().getColor(R.color.accent));
                                            piso2.setBackgroundColor(getResources().getColor(R.color.white));
                                            showFloorOne();
                                            break;
                                        case 2:
                                            piso2.setBackgroundColor(getResources().getColor(R.color.accent));
                                            piso1.setBackgroundColor(getResources().getColor(R.color.white));
                                            showFloorTwo();
                                            break;
                                        case 3:
                                            piso2.setBackgroundColor(getResources().getColor(R.color.accent));
                                            piso1.setBackgroundColor(getResources().getColor(R.color.white));
                                            showFloorTwo();
                                            break;
                                    }
                                }
                            });
                }
                break;
            case ConnectionResult.SERVICE_MISSING:
                // Todo install google play services
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                // Todo go to playstore to upload google play services
                break;
            default:
                // Todo some error happends try again later
        }

        return rootView;
    }

    private void showFloorOne(){
        googlemap.clear();
        if(overlay!=null) {
            overlay.remove();
        }
        LatLng NEWARK = new LatLng(-16.500461, -68.134270);
        GroundOverlayOptions newarkMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.cncp1))
                .position(NEWARK, 30f, 30f);
        overlay = googlemap.addGroundOverlay(newarkMap);
        Marker marker = googlemap.addMarker(new MarkerOptions()
                .position(new LatLng(-16.500495, -68.134284))
                .title("Auditorio Principal")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_map)));
        if(type!=0){
            if(type==1){
                marker.showInfoWindow();
                type =0;
            }
        }

    }

    private void showFloorTwo(){
        googlemap.clear();
        if(overlay!=null) {
            overlay.remove();
        }
        LatLng NEWARK = new LatLng(-16.500461, -68.134270);
        GroundOverlayOptions newarkMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.cncp2))
                .position(NEWARK, 30f, 30f);
        overlay = googlemap.addGroundOverlay(newarkMap);


        Marker marker = googlemap.addMarker(new MarkerOptions()
                .position(new LatLng(-16.500425, -68.134262))
                .title("Auditorio 2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_map)));
        if(type!=0){
            if(type==2){
                marker.showInfoWindow();
                type =0;
            }
        }
        marker = googlemap.addMarker(new MarkerOptions()
                .position(new LatLng(-16.500501, -68.134340))
                .title("Ambiente 1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_map)));
        if(type!=0){
            if(type==3){
                marker.showInfoWindow();
                type =0;
            }
        }
    }
}
