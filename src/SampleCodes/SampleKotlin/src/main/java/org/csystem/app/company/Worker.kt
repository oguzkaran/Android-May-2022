package org.csystem.app.company

open class Worker : Employee() {
    var feePerHour: Double = 0.0
    var hourPerDay: Int = 0
    override fun calculateInsurancePayment() = feePerHour * hourPerDay * 30
}