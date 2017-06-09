package com.alorma.travisapp.stubs;

import android.support.test.InstrumentationRegistry.getInstrumentation
import com.alorma.travisapp.stubs.JsonReaderUtil.getStringFromFile
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*

class AccountReposStubs(val wireMockServer: WireMockServer) {

    fun givenValidAccountReposResponse() {
        val request = get(urlPathMatching("/repos/testuser"))

        val stringFromFile = getStringFromFile(getInstrumentation().context, "repos_response.json")
        val response = aResponse().withBody(stringFromFile)

        wireMockServer.stubFor(request.willReturn(response))
    }
}