package com.automationexercise.stepDefinitions;


import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.pages.ProductPage;
import com.automationexercise.utilities.Driver;
import com.automationexercise.utilities.ReusableMethods;
import com.github.javafaker.Faker;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

import static com.automationexercise.utilities.ReusableMethods.*;
import static org.junit.Assert.*;

public class ProductPageStepDefinitions {

    HomePage homePage = new HomePage();
    LoginPage loginPage=new LoginPage();
    ProductPage productPage=new ProductPage();
    List<String> clickedProductNames=new ArrayList<>();
    List<String> addedCartProductNames=new ArrayList<>();

    @Then("user hovers over product {int} and click add to cart button")
    public void user_hovers_over_product_and_click_add_to_cart_button(Integer prdctOrder) {
        int num=prdctOrder-1;
        hover(homePage.productOverLaysList.get(num));
        clickedProductNames.add(homePage.productsNameList.get(num).getText());
        waitFor(1);
        jsClick(homePage.addToCartButtonList.get(num));

    }
    @Then("user clicks view cart button")
    public void user_clicks_view_cart_button() {
        homePage.viewCartButton.click();
    }
    @Then("user verifies both two product are added to cart")
    public void user_verifies_both_two_product_are_added_to_cart() {
        addedCartProductNames.add(homePage.productNamesAddedCart.get(0).getText());
        addedCartProductNames.add(homePage.productNamesAddedCart.get(1).getText());
        assertTrue(addedCartProductNames.containsAll(clickedProductNames));
    }
    @Then("user clicks continue shopping button")
    public void user_clicks_continue_shopping_button() {
        homePage.continueShoppingButton.click();
    }

    @When("user clicks view product for the {int}. product on home page")
    public void user_clicks_view_product_for_the_product_on_home_page(Integer orderOfPrdct) {
        int prdctOrder=orderOfPrdct-1;
        for (int i = 0; i <2 ; i++) {
            homePage.viewProductHomePageLinks.get(prdctOrder).click();
            Driver.getDriver().navigate().refresh();
        }

    }
    @Then("user verifies product detail is opened and increase quantity to {int}")
    public void user_verifies_product_detail_is_opened_and_increase_quantity_to(Integer quantityNum) {
       String quantityOfPrdct=quantityNum.toString();
       homePage.quantityOfProduct.clear();
       homePage.quantityOfProduct.sendKeys(quantityOfPrdct);

    }
    @Then("user clicks add to cart button and clicks view cart")
    public void user_clicks_add_to_cart_button_and_clicks_view_cart() {
        homePage.addToCartButton.click();
        homePage.viewCartButton.click();
    }
    @Then("user verifies that product is displayed in cart page with {int} quantity")
    public void user_verifies_that_product_is_displayed_in_cart_page_with_quantity(Integer expectedQuantity) {
        Integer actualQuantity=Integer.parseInt(homePage.cartQuantityList.get(0).getText());
        assertEquals(expectedQuantity, actualQuantity);
    }
    @Then("user clicks proceed to checkout button")
    public void user_clicks_proceed_to_checkout_button() {
      homePage.proceedToCheckoutButton.click();
    }
    @Then("user clicks RegisterLogin button and fills details in signup and create account")
    public void user_clicks_register_login_button_and_fills_details_in_signup_and_create_account() {
        homePage.registerLoginButton.click();
        loginPage.signupName.sendKeys(Faker.instance().name().fullName());
        loginPage.singupEmail.sendKeys(Faker.instance().internet().emailAddress());
        loginPage.signupButton.click();
        ReusableMethods.signUp();
    }
    @Then("user enters description in comment text area and click place order button")
    public void user_enters_description_in_comment_text_area_and_click_place_order_button() {
        productPage.checkoutTextArea.sendKeys(Faker.instance().lorem().paragraph());
        productPage.placeOrderButton.click();
    }

}
