package org.csystem.app.company

open class ProjectWorker : Worker() {
    var projectName: String = ""
    var extra: Double = 0.0

    //...

    override fun calculateInsurancePayment() = super.calculateInsurancePayment() + extra * 1.5
}