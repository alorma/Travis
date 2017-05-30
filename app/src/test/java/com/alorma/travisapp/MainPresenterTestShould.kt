package com.alorma.travisapp

import com.alorma.travisapp.data.account.AccountAgent
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class MainPresenterTestShould {

    lateinit var accountAgent: AccountAgent
    lateinit var view: MainPresenter.View

    lateinit var presenter: MainPresenter

    @Before
    fun setup() {
        accountAgent = mock(AccountAgent::class.java)
        view = mock(MainPresenter.View::class.java)

        presenter = MainPresenter(accountAgent)
        presenter.view = view
    }

    @Test
    fun call_show_no_account_when_agent_account_exist_returns_false() {
        given(accountAgent.accountExist()).willReturn(false)

        presenter.start()

        verify(view).showNoAccount()
    }
}
