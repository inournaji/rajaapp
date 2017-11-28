package com.rajateck.wael.raja.controllers.inAppViews.ourBranches;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.connection.Connection;
import com.rajateck.wael.raja.customViews.branchedPackage.BranchesPopUpForm;
import com.rajateck.wael.raja.customViews.branchedPackage.BranchesPopUpInterface;
import com.rajateck.wael.raja.customViews.branchedPackage.BranchesPopup;
import com.rajateck.wael.raja.delegates.networkDelegates.GetBranchesDelegate;
import com.rajateck.wael.raja.models.Branches;
import com.rajateck.wael.raja.utils.GoogleMapUtils;
import com.rajateck.wael.raja.utils.ScreenUtils;
import com.rajateck.wael.raja.utils.cacheUtils.RajaCacheUtils;
import com.rajateck.wael.raja.utils.locationUtils.LocationUtils;

import java.util.ArrayList;

import fr.arnaudguyon.tabstacker.TabStacker;


public class OurBranchesFragment extends Fragment implements TabStacker.TabStackInterface, OnClickListener, OnMapReadyCallback {
    private View inflatedView;
    private GoogleMap googleMap;
    private String[] mPlaceType = null;
    private RelativeLayout googleRel;
    private LocationUtils.LatLang userLatLong;
    private ArrayList<Branches> rajaBranches;
    private Snackbar snackbar;

    public OurBranchesFragment() {

    }


    public static OurBranchesFragment newInstance(String param1, String param2) {
        return new OurBranchesFragment();
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        checkGooglePlayServices();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflatedView = inflater.inflate(R.layout.fragment_map, container, false);
        googleRel = inflatedView.findViewById(R.id.googleRel);
        setRetainInstance(false);
        return this.inflatedView;
    }


