package com.example.kotdesignpatterns.behavior

import org.junit.Assert.assertEquals
import org.junit.Test

// l'interface HandlerChain définit la méthode addHeader qui est appelée pour ajouter un en-tête à une chaîne d'en-têtes.
interface HandlerChain {
    fun addHeader(inputHeader: String): String
}

//AuthenticationHeader qui ajoute un en-tête d'authentification à la requête.
class AuthenticationHeader(val token: String?, var next: HandlerChain? = null): HandlerChain {
    override fun addHeader(inputHeader: String) =
        "$inputHeader\nAuthorization: $token"
            .let { next?.addHeader(it) ?: it }
}

//ContentTypeHeader qui ajoute un en-tête de type de contenu à la requête.
class ContentTypeHeader(val contentType: String, var next: HandlerChain? = null): HandlerChain {
    override fun addHeader(inputHeader: String) =
        "$inputHeader\nContentType: $contentType"
            .let { next?.addHeader(it) ?: it }
}

//BodyPayloadHeader qui ajoute le corps de la requête à la chaîne d'en-têtes.
class BodyPayloadHeader(val body: String, var next: HandlerChain? = null): HandlerChain {
    override fun addHeader(inputHeader: String) =
        "$inputHeader\n$body"
            .let { next?.addHeader(it) ?: it }
}

//Dans la méthode addHeader de chaque handler, l'en-tête correspondant est ajouté à la chaîne en utilisant la méthode let de Kotlin, qui permet d'exécuter une action sur une expression et de renvoyer le résultat de cette action.
//Ensuite, la méthode addHeader du handler suivant est appelée avec la nouvelle chaîne d'en-têtes créée, ou bien la chaîne est retournée telle quelle si aucun handler suivant n'est présent.

class ChainOfResponsibilityTest {
    @Test
    fun testChainOfResponsibility() {
        val authenticationHeader = AuthenticationHeader("123456")
        val contentTypeHeader = ContentTypeHeader("json")
        val bodyPayloadHeader = BodyPayloadHeader("Body: {\"username\" = \"john\"}")

        authenticationHeader.next = contentTypeHeader
        contentTypeHeader.next = bodyPayloadHeader

        val messageWithAuthentication = authenticationHeader.addHeader("Headers with authentication")
        println(messageWithAuthentication)

        println("-------------------------")

        val messageWithoutAuthentication = contentTypeHeader.addHeader("Headers without authentication")
        println(messageWithoutAuthentication)

        assertEquals(messageWithAuthentication,
            """
                    Headers with authentication
                    Authorization: 123456
                    ContentType: json
                    Body: {"username" = "john"}
                """.trimIndent()
        )

        assertEquals(messageWithoutAuthentication,
            """
                    Headers without authentication
                    ContentType: json
                    Body: {"username" = "john"}
                """.trimIndent()
        )
    }
}