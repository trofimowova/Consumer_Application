package com.example;

import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
public class DataController {


    @GetMapping(path = "/datagroups")
    public List<DatagroupOverviewTo> getDatagroups(HttpServletRequest request,
                                                   @RequestParam(required = false) String beschreibung,
                                                   @RequestParam(required = false) String postProcessorVersion,
                                                   @RequestParam(value = "buildNumber", required = false) String adcBuildNumber) {
        return getDatagroupOverviewTo(request);
    }

    private List<DatagroupOverviewTo> getDatagroupOverviewTo(HttpServletRequest request) {
        var req = HttpRequestUtil.getBaseUrl(request);

        return List.of(getDatagroupOverviewTo(1, req), getDatagroupOverviewTo(2, req), getDatagroupOverviewTo(3, req), getDatagroupOverviewTo(4, req),
                getDatagroupOverviewTo(5, req), getDatagroupOverviewTo(6, req));
        //return new ArrayList<>();
    }

    private DatagroupOverviewTo getDatagroupOverviewTo(int idx, String req) {

        DatagroupOverviewTo datagroupOverviewTo = new DatagroupOverviewTo();
        datagroupOverviewTo.setAdcBuildNumber(idx % 2 == 0 ? "BuildNumber" + (idx) : null);
        datagroupOverviewTo.setAdcDump(idx % 2 == 0 ? null : String.valueOf(idx));
        datagroupOverviewTo.setAdcTemporal(false);
        datagroupOverviewTo.setBeschreibung(idx % 2 != 0 ? "Lorem" + (idx) : null);
        datagroupOverviewTo.setErstellungsZeitpunkt(idx % 2 == 0 ? DateTime.now().minusDays(idx) : null);
        datagroupOverviewTo.setExporterVersion(idx % 2 != 0 ? null : String.valueOf(idx));
        datagroupOverviewTo.setStatus(idx % 2 == 0 ? "CONFIRMED" : null);
        datagroupOverviewTo.setPostProcessorVersion(idx % 2 != 0 ? null : String.valueOf(idx));
        datagroupOverviewTo.setReadyForSmoke(false);
        datagroupOverviewTo.setId(idx % 2 == 0 ? Long.valueOf(idx) : null);
        datagroupOverviewTo.setLatestPlandatenId(idx % 2 == 0 ? null : String.valueOf(idx));
        datagroupOverviewTo.setUrl(idx % 2 == 0 ? UriComponentsBuilder.fromHttpUrl(req).path("/datagroups").buildAndExpand(idx).encode().toString() : null);

        return datagroupOverviewTo;
    }


}





