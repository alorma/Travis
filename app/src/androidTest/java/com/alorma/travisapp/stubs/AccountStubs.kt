package com.alorma.travisapp.stubs;

import android.support.test.InstrumentationRegistry.getInstrumentation
import com.alorma.travisapp.stubs.JsonReaderUtil.getStringFromFile
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*

class AccountStubs(val wireMockServer: WireMockServer) {
    fun givenValidAccountsResponse() {
        val request = get(urlPathEqualTo("/accounts"))

        val stringFromFile = getStringFromFile(getInstrumentation().context, "accounts_response.json")
        val response = aResponse().withBody(stringFromFile)

        wireMockServer.stubFor(request.willReturn(response))
    }
}