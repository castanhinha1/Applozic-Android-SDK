package Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.applozic.mobicomkit.sample.R;
import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.ProgressCallback;
import com.parse.SaveCallback;

/**
 * Created by Dylan Castanhinha on 8/25/2017.
 */

@ParseClassName("_User")
public class MyUser extends ParseUser {

    public MyUser() {
        super();
    }

    public String getFullName() {
        return getString("fullname");
    }
    public void setFullName(String value) {
        put("fullname", value);
    }

    public String getOneSignalId() {
        return getString("onesignalid");
    }
    public void setOneSignalId(String value) {
        put("onesignalid", value);
    }


    public String getLocation(){
        return getString("location");
    }

    public void setLocation(String value){
        put("location", value);
    }

    public String getPhoneNumber(){
        return getString("phonenumber");
    }

    public void setPhoneNumber(String value){
        put("phonenumber", value);
    }

    public ParseGeoPoint getGeopoint(){
        return getParseGeoPoint("geopoint");
    }

    public void setGeopoint(ParseGeoPoint value){
        put("geopoint", value);
    }

    public double getLatitude() { return getDouble("latitude");}

    public void setLatitude(double value) { put("latitude", value);}

    public double getLongitude() { return getDouble("longitude");}

    public void setLongitude(double value) { put("longitude", value);}

    public String getFirstName(){
        return getString("firstname");
    }
    public void setFirstName(String value){
        put("firstname", value);
    }
    public String getLastName(){
        return getString("lastname");
    }
    public void setLastName(String value){
        put("lastname", value);
    }

    public String getLicensePlateState(){
        return getString("licenseplatestate");
    }
    public void setLicensePlateState(String value){
        put("licenseplatestate", value);
    }

    public String getLicensePlateNumber(){
        return getString("licenseplate");
    }
    public void setLicensePlateNumber(String value){
        put("licenseplate", value);
    }

    public String getSex(){
        return getString("sex");
    }
    public void setSex(String value){
        put("sex", value);
    }
    public String getAge(){
        return getString("age");
    }
    public void setAge(String value){
        put("age", value);
    }
    public ParseRelation<MyUser> getClients() {
        return getRelation("client");
    }
    public void setClients(ParseRelation<MyUser> clients) {
        put("client", clients);
    }

    public ParseFile getProfilePictureFile(){
        return getParseFile("profilepicture");
    }

    public Bitmap getProfilePicture(){
        Bitmap bmp = null;
        ParseFile profilePicture = getProfilePictureFile();
        if (profilePicture != null) {
            try {
                bmp = BitmapFactory.decodeStream(profilePicture.getDataStream());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            bmp = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.com_facebook_profile_picture_blank_square);
        }
        return bmp;
    }
    public void setProfilePicture(byte[] value){
        final ParseFile profilePicture = new ParseFile("profilepicture.png", value);
        profilePicture.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("AppInfo", "Photo saved");
                    MyUser currentUser = (MyUser) MyUser.getCurrentUser();
                    currentUser.put("profilepicture", profilePicture);
                    currentUser.saveInBackground();
                } else {
                    Log.i("AppInfo", e.getMessage());
                }
            }
        }, new ProgressCallback() {
            @Override
            public void done(Integer percentDone) {
                Log.i("AppInfo", "Percent Done: "+percentDone.toString());
            }
        });
    }

    public LatLng getLatLng(){
        LatLng latLng = new LatLng(getGeopoint().getLatitude(), getGeopoint().getLongitude());
        return latLng;
    }
}
