package com.example.kotdesignpatterns.behavior

import org.junit.Assert.assertEquals
import org.junit.Test


//La classe scellée AuthorizationState est la classe de base de la hiérarchie d'états.
// Elle a deux sous-classes : Unauthorized et Authorized.

sealed class AuthorizationState

// La première est un objet singleton qui représente l'état d'autorisation non valide
object Unauthorized : AuthorizationState()

//  la seconde est une classe qui représente l'état d'autorisation valide et contient le nom d'utilisateur correspondant.
class Authorized(val username: String) : AuthorizationState()


//La classe AuthorizationPresenter maintient l'état actuel de l'autorisation en utilisant une propriété privée state, qui est initialement définie sur l'état Unauthorized.
// La classe fournit des méthodes publiques pour modifier l'état : loginUser(username: String) pour passer à l'état Authorized, et logoutUser() pour passer à l'état Unauthorized.
// La classe fournit également deux propriétés en lecture seule : isAuthorized qui renvoie un booléen indiquant si l'utilisateur est actuellement autorisé, et username qui renvoie le nom d'utilisateur si l'utilisateur est autorisé et "Unknown" sinon.

class AuthorizationPresenter {
    private var state: AuthorizationState = Unauthorized

    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is Unauthorized -> false
        }

    val username: String
        get() {
            return when (val state = this.state) {
                is Authorized -> state.username
                is Unauthorized -> "Unknown"
            }
        }

    fun loginUser(username: String) {
        state = Authorized(username)
    }

    fun logoutUser() {
        state = Unauthorized
    }

    override fun toString() = "User $username is logged in: $isAuthorized"
}


//La classe de test StateTest utilise la classe de présentation pour tester les fonctionnalités.
// Dans la méthode testState(), elle crée une instance de AuthorizationPresenter, connecte un utilisateur avec loginUser(), puis vérifie que les propriétés isAuthorized et username sont correctes en utilisant la fonction assertEquals().
// Ensuite, elle appelle logoutUser() et vérifie à nouveau que les propriétés ont été mises à jour correctement.

class StateTest {
    @Test
    fun testState() {
        val authorizationPresenter = AuthorizationPresenter()

        authorizationPresenter.loginUser("admin")
        println(authorizationPresenter)
        assertEquals(authorizationPresenter.isAuthorized,true)
        assertEquals(authorizationPresenter.username,"admin")

        authorizationPresenter.logoutUser()
        println(authorizationPresenter)
        assertEquals(authorizationPresenter.isAuthorized,false)
        assertEquals(authorizationPresenter.username,"Unknown")
    }
}
