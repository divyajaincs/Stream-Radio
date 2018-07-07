package com.codecanyon.streamradio.v2;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by User on 2014.08.06..
 */
public class DataManager {

    private Context context;
    private String fileName;


    public DataManager(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

   /* public void addNewRadio(String radioListName, String location, String URL) {
        if (loadStoredData() == null) {
            addRadio(radioListName, location, URL, loadStoredData());
        }
        addRadio(radioListName, location, URL, loadStoredData());
    }*/


    public boolean addRadio(String radioListName, String location, String URL, ArrayList<String> oldLines) {
        try {
            String oldText = "";
            FileOutputStream fOut = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            for (String line : oldLines) {
                String data[] = line.split("<separator>");
                if (data[0].toString().equals(radioListName.toString()))
                    System.out.println("Egyezik");
                else
                    oldText = oldText + "" + line + "\n";
            }
            oldText = oldText + "" + radioListName + "<separator>" + location + "<separator>" + URL;
            fOut.write(oldText.getBytes());
            fOut.close();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public boolean deleteExistingData(String radioListName) {
        try {
            ArrayList<String> oldLines = loadStoredData();
            String oldText = "";
            FileOutputStream fOut = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            for (int i = 0; i < oldLines.size(); i++) {
                String part[] = oldLines.get(i).split("<separator>");
                if (!part[0].equals(radioListName) && i < oldLines.size() - 1) {
                    oldText = oldText + "" + oldLines.get(i) + "\n";
                } else if (!part[0].equals(radioListName) && i == oldLines.size() - 1) {
                    oldText = oldText + "" + oldLines.get(i);
                }
            }
            fOut.write(oldText.getBytes());
            fOut.close();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public ArrayList<String> loadStoredData() {
        ArrayList<String> lineList = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(fileName)));
            String line;
            while ((line = br.readLine()) != null) {
                lineList.add(line);
            }
            br.close();
            return lineList;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public void loadStoredRadioStations(ArrayList<RadioListElement> radioList, ArrayList<String> userRadios) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(fileName)));
            String line;
            userRadios.clear();
            while ((line = br.readLine()) != null) {
                String data[] = line.split("<separator>");
                System.out.println(line);
                userRadios.add(data[0]);
                radioList.add(new RadioListElement(context, data[0], data[1], data[2], true));
            }
            br.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void createRadioListForRadioScreen(TableLayout UIRadioList,  ArrayList<String> userRadios, TextView radioListName, TextView radioListLocation) {
        ArrayList<RadioListElement> radioArray = new ArrayList<RadioListElement>();
        MainActivity.getDataManager().loadStoredRadioStations(radioArray, userRadios);
        radioArray.add(new RadioListElement(context, "Bombay Hott Radio", "MUMBAI", "http://96.66.38.209:8012/;"));
        radioArray.add(new RadioListElement(context, "Big FM", "LUCKNOW", "http://sc-bb.1.fm:8017/"));
        radioArray.add(new RadioListElement(context, "Radio HSL", "DELHI", "http://50.7.68.251:7064/stream"));
        radioArray.add(new RadioListElement(context, "Hits Of Bollywood", "GUJARAT", "http://50.7.77.115:8174/"));
        radioArray.add(new RadioListElement(context, "City FM JK", "JAMMU", "http://streaming.radio.co/sf2d23b485/listen"));
        radioArray.add(new RadioListElement(context, "Red FM", "DELHI", "http://prclive1.listenon.in:8808/;stream.mp3"));
        radioArray.add(new RadioListElement(context, "Desi Radio", "PUNJAB", "http://desi.canstream.co.uk:8001/live.mp3"));
        radioArray.add(new RadioListElement(context, "Easy 96", "INDIA", "http://ice8.securenetsystems.net/EASY96"));
        radioArray.add(new RadioListElement(context, "Khas Haryanvi", "HARYANA", "http://khasharyanvi.com:8000/live"));
        radioArray.add(new RadioListElement(context, "Masala 101", "INDIA", "https://stream.radio.co/s9f46a9bb1/listen"));
        radioArray.add(new RadioListElement(context, "Mohd Rafi Radio", "MUMBAI", "http://radio.mohdrafi.com/rafi.mp3?1501312173835.mp3"));
        radioArray.add(new RadioListElement(context, "Radio City Gujarati", "GUJARAT", "http://prclive1.listenon.in:9932/;"));
        radioArray.add(new RadioListElement(context, "Rafa Radio", "INDIA", "http://37.59.47.192:8596/stream"));
        radioArray.add(new RadioListElement(context, "Spice FM", "DHAKA", "http://fmout.spicefm.in:8000/spice_b"));
        radioArray.add(new RadioListElement(context, "Radio Yo FM", "INDIA", "http://83.169.14.80:555/stream"));
        UIRadioList.removeAllViews();
        RadioList radioList = new RadioList(context, radioArray, UIRadioList);
        radioList.addRadioStations(radioListName, radioListLocation);

    }

}





















