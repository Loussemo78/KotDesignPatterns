package com.example.kotdesignpatterns.structural

import org.junit.Assert.assertEquals
import org.junit.Test



//Le système de stockage de données est implémenté par la classe ComplexSystemStore,
// qui stocke les données dans un fichier et utilise un cache en mémoire pour améliorer les performances.

class ComplexSystemStore (private val filePath: String) {
    private val cache: HashMap<String, String>

    init {
        println("Reading data from the file: $filePath")
        cache = HashMap()
        // Read properties from file and put to cache
    }

    fun store(key: String, value: String) {
        cache[key] = value
    }

    fun read(key: String) = cache[key] ?: ""

    fun commit() = println("Storing cached data to file $filePath")
}

data class User(val login: String)


// Facade

//La classe UserRepository expose deux méthodes publiques save et findFirst, qui sont utilisées pour enregistrer et récupérer des données utilisateur.
// La méthode save prend un objet User en entrée et stocke son nom d'utilisateur dans le système de stockage de données en utilisant la clé "USER_KEY".
// La méthode findFirst récupère les données stockées sous la clé "USER_KEY" et renvoie un objet User contenant le nom d'utilisateur.
class UserRepository {
    private val systemPreferences = ComplexSystemStore("/data/default.prefs")

    fun save(user: User) {
        systemPreferences.store("USER_KEY", user.login)
        systemPreferences.commit()
    }

    fun findFirst(): User = User(systemPreferences.read("USER_KEY"))
}

class FacadeTest {
    @Test
    fun testFacade() {
        val userRepo = UserRepository()
        val user = User("john")
        userRepo.save(user)

        val retrievedUser = userRepo.findFirst()

        assertEquals(retrievedUser.login,"john")
    }
}
