package com.alorma.travisapp

import com.alorma.travisapp.data.account.AccountAgent
import com.alorma.travisapp.data.account.TravisAccount
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

open class MainPresenterTestShould {

    lateinit var accountAgent: AccountAgent
    lateinit var view: MainPresenter.View
    lateinit var account: TravisAccount

    lateinit var presenter: MainPresenter

    @Before
    fun setup() {
        accountAgent = mock(AccountAgent::class.java)
        view = mock(MainPresenter.View::class.java)
        account = mock<TravisAccount>()

        presenter = MainPresenter(accountAgent)
        presenter.view = view
    }


    @Test
    fun call_show_show_account_name_when_agent_account_exist_returns_true() {
        given(accountAgent.accountExist()).willReturn(false)

        val account: TravisAccount = mock()
        whenever(account.name).thenReturn("UserName")

        given(accountAgent.getAccount()).willReturn(account)

        presenter.start()

        verify(view).showAccountName("UserName")
    }

    @Test
    fun call_show_no_account_when_agent_account_exist_returns_false() {
        given(accountAgent.accountExist()).willReturn(false)

        presenter.start()

        verify(view).showNoAccount()
    }
}
