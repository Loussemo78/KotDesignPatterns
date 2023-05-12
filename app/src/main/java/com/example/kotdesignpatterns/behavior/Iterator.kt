import org.junit.Assert.assertEquals
import org.junit.Test


//La classe "Book" représente un livre avec un nom comme propriété.
class Book(val name: String)

//La classe "Library" représente une bibliothèque qui contient une liste de livres.
// Elle possède une méthode "getIterator" qui retourne une instance de la classe "BookIterator".
//iterator
class Library(val books: MutableList<Book>) {
    fun getIterator(): BookIterator {
        return BookIterator(this)
    }
}

//La classe "BookIterator" implémente l'interface "Iterator" et est utilisée pour itérer à travers la liste de livres de la bibliothèque.
// Elle garde une référence à la bibliothèque à travers laquelle elle peut accéder à la liste de livres dans le constructeur.
// La méthode "hasNext" retourne "true" si l'itérateur a encore des éléments à parcourir, sinon "false".
// La méthode "next" retourne le prochain élément de la liste et avance l'itérateur d'un cran.
// Si l'itérateur n'a plus d'éléments à parcourir, une exception "NoSuchElementException" est levée.

class BookIterator(private val library: Library) : Iterator<Book> {
    private var index = 0

    override fun hasNext(): Boolean {
        return index < library.books.size
    }

    override fun next(): Book {
        if (hasNext()) {
            return library.books[index++]
        }
        throw NoSuchElementException()
    }
}


//La boucle "while" dans la fonction "main" utilise l'itérateur pour parcourir la liste de livres de la bibliothèque et imprimer le nom de chaque livre à l'écran.
fun main() {
    val library = Library(mutableListOf(Book("Book 1"), Book("Book 2"), Book("Book 3")))

    val iterator = library.getIterator()

    while (iterator.hasNext()) {
        val book = iterator.next()
        println(book.name)
    }
}

class LibraryTest {
    @Test
    fun test() {
        val library = Library(mutableListOf(Book("Book 1"), Book("Book 2"), Book("Book 3")))
        val iterator = library.getIterator()
        var index = 1
        while (iterator.hasNext()) {
            val book = iterator.next()
            assertEquals("Book $index", book.name)
            index++
        }
    }
}
