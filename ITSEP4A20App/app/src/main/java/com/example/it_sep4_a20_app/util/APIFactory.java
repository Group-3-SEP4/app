package com.example.it_sep4_a20_app.util;

import com.example.it_sep4_a20_app.networking.APIClient;
import com.example.it_sep4_a20_app.networking.IAPIClient;
import com.example.it_sep4_a20_app.networking.dummy.APIClientDummy;

public class APIFactory {
    private static IAPIClient apiclient;

    public static IAPIClient getAPIClient() {
        if(apiclient == null)
            apiclient = new APIClient(ServiceGenerator.getAPI());
        return apiclient;
    }

    private static IAPIClient apiClientLocal;

    public static IAPIClient getAPIClientLocalhost() {
        if(apiClientLocal == null)
            apiClientLocal = new APIClient(ServiceGenerator.getAPILocalHost());
        return apiClientLocal;
    }

    private static IAPIClient apiClientDummy;

    public static IAPIClient getAPIClientDummy() {
        if(apiClientDummy == null)
            apiClientDummy = new APIClientDummy();
        return apiClientDummy;
    }
}
