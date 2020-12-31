package client.action;

import java.util.ArrayList;
import java.util.List;

import server.beans.Book;
import server.services.Persistence;

import com.opensymphony.xwork2.ActionSupport;

public class ListBooksAction extends ActionSupport
{
    List<Book> books = new ArrayList<Book>();
    
	public String execute() throws Exception
	{
		books = Persistence.getInstance().getAllBooks();
		return SUCCESS;
	}
	
	public List<Book> getBooks()
	{
	    return books;
	}
}
