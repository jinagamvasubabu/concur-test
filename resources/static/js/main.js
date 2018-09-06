
var rootURL = "http://localhost:8081/billing/api";
var webURL = "http://localhost:8081/billing"

var productsToBeCreated = [];
var orderId = null;
$(document).ready(function(){
    console.log("All set!!!");
    $("#createMe").click(function(){
      console.log("Going to create User!!!");
      createUser();
    });

    $("#loginMe").click(function(){
          console.log("Login user!!!");
          loginMe();
    });

    $("#addProduct").click(function(){
      console.log("Going to add product!!!");
      checkAndAddProduct();
    });

    $("#updateProduct").click(function(){
      console.log("Going to Modify product!!!");
      changeProduct();
    });

    $("#searchProduct").click(function(){
          console.log("Going to Search product!!!");
          searchProduct();
    });

    $("#checkout").click(function(){
      console.log("checkout product");
      checkoutProduct();
    });


    $("#getStockInvoice").click(function(){
      console.log("Solid stock report");
      invokeStoreInvoice();
    });
});


function changeProduct() {
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL+"/product/add",
		data: changeProductToJson(),
		success: function(data, textStatus, jqXHR){

            if (data == "Inventory updated!!!") {
                alert(data);
            }

        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error: ' + textStatus);
        }
	});
}

function searchProduct() {
        var productName = $('#productName').val();
    	$.ajax({
    		type: 'GET',
    		contentType: 'application/json',
    		url: rootURL+"/product/search?productName="+productName,
    		success: function(data, textStatus, jqXHR){
    		    if (data)
    		        renderSearchResults(data);
    		    else
    		        $("#searchDiv").hide();

            },
            error: function(jqXHR, textStatus, errorThrown){
                alert('Error: ' + textStatus);
            }
    	});
}


function checkoutProduct() {
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL+"/order/create",
		data: JSON.stringify(productsToBeCreated),
		success: function(data, textStatus, jqXHR){

            if (data !== "We don't have enough stock to fulfil this order!!!") {
                alert("Order created successfully!!!");
                productsToBeCreated = [];
                orderId = data;
               // window.location.href = webURL+"/html/orderInvoice.html";
               invokeOrderInvoice();
            } else {
                alert(data);
            }

        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error: ' + textStatus);
        }
	});
}

function invokeOrderInvoice() {
    $.ajax({
    		type: 'GET',
    		contentType: 'application/json',
    		url: rootURL+"/invoice/order?orderId="+orderId,
    		success: function(data, textStatus, jqXHR){
               renderOrderInvoice(data);
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert('Error: ' + textStatus);
            }
    	});
}

function invokeStoreInvoice() {
    $.ajax({
    		type: 'GET',
    		contentType: 'application/json',
    		url: rootURL+"/invoice/store",
    		success: function(data, textStatus, jqXHR){
               renderStoreInvoice(data);
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert('Error: ' + textStatus);
            }
    	});
}

function renderSearchResults(data) {
    $("#searchDiv").show();
    $("#searchProductName").html(data.productName);
    $("#searchProductQuantity").html(data.quantity);
    $("#searchProductName").html(data.price);

    $("#searchDiv").show();
}

function renderStoreInvoice(data) {
    $('#storeInvoiceList li').remove();
    $.each(data.productList, function(index, product) {
        $('#storeInvoiceList').append('<li>'+product.productName+','+product.quantity+','+product.totalAmount+'</li>');
    });

    $("#storeTotalAmount").html(data.totalAmount);
    $("#storeInvoice").show();
}

function renderOrderInvoice(data) {
    $('#orderInvoiceList li').remove();
    $.each(data.productList, function(index, product) {
        $('#orderInvoiceList').append('<li>'+product.productName+','+product.quantity+','+product.totalAmount+'</li>');
    });

    $("#orderTotalAmount").html(data.totalAmount);
    $("#orderInvoice").show();
}





function checkAndAddProduct() {
    $("#orderInvoice").hide();
   if ($('#productName').val()!="" && $('#quantity').val() != "") {
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL+"/product/quantityCheck",
		data: productToJson(),
		success: function(data, textStatus, jqXHR){
            alert(data);
            if (data === "product available at inventory!!!") {
                var product = {};
                product.productName = $('#productName').val();
                product.quantity = $('#quantity').val();
                productsToBeCreated.push(product);
            }
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error: ' + textStatus);
        }
	});
	} else {
	    alert("Please fill all fields!!!");
	}
}

function createUser() {
console.log($('#username').val());
if ($('#username').val()!="" && $('#password').val() != "") {
        console.log('Onboarding user!!!');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL+"/user/signup",
		data: userToJson(),
		success: function(data, textStatus, jqXHR){
        			alert(data);
        			if (data === "You have signedup successfully!!!!")
        			    window.location.href = webURL+"/index.html";
        		},
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error: ' + textStatus);
        }
	});
	} else {
	    alert("Please fill all fields!!!");
	}
}

function loginMe() {
    console.log('Login the user!!!');
    if ($('#username').val() != "" && $('#password').val() != "") {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: rootURL+"/user/login",
            data: userToJson(),
            success: function(data, textStatus, jqXHR){
                        console.log(data);
                        if (data === "User Logged in!!!")
                            window.location.href = webURL+"/html/welcome.html";
                            else
                          alert("Invalid Credentials!!!");
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert('Error: ' + textStatus);
            }
        });
	} else {
	     alert("Please fill all fields!!!");
	}
}

function userToJson() {
 if ($('#username').val() && $('#password').val())
	return JSON.stringify({
		"userName": $('#username').val(),
		"password": $('#password').val()
		});
}

function productToJson() {
    if ($('#productName').val() && $('#quantity').val())
        return JSON.stringify({
            "productName": $('#productName').val(),
            "quantity": $('#quantity').val()
            });
}

function changeProductToJson() {
    if ($('#productName').val() && $('#quantity').val())
        return JSON.stringify({
            "productName": $('#productName').val(),
            "quantity": $('#quantity').val(),
            "price": $('#price').val()
        });
}






