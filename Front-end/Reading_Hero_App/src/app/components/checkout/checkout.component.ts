import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Country } from 'src/app/common/country';
import { State } from 'src/app/common/state';
import { CartService } from 'src/app/services/cart.service';
import { CheckoutService } from 'src/app/services/checkout.service';
import { CartDetailsComponent } from '../cart-details/cart-details.component';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkout !: FormGroup;



  totalPrice : number = 0;
  totalQuantity : number = 0;

  creditCardYears: number[] = [];
  creditCardMonths: number[] = [];

  countries: Country[] = [];

  shippingStates: State[] = [];


  constructor(private formBuilder: FormBuilder
              ,private checkoutService:CheckoutService
              ,private cartService : CartService
              ,private router : Router) {
               
               }

  ngOnInit(): void {
    this.checkout = this.formBuilder.group({
    checkoutFormGroup : this.formBuilder.group({
      firstName:['',[Validators.required,Validators.minLength(2)]],
      lastName:['',Validators.required,Validators.minLength(2)],
      email:['',Validators.required,Validators.email] 
    }),
    shippingAddress : this.formBuilder.group({
      street:[''],
      city:[''],
      state:[''] ,
      country:[''],
      zipCode:['']
    }),
    creditCard :this.formBuilder.group({
      cardType:[''],
      nameOncard:[''],
      cardNumber:[''] ,
      securityCode:[''],
      expirationMonth:[''],
      expirationYear:['']
    })
  });

    const startMonth: number = new Date().getMonth() + 1;
    console.log("startMonth: " + startMonth);

    this.checkoutService.getCreditCardMonths(startMonth).subscribe(
      data => {
        console.log("Retrieved credit card months: " + JSON.stringify(data));
        this.creditCardMonths = data;
      }
    );

    // populate credit card years

    this.checkoutService.getCreditCardYears().subscribe(
      data => {
        console.log("Retrieved credit card years: " + JSON.stringify(data));
        this.creditCardYears = data;
      }
    );

    // populate countries

    this.checkoutService.getCountries().subscribe(
      data => {
        console.log("Retrieved countries: " + JSON.stringify(data));
        this.countries = data;
      }
    );
    }



  
  onSubmit(){
    
    console.log("Handling the submit button");
    const j = this.checkout.get('checkoutFormGroup') as any;
    console.log(j.value);
    console.log("email is   ...  "+j.value.email);
    alert("Order Placed ...");
    this.router.navigate(['/books']);


  }

  handleMonthsAndYears() {

    const j = this.checkout.get('creditCard') as any;

    const creditCardFormGroup = j.value;

    const currentYear: number = new Date().getFullYear();
    const selectedYear: number = Number(creditCardFormGroup.value.expirationYear);

    // if the current year equals the selected year, then start with the current month

    let startMonth: number;

    if (currentYear === selectedYear) {
      startMonth = new Date().getMonth() + 1;
    }
    else {
      startMonth = 1;
    }

    this.checkoutService.getCreditCardMonths(startMonth).subscribe(
      data => {
        console.log("Retrieved credit card months: " + JSON.stringify(data));
        this.creditCardMonths = data;
      }
    );
  }

  getStates(formGroupName: string) {

    const j = this.checkout.get('shippingAddress') as any;

    const formGroup = j.value;

    const countryCode = formGroup.value.country.code;
    const countryName = formGroup.value.country.name;

    console.log(`${formGroupName} country code: ${countryCode}`);
    console.log(`${formGroupName} country name: ${countryName}`);

    this.checkoutService.getStates(countryCode).subscribe(
      data => {

        if (formGroupName === 'shippingAddress') {
          this.shippingStates = data; 
        }
     

        // select first item by default
        formGroup.get('state').setValue(data[0]);
      }
    );
  }
  reviewCartDetails() {
    //subscribe to total quantity and total price
    this.cartService.totalQuantity.subscribe(
      (totalQuantity) => (this.totalQuantity = totalQuantity)
    );
    this.cartService.totalPrice.subscribe(
      (totalPrice) => (this.totalPrice = totalPrice)
    );
  }
}
