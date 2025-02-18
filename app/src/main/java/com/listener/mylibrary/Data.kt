package com.listener.mylibrary

data class Book(
    val name: String,
    val author: String,
    val currentBooks: MutableList<CurrentBook>,
    val issuedBooks: MutableList<CurrentBook>
)

data class CurrentBook(
    val id: Long,
    val book: Book,
    val issuingDate: String?,
    val dueDate: String?,
    val overdue: Boolean?,
    val readerId: Long?
)

data class Reader(
    val id: Long,
    val name: String,
    val cardIssueDate: String,
    val lastRegistrationDate: String,
    val booksOnHand: MutableList<CurrentBook>
)

data class Librarian(
    val id: Long = 123,
    val name: String = "Иванов Иван Иванович",
    val registrationDate: String = "01.01.2023"
)

object Data {
    var currentBooks1: MutableList<CurrentBook> = mutableListOf()
    var books: List<Book> = emptyList()

    init {
        books = listOf(
            Book(
                "Преступление и наказание",
                "Ф.М. Достоевский",
                currentBooks1,
                mutableListOf()
            ),
            Book("Игрок", "Ф.М. Достоевский", mutableListOf(), mutableListOf()),
            Book("Капитанская дочка", "A.C. Пушкин", mutableListOf(), mutableListOf())
        )

        currentBooks1 = mutableListOf(
            CurrentBook(
                id = 140234,
                book = books[0],
                issuingDate = "01.01.2024",
                dueDate = "15.01.2024",
                overdue = true,
                readerId = 3234
            ),
            CurrentBook(
                id = 140235,
                book = books[0],
                issuingDate = "03.01.2024",
                dueDate = "18.01.2024",
                overdue = false,
                readerId = 3234
            ),
            CurrentBook(
                id = 140236,
                book = books[0],
                issuingDate = "05.01.2024",
                dueDate = "20.01.2024",
                overdue = false,
                readerId = null
            )
        )
        books[0].currentBooks.add(currentBooks1[0])
        books[0].currentBooks.add(currentBooks1[1])
        books[0].currentBooks.add(currentBooks1[2])

        books[0].issuedBooks.add(currentBooks1[0])
    }

    val readers = mutableListOf(
        Reader(
            321,
            "Воронцов Георгий Николаевич",
            "01.01.2024",
            "01.07.2024",
            mutableListOf(currentBooks1[0])
        )
    )

    val reader: Reader = readers[0]
    val librarian = Librarian()

    val passwords = mutableMapOf(
        "L123" to "123",
        "R321" to "321"
    )

    var mode: Char? = null
}
