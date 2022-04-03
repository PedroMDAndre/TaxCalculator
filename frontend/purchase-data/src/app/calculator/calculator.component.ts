import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CalculatorService } from '../calculator-service/calculator.service';
import { PurchaseDataDto } from '../model/purchase-data-dto';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.scss']
})
export class CalculatorComponent implements OnInit {

  purchaseDataDto: PurchaseDataDto = {};

  taxRateList: number[] = [];

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


  constructor(private calculatorService: CalculatorService) { }

  ngOnInit(): void {
    this.disable();
    this.calculatorService.getVatRates().subscribe(
      (vatRates) => {
        if (vatRates && vatRates.length > 0) {
          this.taxRateList = vatRates;
          this.vatRate.setValue(this.taxRateList[0]);
        }
      },
      (error) => { this.errorMessage = error.error }
    );
  }

  calculate(): void {
    this.errorMessage = '';

    switch (this.radioInputData.value) {
      case "net": {
        this.purchaseDataDto = {
          netValue: this.purchaseDataDto.netValue
        };
        break
      }

      case "gross": {
        this.purchaseDataDto = {
          grossValue: this.purchaseDataDto.grossValue
        };
        break;
      }

      case "vat": {
        this.purchaseDataDto = {
          vatValue: this.purchaseDataDto.vatValue
        };
        break;
      }
    }
    this.purchaseDataDto.vatRate = this.vatRate.value;


    this.calculatorService.calculatePurchaseData(this.purchaseDataDto).subscribe(
      (purchaseDataDto) => { this.purchaseDataDto = purchaseDataDto },
      (error) => { this.errorMessage = error.error }
    )
  }

  disable(): void {
    this.purchaseDataDto = {
      vatRate: this.purchaseDataDto.vatRate
    };

    this.netValue.disable();
    this.grossValue.disable();
    this.vatValue.disable();

    switch (this.radioInputData.value) {
      case "net": {
        this.netValue.enable();
        break
      }

      case "gross": {
        this.grossValue.enable();
        break;
      }

      case "vat": {
        this.vatValue.enable();
        break;
      }
    }
  }

}
