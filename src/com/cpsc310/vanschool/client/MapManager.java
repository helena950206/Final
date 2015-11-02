package com.cpsc310.vanschool.client;

import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

public class MapManager {
    private final String mapsContainer = "maps";
    private MapWidgetInstance mapWidgetInstance;

    public MapManager(){
    }

    public void startMap(){
        loadMapApi();
    }

    private void loadMapApi() {
        boolean sensor = true;
        // load all the libs for use in the maps
        ArrayList<LoadApi.LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
        loadLibraries.add(LoadApi.LoadLibrary.ADSENSE);
        loadLibraries.add(LoadApi.LoadLibrary.DRAWING);
        loadLibraries.add(LoadApi.LoadLibrary.GEOMETRY);
        loadLibraries.add(LoadApi.LoadLibrary.PANORAMIO);
        loadLibraries.add(LoadApi.LoadLibrary.PLACES);
        loadLibraries.add(LoadApi.LoadLibrary.WEATHER);
        loadLibraries.add(LoadApi.LoadLibrary.VISUALIZATION);

        Runnable onLoad = new Runnable() {
            @Override
            public void run() {
                drawMapWidget();
            }
        };

        LoadApi.go(onLoad, loadLibraries, sensor);
    }

    private void drawMapWidget() {
        mapWidgetInstance = new MapWidgetInstance();
        mapWidgetInstance.draw();
        addMapWidget(mapWidgetInstance);
    }

    private void addMapWidget(Widget wMap) {
        RootPanel.get(mapsContainer).add(wMap);
    }


    public String getMapsContainer() {
        return mapsContainer;
    }

    public MapWidgetInstance getMapWidget() {
        return mapWidgetInstance;
    }



}
