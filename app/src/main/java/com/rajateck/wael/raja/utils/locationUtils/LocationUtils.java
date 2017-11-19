package com.rajateck.wael.raja.utils.locationUtils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import static android.content.Context.LOCATION_SERVICE;


public class LocationUtils {

    public static LatLang getLocationCoordinate(Context context) {
        LatLang latLang = new LatLang();

        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location l = locationManager.getLastKnownLocation("gps");
            if (l != null) {
                latLang.setLatitude(l.getLatitude());
                latLang.setLongitude(l.getLongitude());

                return latLang;
            }

            l = locationManager.getLastKnownLocation("network");
            if (l != null) {
                latLang.setLatitude(l.getLatitude());
                latLang.setLongitude(l.getLongitude());

                return latLang;
            }

            l = locationManager.getLastKnownLocation("passive");
            if (l != null) {
                latLang.setLatitude(l.getLatitude());
                latLang.setLongitude(l.getLongitude());

                return latLang;
            }

            return null;
        }
        return null;


    }

    public static double calculateDistance(LatLang firstLocation, LatLang secondLocation) {

        Location locationA = new Location("point A");

        locationA.setLatitude(firstLocation.latitude);
        locationA.setLongitude(firstLocation.longitude);

        Location locationB = new Location("point B");

        locationB.setLatitude(secondLocation.latitude);
        locationB.setLongitude(secondLocation.longitude);

        return Math.ceil(locationA.distanceTo(locationB) / 1000);


    }

    public static class LatLang {
        private Double latitude;
        private Double longitude;

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public LatLang(Double latitude, Double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public LatLang() {
        }
    }
}
