package org.csystem.app.company

open class Manager : Employee() {
    var department: String = ""
    var salary: Double = 0.0

    //...
    override fun calculateInsurancePayment() = salary * 1.5
}