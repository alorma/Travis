package com.alorma.travisapp;

import com.alorma.travisapp.data.account.AccountAgent;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class MainPresenterTestShould {

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @Mock AccountAgent accountAgent;
    @Mock MainPresenter.View view;

    private MainPresenter presenter;

    @Before
    public void setup() {
        presenter = new MainPresenter(accountAgent);
        presenter.view = view;
    }

    @Test
    public void call_show_no_account_when_agent_account_exist_returns_false() {
        given(accountAgent.accountExist()).willReturn(false);

        presenter.start();

        verify(view).showNoAccount();
    }
}