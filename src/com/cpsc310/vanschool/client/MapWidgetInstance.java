package com.cpsc310.vanschool.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.events.tiles.TilesLoadedMapEvent;
import com.google.gwt.maps.client.events.tiles.TilesLoadedMapHandler;
import com.google.gwt.maps.client.overlays.InfoWindow;
import com.google.gwt.maps.client.overlays.InfoWindowOptions;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import java.util.List;

public class MapWidgetInstance extends Composite {

    private VerticalPanel pWidget;
    private MapWidget mapWidget;
    private List<School> schoolList;

    public MapWidgetInstance() {
        pWidget = new VerticalPanel();
        initWidget(pWidget);
    }

    public void draw() {
        pWidget.clear();
        pWidget.add(new HTML("<br>Demo School map with markers"));

        schoolList = fetchSchoolData();

        drawMap();
        drawMarkers(schoolList);
        console(schoolList.toString());
    }

    private void drawMap() {
        LatLng center = LatLng.newInstance(49.236675, -123.12625);
        MapOptions opts = MapOptions.newInstance();
        opts.setZoom(11);
        opts.setCenter(center);
        opts.setMapTypeId(MapTypeId.ROADMAP);

        mapWidget = new MapWidget(opts);
        pWidget.add(mapWidget);
        mapWidget.setSize("750px", "500px");

        mapWidget.addClickHandler(new ClickMapHandler() {
            public void onEvent(ClickMapEvent event) {
                GWT.log("clicked on latlng=" + event.getMouseEvent().getLatLng());
            }
        });

        mapWidget.addTilesLoadedHandler(new TilesLoadedMapHandler() {
            public void onEvent(TilesLoadedMapEvent event) {
                // Load something after the tiles load
            }
        });
    }

    private void drawMarkers(List<School> schoolList) {
        for(School school : schoolList){
            drawMarker(school);
        }

    }

    private void drawMarker(School school) {
        LatLng center = LatLng.newInstance(school.getLocation().getLAT(), school.getLocation().getLONG());
        MarkerOptions options = MarkerOptions.newInstance();
        options.setPosition(center);
        options.setTitle(school.getName());

        final Marker marker = Marker.newInstance(options);
        marker.setMap(mapWidget);

        HTML html = new HTML(school.getName() + "<br> Address: " + school.getAddress() + "<br> Web link: <a  target=\"_blank\"  href='" + school.getUrl() + "'>" + school.getUrl());
        VerticalPanel vp = new VerticalPanel();
        vp.add(html);

        InfoWindowOptions infoWindowOptions = InfoWindowOptions.newInstance();
        infoWindowOptions.setContent(vp);

        final InfoWindow iw = InfoWindow.newInstance(infoWindowOptions);
        marker.addClickHandler(new ClickMapHandler() {
            public void onEvent(ClickMapEvent event) {
                iw.open(mapWidget, marker);
            }
        });
    }

    private List<School> fetchSchoolData() {
        //eventually will get the data from mySQLConnector, for now just create one
        List<School> schoolList = new ArrayList<School>();

        School school1 = new School("Point Grey Secondary", 3939012, "5350 Eest Boulevard", new Location(49.2379, -123.1534), "http://www.vsb.bc.ca/schools/point-grey", false, true, false, true, false, false, true, false, true, false);
        schoolList.add(school1);
        School school2 = new School("Maple Grove Elementary", 3939074, "6199 Cypress St", new Location(49.2294, -123.1515), "http://www.vsb.bc.ca/schools/maple-grove", false, true, false, true, false, false, false, false, false, false);
        schoolList.add(school2);
        School school3 = new School("Lord Byng Secondary", 3939008, "3939 W 16th Av", new Location(49.2596, -123.1925), "http://www.vsb.bc.ca/schools/lord-byng", false, true, false, true, false, false, true, false, true, true);
        schoolList.add(school3);
        School school4 = new School("Trafalgar Elementary", 3939084, "4170 Trafalgar St", new Location(49.2502, -123.1646), "http://www.vsb.bc.ca/schools/trafalgar", false, true, false, true, false, false, false, false, false, false);
        schoolList.add(school4);
        School school5 = new School("Templeton Secondary",3939010,"727 Templeton Drive", new Location(49.2784,-123.0607),"http://www.vsb.bc.ca/schools/templeton",false,true,false,true,false,false,true,false,true,false);
        schoolList.add(school5);

        return schoolList;
    }

    public List<School> getSchools(){
        return schoolList;
    }

    public static native void console(String text)
/*-{
    console.log(text);
}-*/;

}

