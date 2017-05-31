package com.alorma.travisapp

import com.alorma.travisapp.data.account.AccountAgent
import com.alorma.travisapp.data.account.TravisAccount
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

open class MainPresenterTestShould {

    val accountAgent: AccountAgent = mock(AccountAgent::class.java)
    val view: MainPresenter.View = mock(MainPresenter.View::class.java)
    val account = Mockito.mock(TravisAccount::class.java)

    lateinit var presenter: MainPresenter

    @Before
    fun setup() {

        presenter = MainPresenter(accountAgent)
        presenter.view = view
    }

    @Test
    fun call_show_show_account_name_when_agent_account_exist_returns_true() {
        `when`(accountAgent.accountExist()).thenReturn(true)
        `when`(account.name()).thenReturn("UserName")
        `when`(accountAgent.getAccount()).thenReturn(account)

        presenter.start()

        verify(view).showAccountName("UserName")
    }

    @Test
    fun call_show_no_account_when_agent_account_exist_returns_false() {
        `when`(accountAgent.accountExist()).thenReturn(false)

        presenter.start()

        verify(view).showNoAccount()
    }
}
