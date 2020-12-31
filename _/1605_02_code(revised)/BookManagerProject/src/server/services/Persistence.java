package server.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import server.beans.Book;

public class Persistence
{

    private static Persistence instance = null;
    private SessionFactory sessionFactory;
    private Configuration configuration;
    
    private Persistence()
    {
        try
        {
            configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
            // Check if DB exists.  If not SQLGrammarException will be thrown and caught below
            getAllBooks();
        }
        // This will occur if the database DNE.
        catch (SQLGrammarException sqlGrammarException)
        {
            createDB();
        }
        catch (Exception e)
        {
            System.err.println("Persistence.constructor():error. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Persistence getInstance()
    {
        if (instance == null)
        {
            instance = new Persistence();
        }
        return instance;
    }
    
    public void setSessionFactory(SessionFactory value)
    {
        sessionFactory = value;
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void addBook(Book book)
    {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(book);
        tx.commit();
        session.close();
    }

    public void updateBook(Book book)
    {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(book);
        tx.commit();
        session.close();
    }
    
    public void deleteBook(Book book)
    {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(book);
        tx.commit();
        session.close();
    }
    

    @SuppressWarnings("unchecked")
    public Book getBook(Long book_id)
    {
        Book book = new Book();
        List<Book> books = getSessionFactory().openSession().createQuery("from Book where book_id = " + book_id).list();
        if (books != null && books.size() > 0)
        {
            book = books.get(0);
        }
        return book;
    }
    
    @SuppressWarnings("unchecked")
    public List<Book> getAllBooks()
    {
        return getSessionFactory().openSession().createQuery("from Book").list();
    }
    
    private void createDB()
    {
        SchemaExport se = new SchemaExport(configuration);
        se.create(true, true);
    }
}