    private void checkGooglePlayServices() {
        try {
            int v = getActivity().getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            int available = googleApiAvailability.isGooglePlayServicesAvailable(getActivity());
            System.out.println("RewardsPopup.checkGooglePlayServices : " + v);
            System.out.println("RewardsPopup.checkGooglePlayServices is google play available = " + available);
            if (available == ConnectionResult.SUCCESS) {
                //alarm to go and install Google Play Services
            } else if (available == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED) {
                Toast.makeText(getActivity(), "please update your google play service", Toast.LENGTH_SHORT).show();

                googleRel.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.inflatedView = view;
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onTabFragmentPresented(TabStacker.PresentReason presentReason) {

    }

    @Override
    public void onTabFragmentDismissed(TabStacker.DismissReason dismissReason) {
        System.out.println("RewardsPopup.onStop : it's stop here.");
        try {
            android.support.v4.app.Fragment fragment = getChildFragmentManager().findFragmentById(R.id.map);

            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.remove(fragment);
            ft.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public View onSaveTabFragmentInstance(Bundle bundle) {
        return null;
    }

    @Override
    public void onRestoreTabFragmentInstance(Bundle bundle) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap1) {
        System.out.println("RewardsPopup.onMapReady : it's on map ready");
        googleMap = googleMap1;
        animateToUserLocation();
        addMarkerToUserLocation();
        getRajaMarkers();


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                if (rajaBranches != null) {
                    Branches branches = null;
                    for (int i = 0; i < rajaBranches.size(); i++) {
                        try {
                            if (rajaBranches.get(i).getLat() != null &&
                                    Double.valueOf(rajaBranches.get(i).getLat()) == marker.getPosition().latitude) {
                                branches = rajaBranches.get(i);
                                System.out.println("OurBranchesFragment.onMapClick : found");
                            } else {
                                System.out.println("OurBranchesFragment.onMapClick : not found");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }


                    if (branches != null) {

                        String popupMessage = "";

                        popupMessage = branches.getTitle() +
                                "\n" + branches.getAddress();

                        Boolean callEnabled = false;

                        if (branches.getPhonenumber() != null && branches.getPhonenumber().length() > 0) {
                            popupMessage += "\n" + getString(R.string.phoneNumber) + ": " +
                                    branches.getPhonenumber();
                            callEnabled = true;
                        }


                        final BranchesPopUpForm branchesPopUpForm = new BranchesPopUpForm(
                                branches.getTitle(),
                                popupMessage,
                                getString(R.string.visit),
                                getString(R.string.close),
                                callEnabled,
                                new BranchesPopUpInterface() {
                                    @Override
                                    public void onPositiveButtonClicked() {
                                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                                Uri.parse("http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude));
                                        try {
                                            getActivity().startActivity(intent);
                                        } catch (ActivityNotFoundException e) {
                                            Toast.makeText(getActivity(), "Please install a maps application", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onNegativeButtonClicked() {

                                    }

                                    @Override
                                    public void onDismiss() {

                                    }
                                });
                        if (branches.getPhonenumber() != null &&
                                branches.getPhonenumber().length() > 0) {
                            branchesPopUpForm.setPhoneNumber(branches.getPhonenumber());
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                BranchesPopup.newInstance(getContext(), branchesPopUpForm).show();
                            }
                        }, 1000);

                    }
                }

                return false;
            }
        });
    }


    private void getRajaMarkers() {

        ScreenUtils.showLoader(getActivity());
        Connection.getBranches(new GetBranchesDelegate() {
            @Override
            public void GetBranchesSuccessDelegate(ArrayList<Branches> branches, Boolean success) {
                System.out.println("OurBranchesFragment.GetBranchesSuccessDelegate");
                ScreenUtils.dismissLoader();
                rajaBranches = branches;
                if (rajaBranches == null) {
                    rajaBranches = new ArrayList<>();
                }
                RajaCacheUtils.cacheMapPines(rajaBranches);
                addRajaMarkers(rajaBranches);
            }

            @Override
            public void GetBranchesFailureDelegate(String error) {
                System.out.println("OurBranchesFragment.GetBranchesFailureDelegate");
                ScreenUtils.dismissLoader();
                snackbar = Snackbar
                        .make(inflatedView, getString(R.string.errorLoading), Snackbar.LENGTH_INDEFINITE)
                        .setAction(getString(R.string.retry), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getRajaMarkers();
                                snackbar.dismiss();
                            }
                        });
                snackbar.show();
            }

            @Override
            public void GetBranchesConnectionErrorDelegate() {
                System.out.println("OurBranchesFragment.GetBranchesConnectionErrorDelegate");
                ScreenUtils.dismissLoader();

                snackbar = Snackbar
                        .make(inflatedView, getString(R.string.errorLoading), Snackbar.LENGTH_INDEFINITE)
                        .setAction(getString(R.string.retry), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getRajaMarkers();
                                snackbar.dismiss();
                            }
                        });
                snackbar.show();
            }
        }, getActivity());
    }


    private void addRajaMarkers(ArrayList<Branches> branches) {

        System.out.println("OurBranchesFragment.addRajaMarkers : the number of markers =" + branches.size());

        addMarkerToUserLocation();

        for (int i = 0; i < branches.size(); i++) {
            if (branches.get(i).getLat() != null &&
                    branches.get(i).getLat().trim().length() != 0) {

                Bitmap firstMarkerBitmap = GoogleMapUtils.resizeMapIconFromAssets(getActivity(),
                        "marker2",
                        (int) ScreenUtils.dpToPx(getActivity(), 36),
                        (int) ScreenUtils.dpToPx(getActivity(), 36));


                String rajaLocatoin = "Damascus Location";

                if (branches.get(i).getTitle() != null &&
                        branches.get(i).getTitle().trim().length() != 0) {
                    rajaLocatoin = branches.get(i).getTitle();
                }


                if (firstMarkerBitmap != null) {
                    try {
                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(Double.valueOf(branches.get(i).getLat()), Double.valueOf(branches.get(i).getLonge())))
                                .title(rajaLocatoin)
                                .icon(BitmapDescriptorFactory.fromBitmap(firstMarkerBitmap)));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(Double.valueOf(branches.get(i).getLat()), Double.valueOf(branches.get(i).getLonge())))
                                .title(rajaLocatoin)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }


    private void addMarkerToUserLocation() {

        Bitmap firstMarkerBitmap = GoogleMapUtils.resizeMapIconFromAssets(getActivity(),
                "ic_my_location",
                (int) ScreenUtils.dpToPx(getActivity(), 23),
                (int) ScreenUtils.dpToPx(getActivity(), 23));

        if (userLatLong != null) {
            if (firstMarkerBitmap != null) {
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(userLatLong.getLatitude(), userLatLong.getLongitude()))
                        .title(getString(R.string.yourLocation))
                        .icon(BitmapDescriptorFactory.fromBitmap(firstMarkerBitmap)));
            } else {
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(userLatLong.getLatitude(), userLatLong.getLongitude()))
                        .title(getString(R.string.yourLocation))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));
            }
        }
    }


    private void animateToUserLocation() {
        userLatLong = LocationUtils.getLocationCoordinate(getActivity());
        if (userLatLong != null &&
                userLatLong.getLatitude() != null &&
                userLatLong.getLatitude() != 0) {
            animateCameraToThisLocation(userLatLong, false);
        } else {
            animateCameraToThisLocation(new LocationUtils.LatLang(33.513430, 36.276426), true);
//            animateCameraToDubai();
        }
    }


    private void animateCameraToDubai() {
        try {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(25.096736, 55.157188))
                    .zoom(16)                   // Sets the zoom
                    .tilt(70)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        } catch (Exception ex) {
            System.out.println("FindParking : error during animate camera");
            ex.printStackTrace();
        }
    }


    private void animateCameraToThisLocation(LocationUtils.LatLang latLng, boolean defaultLocation) {
        if (latLng != null) {
            try {
                int zoomLevel = 16;
                if (defaultLocation) {
                    zoomLevel = 12;
                }
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(latLng.getLatitude(), latLng.getLongitude()))
                        .zoom(zoomLevel)                   // Sets the zoom
                        .tilt(70)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            } catch (Exception ex) {
                System.out.println("FindParking : error during animate camera");
                ex.printStackTrace();
            }
        } else {
            animateCameraToDubai();
        }
    }

}
