package org.csystem.app.company

open class SalesManager : Manager() {
    var extra: Double = 0.0
    //...
    override fun calculateInsurancePayment() = super.calculateInsurancePayment() + extra
}