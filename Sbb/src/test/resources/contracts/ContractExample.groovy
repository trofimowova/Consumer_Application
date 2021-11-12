package contracts


org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/datagroups'
        body([

        ])
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status OK()
        body(
                ["id"                  : anyPositiveInt(),
                 "beschreibung"        : anyAlphaUnicode(),
                 "adcDump"             : anyAlphaUnicode(),
                 "readyForSmoke"       : anyBoolean(),
                 "url"                 : anyUrl(),
                 "erstellungsZeitpunkt": anyDateTime(),
                 "status"              : anyAlphaUnicode(),
                 "postProcessorVersion": anyAlphaUnicode(),
                 "exporterVersion"     : anyAlphaUnicode(),
                 "adcTemporal"         : anyBoolean(),
                 "adcBuildNumber"      : anyAlphaUnicode(),
                 "latestPlandatenId"   : anyAlphaUnicode()

                ]
        )
        headers {
            contentType('application/json')
        }
    }

}


