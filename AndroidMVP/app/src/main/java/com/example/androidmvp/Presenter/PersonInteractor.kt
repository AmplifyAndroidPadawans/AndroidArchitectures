package com.example.androidmvp.Presenter

import com.example.androidmvp.Model.Person

class PersonInteractor {

    private val mPerson: Person = Person("david", "perez")

    fun isValidData(callback: (Person?, Boolean) -> Unit ) {
        if (mPerson.firstName.isEmpty() && mPerson.lastName.isEmpty()) {
            callback(null, false)
        } else {
            callback(mPerson, true)
        }
    }
}