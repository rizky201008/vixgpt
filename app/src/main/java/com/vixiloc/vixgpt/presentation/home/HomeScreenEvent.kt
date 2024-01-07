package com.vixiloc.vixgpt.presentation.home

sealed class HomeScreenEvent {
    data class QuestionChanged(val question: String) : HomeScreenEvent()
    object Submit : HomeScreenEvent()
    object AnswerDismissed : HomeScreenEvent()
    object ErrorAlertDismissed : HomeScreenEvent()
}