package com.example;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class SbbApplicationTests {


    @Autowired
    private WebApplicationContext appContext;

    @Autowired
    DataController dataController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(appContext);

//        var mockedRequest = Mockito.mock(HttpServletRequest.class);
//        when(dataController.getDatagroups(mockedRequest, any(), any(), any())).thenReturn(getDatagroupOverviewTo(mockedRequest));
    }

    private List<DatagroupOverviewTo> getDatagroupOverviewTo(HttpServletRequest request) {
        var req = HttpRequestUtil.getBaseUrl(request);

        return List.of(getDatagroupOverviewTo(1, req), getDatagroupOverviewTo(2, req), getDatagroupOverviewTo(3, req), getDatagroupOverviewTo(4, req),
                getDatagroupOverviewTo(5, req), getDatagroupOverviewTo(6, req));
    }

    private DatagroupOverviewTo getDatagroupOverviewTo(int idx, String req) {

        DatagroupOverviewTo datagroupOverviewTo = new DatagroupOverviewTo();
        datagroupOverviewTo.setAdcBuildNumber(idx % 2 == 0 ? "BuildNumber" + (idx) : null);
        datagroupOverviewTo.setAdcDump(idx % 2 == 0 ? null : String.valueOf(idx));
        datagroupOverviewTo.setAdcTemporal(false);
        datagroupOverviewTo.setBeschreibung(idx % 2 != 0 ? "Lorem" + (idx) : null);
        datagroupOverviewTo.setErstellungsZeitpunkt(idx % 2 == 0 ? DateTime.now().minusHours(idx) : null);
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




