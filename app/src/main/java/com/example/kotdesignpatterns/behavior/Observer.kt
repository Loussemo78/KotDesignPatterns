package com.example.kotdesignpatterns.behavior

import org.junit.Test
import java.io.File

//L'interface EventListener définit une méthode update() qui est invoquée par l'objet observé lorsqu'un événement est déclenché.
//Cette méthode prend deux arguments : le type d'événement et l'objet File associé à l'événement.

interface EventListener {
    fun update(eventType: String?, file: File?)
}


//La classe EventManager est l'observé dans ce cas. Il contient une liste de listeners associée à chaque type d'événement.
//Les méthodes subscribe(), unsubscribe() et notify() sont utilisées pour ajouter, supprimer et notifier les observateurs respectivement.
// View model ou Presenter  dans une clean archi

class EventManager(vararg operations: String) {
    var listeners = hashMapOf<String, ArrayList<EventListener>>()

    init {
        for (operation in operations) {
            listeners[operation] = ArrayList<EventListener>()
        }
    }

    fun subscribe(eventType: String?, listener: EventListener) {
        val users = listeners[eventType]
        users?.add(listener)
    }

    fun unsubscribe(eventType: String?, listener: EventListener) {
        val users = listeners[eventType]
        users?.remove(listener)
    }

    fun notify(eventType: String?, file: File?) {
        val users = listeners[eventType]
        users?.let {
            for (listener in it) {
                listener.update(eventType, file)
            }
        }
    }
}

//La classe Editor contient un objet EventManager et une variable File pour suivre le fichier ouvert.
// Les méthodes openFile() et saveFile() sont utilisées pour ouvrir et enregistrer le fichier, respectivement.
//à rapprocher des données d'un fichier texte donc d'un Model dans une clean archi

class Editor {
    var events = EventManager("open", "save")

    private var file: File? = null

    fun openFile(filePath: String) {
        file = File(filePath)
        events.notify("open", file)
    }

    fun saveFile() {
        file?.let {
            events.notify("save", file)
        }
    }
}

//Les classes EmailNotificationListener et LogOpenListener sont des implémentations concrètes de EventListener.
// Ils ont chacun leur propre comportement lorsqu'ils sont notifiés.
//(Adaptateur dans une clean archi)
class EmailNotificationListener(private val email: String): EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Email to $email: Someone has performed $eventType operation with the file ${file?.name}")
    }
}

class LogOpenListener(var filename: String): EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Save to log $filename: Someone has performed $eventType operation with the file ${file?.name}")
    }
}

//Enfin, la classe ObserverTest est une simple classe de test qui crée un objet Editor, ajoute deux observateurs, ouvre un fichier et enregistre le fichier.
// Les deux observateurs sont censés être notifiés et effectuer leurs actions respectives.

class ObserverTest {
    @Test
    fun testObserver() {
        val editor = Editor()

        val logListener = LogOpenListener("path/to/log/file.txt")
        val emailListener = EmailNotificationListener("test@test.com")

        editor.events.subscribe("open", logListener)
        editor.events.subscribe("open", emailListener)
        editor.events.subscribe("save", emailListener)

        editor.openFile("test.txt")
        editor.saveFile()
    }
}