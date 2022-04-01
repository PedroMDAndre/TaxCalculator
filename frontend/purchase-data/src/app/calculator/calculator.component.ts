import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CalculatorService } from '../calculator-service/calculator.service';
import { PurchaseDataDto } from '../model/purchase-data-dto';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.scss']
})
export class CalculatorComponent {

  purchaseDataDto: PurchaseDataDto = {
    netValue: "100",
    grossValue: undefined,
    vatValue: undefined,
    vatRate: "0.2"
  };

  taxRateList: number[] = [0.1, 0.13, 0.2];

  errorMessage: string = '';

 calculateForm: FormGroup = this.formBuilder.group({
    netValue: {value: undefined, disabled: true},
    grossValue: {value: undefined, disabled: true},
    vatValue: {value: undefined, disabled: true},
    vatRate: {value: undefined}
  });

  constructor(private calculatorService: CalculatorService, private formBuilder: FormBuilder) { }

  calculate(): void {
    this.errorMessage = '';
    this.calculatorService.calculatePurchaseData(this.purchaseDataDto).subscribe(
      (purchaseDataDto) => { this.purchaseDataDto = purchaseDataDto },
      (error) => { this.errorMessage = error.error}
    )
  }

}
