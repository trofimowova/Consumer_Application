package com.example;


import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

@Getter
@Setter
public class DatagroupOverviewTo {

    private Long id;
    private String beschreibung;
    private String adcDump;
    private boolean readyForSmoke;
    private String url;
    private DateTime erstellungsZeitpunkt;
    private String status;
    private String postProcessorVersion;
    private String exporterVersion;
    private boolean adcTemporal;
    private String adcBuildNumber;
    private String latestPlandatenId;
}
