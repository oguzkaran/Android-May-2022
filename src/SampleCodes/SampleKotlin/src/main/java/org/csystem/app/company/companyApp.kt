package org.csystem.app.company


private fun getManager() : Manager
{
    val m = Manager()

    m.name = "Ali";
    m.citizenId = "12345678912"
    m.address = "Şişli"
    m.department = "Pazarlama"
    m.salary = 40000.7

    return m
}

private fun getWorker() : Worker
{
    val w = Worker()

    w.name = "Veli";
    w.citizenId = "12345678916"
    w.address = "Beşiktaş"
    w.feePerHour = 250.6
    w.hourPerDay = 8

    return w
}

private fun getSalesManager() : SalesManager
{
    val m = SalesManager()

    m.name = "Ayşe";
    m.citizenId = "12345678914"
    m.address = "Şişli"
    m.department = "Yazılım"
    m.salary = 40000.7
    m.extra = 10000.9

    return m
}

private fun getProjectWorker() : ProjectWorker
{
    val w = ProjectWorker()

    w.name = "Fatma";
    w.citizenId = "12345678918"
    w.address = "Mecidiyeköy"
    w.feePerHour = 250.6
    w.hourPerDay = 8
    w.projectName = "Online chat platform"
    w.extra = 15000.6

    return w
}


fun runCompanyApp()
{
    val manager = getManager()
    val worker = getWorker()
    val salesManager = getSalesManager()
    val projectWorker = getProjectWorker()

    payInsurance(manager)
    payInsurance(worker)
    payInsurance(salesManager)
    payInsurance(projectWorker)
}

