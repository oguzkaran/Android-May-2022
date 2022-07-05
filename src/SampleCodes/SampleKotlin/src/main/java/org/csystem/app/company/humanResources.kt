package org.csystem.app.company

fun payInsurance(employee: Employee)
{
    println("Name:${employee.name}")
    println("Citizen Id:${employee.citizenId}")
    println("Address:${employee.address}")
    println("Insurance Payment:${employee.calculateInsurancePayment()}")
}

//...