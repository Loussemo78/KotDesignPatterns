package com.example.kotdesignpatterns.behavior

import org.junit.Assert.assertEquals
import org.junit.Test

//Dans cet exemple, il y a trois types de contrats: FixedPriceContract, TimeAndMaterialsContract, et SupportContract.
// Chaque contrat implémente l'interface ReportElement et la méthode accept, qui prend en paramètre un objet ReportVisitor générique.
// La méthode accept appelle la méthode visit du visitor correspondant au type de contrat.

interface ReportElement {
    fun <R> accept(visitor: ReportVisitor<R>): R
}

class FixedPriceContract(val costPerYear: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

class TimeAndMaterialsContract(val costPerHour: Long, val hours: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

class SupportContract(val costPerMonth: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

//L'interface ReportVisitor définit les méthodes visit pour chaque type de contrat.
// Les implémentations de ces méthodes sont fournies par les classes MonthlyCostReportVisitor et YearlyCostReportVisitor,
// qui sont des visiteurs pour calculer le coût mensuel et annuel de chaque contrat.

interface ReportVisitor<out R> {
    fun visit(contract: FixedPriceContract): R
    fun visit(contract: TimeAndMaterialsContract): R
    fun visit(contract: SupportContract): R
}

class MonthlyCostReportVisitor : ReportVisitor<Long> {
    override fun visit(contract: FixedPriceContract): Long = contract.costPerYear / 12

    override fun visit(contract: TimeAndMaterialsContract): Long = contract.costPerHour * contract.hours

    override fun visit(contract: SupportContract): Long = contract.costPerMonth
}

class YearlyCostReportVisitor : ReportVisitor<Long> {
    override fun visit(contract: FixedPriceContract): Long = contract.costPerYear

    override fun visit(contract: TimeAndMaterialsContract): Long = contract.costPerHour * contract.hours

    override fun visit(contract: SupportContract): Long = contract.costPerMonth * 12
}


/*Enfin, dans la classe VisitorTest, des instances des contrats sont créées et stockées dans une liste.
Les visiteurs sont utilisés pour calculer le coût mensuel et annuel de chaque contrat en utilisant la méthode accept.
Le pattern Visitor permet donc de séparer le traitement des contrats du calcul du coût, en implémentant le traitement dans des classes de visiteurs spécifiques.
Cela permet une plus grande flexibilité et une meilleure extensibilité du code.*/

class VisitorTest {
    @Test
    fun testVisitor() {
        val projectAlpha = FixedPriceContract(10_000)
        val projectBeta = SupportContract(500)
        val projectGamma = TimeAndMaterialsContract(150, 10)
        val projectKappa = TimeAndMaterialsContract(50, 50)

        val project = arrayListOf(projectAlpha, projectBeta, projectGamma, projectKappa)

        val monthlyCostVisitor = MonthlyCostReportVisitor()
        val monthlyCost = project.map { it.accept(monthlyCostVisitor) }.sum()
        println("Monthly cost: $monthlyCost")
        assertEquals(monthlyCost,5333)

        val yearlyCostVisitor = YearlyCostReportVisitor()
        val yearlyCost = project.map { it.accept(yearlyCostVisitor) }.sum()
        println("Yearly cost: $yearlyCost")
        assertEquals(yearlyCost,20_000)
    }
}