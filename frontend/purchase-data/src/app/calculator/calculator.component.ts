import { Component } from '@angular/core';
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

  errorMessage: string = '';

  constructor(private calculatorService: CalculatorService) { }

  calculate(): void {
    this.errorMessage = '';
    this.calculatorService.calculatePurchaseData(this.purchaseDataDto).subscribe(
      (purchaseDataDto) => { this.purchaseDataDto = purchaseDataDto },
      (error) => { this.errorMessage = error.error}
    )
  }

}
