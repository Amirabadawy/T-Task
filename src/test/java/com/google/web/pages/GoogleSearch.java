package com.google.web.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearch extends BasePage{

    @FindBy(id="APjFqb")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@class='qLRx3b tjvcx GvPZzd cHaqb']")
    private WebElement wuzzufLink;



    @FindBy(xpath = "//*[@class=\"css-uzlk74\"]")
    private WebElement numberOfJobs;

    public void enterText(String searchText){
        searchInput.sendKeys(searchText);
        searchInput.sendKeys(Keys.ENTER);
    }

    public WebElement getWuzzufLink(){
        return wuzzufLink;
    }

    public WebElement getNumberOfJobs(){
        return numberOfJobs;
    }

    public void clickWuzzufLink(){
        wuzzufLink.click();
    }

}
