package com.example.androidmvp.Presenter

class PersonPresenter(private val mView: PersonContract.View): PersonContract.Presenter {
    private val mInteractor = PersonInteractor()

    override fun loadMessage() {
        mInteractor.isValidData { person, isValid ->
            if (isValid) {
                val message = "Hi " + person?.firstName.toString() + "!"
                mView.showMessage(message)
            } else {
                mView.showError("No person name found.")
            }
        }

    }

    override fun saveName(firstName: String, lastName: String) {
        TODO("Not yet implemented")
    }

    override fun onPause() {
        TODO("Not yet implemented")
    }
}