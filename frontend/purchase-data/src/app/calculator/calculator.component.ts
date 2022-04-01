import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { CalculatorService } from '../calculator-service/calculator.service';
import { PurchaseDataDto } from '../model/purchase-data-dto';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.scss']
})
export class CalculatorComponent implements OnInit {

  purchaseDataDto: PurchaseDataDto = {
    netValue: "100",
    grossValue: undefined,
    vatValue: undefined,
    vatRate: "0.2"
  }; // start with no values

  taxRateList: number[] = [0.1, 0.13, 0.2]; // to be retrieved from API

  errorMessage: string = '';


  private netValue: FormControl = new FormControl('');
  private grossValue: FormControl = new FormControl('');
  private vatValue: FormControl = new FormControl('');
  private vatRate: FormControl = new FormControl('');
  private radioInputData: FormControl = new FormControl('net');

  calculateForm: FormGroup = new FormGroup({
    netValue: this.netValue,
    grossValue: this.grossValue,
    vatValue: this.vatValue,
    vatRate: this.vatRate,
    radioInputData: this.radioInputData
  });


  constructor(private calculatorService: CalculatorService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  
  }

  calculate(): void {
    this.errorMessage = '';
    this.calculatorService.calculatePurchaseData(this.purchaseDataDto).subscribe(
      (purchaseDataDto) => { this.purchaseDataDto = purchaseDataDto },
      (error) => { this.errorMessage = error.error }
    )
  }

  disable(): void {
   

  }

}
