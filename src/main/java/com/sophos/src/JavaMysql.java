package com.sophos.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BasePage;

public class JavaMysql {
	private BasePage basePage;
	private Connection connect = null;
    private Statement query = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private WebDriver driver;
   
    private By inputSearch = By.xpath("//input[@title='Buscar']");
    private By searchDiv = By.id("search");
    private By resultsDiv = By.xpath("//p[@aria-level='3']");
    
    public JavaMysql(WebDriver driver) {
		this.driver = driver;
		this.basePage = new BasePage(driver);
	}
    
    public boolean setConnection() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
	        // Setup the connection with the DB
	        connect = DriverManager
	                .getConnection("jdbc:mysql://localhost/busquedas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
	                        ,"root", "root");
    		return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
    }
    
    public Boolean search() {
    	try {
    		query = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = query.executeQuery("select textobuscar from busquedas.busqueda");
            while (resultSet.next()) {
            	String textoaBuscar = resultSet.getString("textoBuscar");
            	System.out.println("Texto:: " + textoaBuscar);
            	driver.get("http:\\www.google.com.co");
            	
            	basePage.writeText(inputSearch, textoaBuscar);
            	basePage.submit(inputSearch);
            	
            	
        		WebElement element = basePage.waitPresenceOfElement(searchDiv);;
            		 		
    			 //System.out.println("Element size: " + element.getSize());
    			Dimension sizeContenedorResultado =  element.getSize();
    			//System.out.println("Element size Height: " + sizeContenedorResultado.getHeight());
    			//Boolean sinResultado = driver.findElements(By.xpath("//p[@aria-level='3']")).size() > 0 ;
    			
    			Boolean sinResultado = basePage.sizeElement(resultsDiv) > 0 ;
    			System.out.println(sinResultado && (sizeContenedorResultado.getHeight() == 0));
    			
    			preparedStatement = connect
    	                .prepareStatement("update busqueda set resultado=? where textobuscar LIKE ?;");
    	        
    	     // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
    	        // Parameters start with 1
    	        preparedStatement.setBoolean(1, !(sinResultado && (sizeContenedorResultado.getHeight() == 0)));
    	        preparedStatement.setString(2, textoaBuscar);
    	        preparedStatement.executeUpdate();

            }
            return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}

    }
}
